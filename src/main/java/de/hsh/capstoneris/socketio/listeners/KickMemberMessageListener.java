package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.KickMemberMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;

public class KickMemberMessageListener implements DataListener<KickMemberMessage> {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public KickMemberMessageListener(Manager manager, SocketIOServer socketIOServer) {
        this.manager = manager;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, KickMemberMessage kickMemberMessage, AckRequest ackRequest) throws Exception {
        User host = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Check if user is logged in
        if (host == null) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            socketIOClient.disconnect();
            return;
        }

        // Check if user is currently hosting a session
        if (host.getState() != State.HOSTING) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            return;
        }

        User toBeKicked = manager.getUserByNameIfExist(kickMemberMessage.getMember().getUsername());
        if (toBeKicked.getCurrentSession().equals(host.getCurrentSession())) {
            SharedSession currentSession = host.getCurrentSession();
            currentSession.kick(toBeKicked);
            SocketIOClient client = socketIOServer.getClient(toBeKicked.getSessionID());
            client.sendEvent(SocketMessageTypes.SESSION_LEFT, "KICKED BY " + host.getUsername());
            client.leaveRoom(currentSession.getRoom().getName());
            socketIOServer.getRoomOperations(host.getCurrentSession().getRoom().getName()).sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, "SOMEBODY GOT KICKED (" + toBeKicked.getUsername().toUpperCase() + ")");
        }
    }
}
