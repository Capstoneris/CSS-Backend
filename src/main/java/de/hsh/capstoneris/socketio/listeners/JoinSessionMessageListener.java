package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.JoinSessionMessage;
import de.hsh.capstoneris.socketio.messages.server.MemberListUpdateMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socketio.messages.server.error.InvalidInputErrorMessage;

import java.util.ArrayList;

public class JoinSessionMessageListener implements DataListener<JoinSessionMessage> {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public JoinSessionMessageListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.manager = manager;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, JoinSessionMessage joinSessionMessage, AckRequest ackRequest) throws Exception {
        User guest = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Check if user is logged in
        if (guest == null) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            socketIOClient.disconnect();
            return;
        }


        // Check if user is currently not in a session
        if (guest.getState() != State.IDLE) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            return;
        }

        for (SharedSession session : manager.getSessions()) {
            if (session.getHost().getUsername().equals(joinSessionMessage.hostName) && session.isAlive()) {
                session.join(guest);
                guest.setCurrentSession(session);
                guest.setState(State.JOINED);
                socketIOClient.joinRoom(session.getRoom().getName());
                ArrayList<String> joinedUsers = new ArrayList<>();
                for (User user : session.getJoinedUsers()) {
                    joinedUsers.add(user.getUsername());
                }
                socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, new MemberListUpdateMessage(joinedUsers));
                break;
            } else {
                socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new InvalidInputErrorMessage());
            }
        }


        // Check if Session is started
        //   if already over: send error message
        // Check if user is really invited
        //   if not: send error message
        // Check if user is already in session
        //   if so: send error message
        // Remove invite from user's invite-list
        // Set user's state to in session xyz
        // Send session-joined to client
        // Send invitation-list-update to client (now without the current invite)
        // Send member-list-update to all clients in the session (host too)
    }
}
