package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.JoinSessionMessage;
import de.hsh.capstoneris.socketio.messages.server.MemberListUpdateMessage;
import de.hsh.capstoneris.socketio.messages.server.SessionJoinedMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socketio.messages.server.error.InvalidInputErrorMessage;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

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
        Logger.log(Service.SOCKET, "Client tries to join session hosted by " + joinSessionMessage.host.username);
        User guest = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Check if user is logged in
        if (guest == null) {
            Logger.log(Service.SOCKET, "Guest is not logged in. Disconnecting.");
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            socketIOClient.disconnect();
            return;
        }


        // Check if user is currently not in a session
        if (guest.getState() != State.IDLE) {
            Logger.log(Service.SOCKET, "Client is currently in a session and can't join another one.");
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            return;
        }

        Logger.log(Service.SOCKET, "Searching for requested session.");
        boolean sessionFound = false;
        for (SharedSession session : manager.getSessions()) {
            if (session.getHost().getUsername().equals(joinSessionMessage.getHost().getUsername()) && session.isAlive() && guest.getInvitedTo().contains(session)) {
                Logger.log(Service.SOCKET, "Session found. User " + guest.getUsername() + " joining.");
                session.join(guest);
                guest.setCurrentSession(session);
                guest.setState(State.JOINED);
                socketIOClient.joinRoom(session.getRoom().getName());
                ArrayList<JsonUser> joinedUsers = new ArrayList<>();
                for (User user : session.getJoinedUsers()) {
                    joinedUsers.add(new JsonUser(user));
                }
                Logger.log(Service.SOCKET, "Removing invite from guests invitation list.");
                guest.getInvitedTo().remove(session);
                manager.sendInvitationListUpdate(manager.getUserBySessionIdIfExist(socketIOClient.getSessionId()), socketIOServer);

                Logger.log(Service.SOCKET, "Sending session-joined message to guest");
                socketIOClient.sendEvent(SocketMessageTypes.SESSION_JOINED, new SessionJoinedMessage(new JsonUser(session.getHost()), new ArrayList<>(), new ArrayList<>()));

                Logger.log(Service.SOCKET, "Updating session members. There is/are now " + joinedUsers.size() + " users joined");
                socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, new MemberListUpdateMessage(joinedUsers));
                sessionFound = true;
                Logger.log(Service.SOCKET, "Join request completed.");
                break;
            }
        }

        if (!sessionFound) {
            Logger.log(Service.SOCKET, "Session not found.");
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new InvalidInputErrorMessage());
        }
    }
}
