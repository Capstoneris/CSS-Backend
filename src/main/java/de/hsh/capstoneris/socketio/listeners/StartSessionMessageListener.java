package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.data.dto.GroupDTO;
import de.hsh.capstoneris.data.factories.GroupFactory;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.StartSessionMessage;
import de.hsh.capstoneris.socketio.messages.server.MemberListUpdateMessage;
import de.hsh.capstoneris.socketio.messages.server.SessionStartedMessage;
import de.hsh.capstoneris.socketio.messages.server.error.AlreadyInSessionError;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socketio.messages.server.error.InvalidInputErrorMessage;
import de.hsh.capstoneris.socketio.messages.server.error.NotLoggedInError;
import de.hsh.capstoneris.util.ConsoleColors;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

import java.util.ArrayList;

public class StartSessionMessageListener implements DataListener<StartSessionMessage> {

    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public StartSessionMessageListener(Manager manager, SocketIOServer socketIOServer) {
        this.manager = manager;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, StartSessionMessage startSessionMessage, AckRequest ackRequest) throws Exception {
        Logger.log(Service.SOCKET, "Client tries to start a shared session.", ConsoleColors.YELLOW);
        User host = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Check if user is logged in
        if (host == null) {
            Logger.log(Service.SOCKET, "Client not logged in. Disconnecting.", ConsoleColors.RED);
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new NotLoggedInError());
            socketIOClient.disconnect();
            return;
        }

        // Check if user is currently not in a session
        if (host.getState() != State.IDLE) {
            Logger.log(Service.SOCKET, "Client is currently in a session and can't start a new one.", ConsoleColors.RED);
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new AlreadyInSessionError());
            return;
        }

        GroupFactory groupFac = new GroupFactory();
        GroupDTO groupDTO = groupFac.getGroupByTitle(startSessionMessage.getGroup().getTitle());
        Group group = null;
        if (groupDTO != null) {
            group = new Group(groupDTO.getTitle(), (int) groupDTO.getId());
        }

        if (group == null) {
            Logger.log(Service.SOCKET, "Provided group information was invalid.", ConsoleColors.RED);
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new InvalidInputErrorMessage("Provided group information is invalid!"));
            return;
        }

        Logger.log(Service.SOCKET, "Creating session for user " + host.getUsername(), ConsoleColors.GREEN);
        SharedSession session = new SharedSession(new SocketRoom(socketIOClient.getSessionId().toString() + "-session"), host, group, startSessionMessage.message, startSessionMessage.timestamp);
        manager.addSession(session);

        Logger.log(Service.SOCKET, "Creating invitations for invited users");
        // Create Invites for all users (maybe check if user is in same group here)
        for (JsonUser jsonUser : startSessionMessage.getUsers()) {
            Logger.log(Service.SOCKET, "Inviting user " + jsonUser.username + ". Checking if already exists in manager");
            User user = manager.getUserByNameIfExist(jsonUser.getUsername());
            if (user == host) {
                // user should not invite himself/herself
                socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage("You can't invite yourself!"));
                continue;
            }
            if (user == null) {
                Logger.log(Service.SOCKET, "User not existing yet. Creating...");
                user = new User(jsonUser.getUsername());
                manager.addUser(user);
            }
            user.addSessionUserIsInvitedTo(session);
            session.invite(user);
        }

        Logger.log(Service.SOCKET, "Setting shared session to active. Sending session-started message to host", ConsoleColors.GREEN);
        // Send Session started message to the creator
        host.setCurrentSession(session);
        host.setState(State.HOSTING);
        socketIOClient.sendEvent(SocketMessageTypes.SESSION_STARTED, new SessionStartedMessage());
        ArrayList<JsonUser> memberList = new ArrayList<>();
        memberList.add(new JsonUser(host));
        socketIOClient.sendEvent(SocketMessageTypes.MEMBER_LIST_UPDATE, new MemberListUpdateMessage(memberList));
        socketIOClient.joinRoom(session.getRoom().getName());

        Logger.log(Service.SOCKET, "Trying to invite currently connected users");
        // Send invite messages to all users
        for (User user : session.getInvitedUsers()) {
            manager.sendInvitationListUpdate(user, socketIOServer);
        }
    }
}
