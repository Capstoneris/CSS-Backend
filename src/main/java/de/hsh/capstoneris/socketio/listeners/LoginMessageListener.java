package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.rest.json.JsonInvitation;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.LoginMessage;
import de.hsh.capstoneris.socketio.messages.server.HelloMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socketio.messages.server.error.InvalidInputErrorMessage;
import de.hsh.capstoneris.util.Authenticator;
import de.hsh.capstoneris.util.ConsoleColors;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

import java.util.ArrayList;

public class LoginMessageListener implements DataListener<LoginMessage> {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public LoginMessageListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.socketIOServer = socketIOServer;
        this.manager = manager;
    }


    @Override
    public void onData(SocketIOClient socketIOClient, LoginMessage loginMessage, AckRequest ackRequest) throws Exception {
        Logger.log(Service.SOCKET, "Client tries to login", ConsoleColors.YELLOW);
        // See if the connected client is already logged in
        User user = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // If not logged in
        if (user == null) {
            Logger.log(Service.SOCKET, "Verifying token...");
            // Get the username from the JWT token
            String username = Authenticator.verifyToken(loginMessage.token);
            socketIOClient.leaveRoom(SocketMessageTypes.CONNECTION_ROOM);

            // If the username could be retrieved from the token
            if (username != null) {
                Logger.log(Service.SOCKET, "Login successful, logged in user " + username, ConsoleColors.GREEN);
                socketIOClient.joinRoom(SocketMessageTypes.LOGGED_IN_ROOM);

                Logger.log(Service.SOCKET, "Checking if user exists in manager");
                // Try to find the corresponding user-object to reuse it
                user = manager.getUserByNameIfExist(username);

                // If user object for that user has not been created yet, create it and add it to the manager
                if (user == null) {
                    Logger.log(Service.SOCKET, "User not existing yet, creating new Object");
                    user = new User(username);
                    user.setState(State.IDLE);
                    user.setSessionID(socketIOClient.getSessionId());
                    manager.addUser(user);
                } else {
                    // If user object exists, check if already connected
                    // and disconnect old client
                    Logger.log(Service.SOCKET, "User existing, checking if another connection already exists");
                    if (user.getState() != State.OFFLINE) {
                        Logger.log(Service.SOCKET, "User already connected. Disconnecting previous session. Connection process complete", ConsoleColors.YELLOW);
                        socketIOServer.getClient(user.getSessionID()).disconnect();
                    } else {
                        Logger.log(Service.SOCKET, "User was not connected. Connection process complete", ConsoleColors.GREEN);
                    }

                    // set the state to IDLE and and the session ID of this socketIOClient
                    user.setState(State.IDLE);
                    user.setSessionID(socketIOClient.getSessionId());
                }


                Logger.log(Service.SOCKET, "Checking for invitations for user");
                // Send the HelloMessage to the client
                ArrayList<JsonInvitation> invites = new ArrayList<>();
                for (SharedSession sessions : user.getInvitedTo()) {
                    invites.add(new JsonInvitation(new JsonUser(sessions.getHost()), sessions.getInviteMessage(), sessions.getTimeStamp()));
                }
                Logger.log(Service.SOCKET, "Found " + invites.size() + " invitation(s)");
                socketIOClient.sendEvent(SocketMessageTypes.HELLO, new HelloMessage(invites));
            } else {
                Logger.log(Service.SOCKET, "Could not verify the user", ConsoleColors.RED);
                socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new InvalidInputErrorMessage());
                socketIOClient.disconnect();
            }
        } else {
            // Already logged in
            Logger.log(Service.SOCKET, "This session is already logged in!", ConsoleColors.RED);
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
        }
    }
}
