package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DisconnectListener;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.server.MemberListUpdateMessage;

import java.util.ArrayList;

public class SocketDisconnectListener implements DisconnectListener {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public SocketDisconnectListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.socketIOServer = socketIOServer;
        this.manager = manager;
    }


    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        // IMPORTANT: Clients get removed from rooms before this method is called!!!

        User user = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Only if user is actually logged in
        if (user != null) {
            if (user.getState() == State.HOSTING) {
                SharedSession currentSession = user.getCurrentSession();
                currentSession.end();
                ArrayList<User> joinedUsers = (ArrayList<User>) currentSession.getJoinedUsers().clone();
                ArrayList<User> invitedUsers = currentSession.getInvitedUsers();
                socketIOServer.getRoomOperations(currentSession.getRoom().getName()).sendEvent(SocketMessageTypes.SESSION_LEFT, "SESSION ENDED BY " + user.getUsername().toUpperCase());

                for (User next : joinedUsers) {
                    if (!next.equals(user)) {
                        currentSession.kick(next);
                        SocketIOClient client = socketIOServer.getClient(next.getSessionID());
                        client.leaveRoom(currentSession.getRoom().getName());
                    }
                }

                for (User invited : invitedUsers) {
                    invited.removeInvite(currentSession);
                    manager.sendInvitationListUpdate(invited, socketIOServer);
                }


                // TODO SAVE SESSION INFORMATION TO THE DATABASE
            } else if (user.getState() == State.JOINED) {
                System.out.println("Trying to leave session");
                SharedSession currentSession = user.getCurrentSession();
                currentSession.kick(user);

                ArrayList<JsonUser> joinedUsers = new ArrayList<>();
                for (User u : currentSession.getJoinedUsers()) {
                    joinedUsers.add(new JsonUser(u));
                }

                socketIOClient.sendEvent(SocketMessageTypes.LEAVE_SESSION, "SESSION LEFT (" + user.getUsername().toUpperCase() + ")");
                socketIOServer.getRoomOperations(currentSession.getRoom().getName()).sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, new MemberListUpdateMessage(joinedUsers));
            }

            user.setSessionID(null);
            user.setState(State.OFFLINE);
        }

    }


}
