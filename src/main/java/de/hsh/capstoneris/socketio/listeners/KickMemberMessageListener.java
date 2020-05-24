package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.KickMemberMessage;
import de.hsh.capstoneris.socketio.messages.server.MemberListUpdateMessage;
import de.hsh.capstoneris.socketio.messages.server.SessionLeftMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

import java.util.ArrayList;

public class KickMemberMessageListener implements DataListener<KickMemberMessage> {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public KickMemberMessageListener(Manager manager, SocketIOServer socketIOServer) {
        this.manager = manager;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, KickMemberMessage kickMemberMessage, AckRequest ackRequest) throws Exception {
        Logger.log(Service.SOCKET, "Getting request to kick " + kickMemberMessage.member.username + " from the session");
        User host = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Check if user is logged in
        if (host == null) {
            Logger.log(Service.SOCKET, "Requesting user is not logged in. Disconnecting.");
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            socketIOClient.disconnect();
            return;
        }

        // Check if user is currently hosting a session
        if (host.getState() != State.HOSTING) {
            Logger.log(Service.SOCKET, "Error. Requesting user is not hosting a session");
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            return;
        }


        User toBeKicked = manager.getUserByNameIfExist(kickMemberMessage.getMember().getUsername());
        if (toBeKicked != host && toBeKicked != null && toBeKicked.getCurrentSession().equals(host.getCurrentSession())) {
            Logger.log(Service.SOCKET, "Removing user from session by " + host.getUsername());
            SharedSession currentSession = host.getCurrentSession();
            currentSession.kick(toBeKicked);
            SocketIOClient client = socketIOServer.getClient(toBeKicked.getSessionID());
            client.sendEvent(SocketMessageTypes.SESSION_LEFT, new SessionLeftMessage("Kicked by host (" + host.getUsername() + ")"));
            client.leaveRoom(currentSession.getRoom().getName());
            Logger.log(Service.SOCKET, "Sending member-list-update messages");
            ArrayList<JsonUser> joinedUsers = new ArrayList<>();
            for (User user : currentSession.getJoinedUsers()) {
                joinedUsers.add(new JsonUser(user));
            }
            socketIOServer.getRoomOperations(host.getCurrentSession().getRoom().getName()).sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, new MemberListUpdateMessage(joinedUsers));
            Logger.log(Service.SOCKET, "Kick request completed");
        }
    }
}
