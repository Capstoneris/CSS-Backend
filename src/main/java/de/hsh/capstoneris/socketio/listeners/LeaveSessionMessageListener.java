package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.data.sql.Connection;
import de.hsh.capstoneris.rest.json.JsonQuestion;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.LeaveSessionMessage;
import de.hsh.capstoneris.socketio.messages.server.MemberListUpdateMessage;
import de.hsh.capstoneris.socketio.messages.server.RatingQuestionsMessage;
import de.hsh.capstoneris.socketio.messages.server.SessionLeftMessage;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LeaveSessionMessageListener extends Connection implements DataListener<LeaveSessionMessage> {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public LeaveSessionMessageListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.socketIOServer = socketIOServer;
        this.manager = manager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, LeaveSessionMessage leaveSessionMessage, AckRequest ackRequest) throws Exception {
        Logger.log(Service.SOCKET, "Getting LeaveSessionMessage");

        User user = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Only if user is actually logged in
        if (user != null) {
            Logger.log(Service.SOCKET, "User was logged in. Cleaning up...");
            if (user.getState() == State.HOSTING) {
                Logger.log(Service.SOCKET, "User was hosting a session. Ending session...");
                SharedSession currentSession = user.getCurrentSession();
                currentSession.end();
                ArrayList<User> joinedUsers = (ArrayList<User>) currentSession.getJoinedUsers().clone();
                ArrayList<User> invitedUsers = currentSession.getInvitedUsers();
                socketIOServer.getRoomOperations(currentSession.getRoom().getName()).sendEvent(SocketMessageTypes.SESSION_LEFT, new SessionLeftMessage("The Host disconnected"));

                for (User next : joinedUsers) {
                    if (!next.equals(user)) {
                        currentSession.kick(next);
                        SocketIOClient client = socketIOServer.getClient(next.getSessionID());
                        client.leaveRoom(currentSession.getRoom().getName());
                    }
                }

                Logger.log(Service.SOCKET, "Removing remaining session invitations");
                for (User invited : invitedUsers) {
                    invited.removeInvite(currentSession);
                    manager.sendInvitationListUpdate(invited, socketIOServer);
                }

                user.setState(State.IDLE);
                user.setCurrentSession(null);

                // Get Survey Questions
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                ArrayList<JsonQuestion> questions = new ArrayList<>();

                try {
                    preparedStatement = setupPreparedStatement("select css.questions.id, css.questions.question, css.questions.active from css.questions");
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        if (resultSet.getBoolean("active")) {
                            JsonQuestion nextQuestion = new JsonQuestion();
                            nextQuestion.id = resultSet.getLong("id");
                            nextQuestion.question = resultSet.getString("question");
                            questions.add(nextQuestion);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    closeConnections(resultSet, preparedStatement);
                }

                socketIOClient.sendEvent(SocketMessageTypes.RATING_QUESTIONS, new RatingQuestionsMessage(questions));

            } else if (user.getState() == State.JOINED) {
                Logger.log(Service.SOCKET, "User joined a session. Leaving session...");
                SharedSession currentSession = user.getCurrentSession();
                currentSession.kick(user);

                ArrayList<JsonUser> joinedUsers = new ArrayList<>();
                for (User u : currentSession.getJoinedUsers()) {
                    joinedUsers.add(new JsonUser(u));
                }

                socketIOClient.sendEvent(SocketMessageTypes.SESSION_LEFT, new SessionLeftMessage("You disconnected"));
                socketIOServer.getRoomOperations(currentSession.getRoom().getName()).sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, new MemberListUpdateMessage(joinedUsers));
                user.setState(State.IDLE);
                user.setCurrentSession(null);
            }
        }
    }
}