package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.State;
import de.hsh.capstoneris.socketio.User;
import de.hsh.capstoneris.socketio.messages.client.LoginMessage;
import de.hsh.capstoneris.socketio.messages.server.HelloMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socketio.messages.server.error.InvalidInputErrorMessage;
import de.hsh.capstoneris.util.Authenticator;

public class LoginMessageListener implements DataListener<LoginMessage> {
    private Manager manager;

    public LoginMessageListener(Manager manager) {
        super();
        this.manager = manager;
    }


    @Override
    public void onData(SocketIOClient socketIOClient, LoginMessage loginMessage, AckRequest ackRequest) throws Exception {
        // See if the connected client is already logged in
        User user = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // If not logged in
        if (user == null) {
            // Get the username from the JWT token
            String username = Authenticator.verifyToken(loginMessage.token);
            socketIOClient.leaveRoom(SocketMessageTypes.CONNECTION_ROOM);

            // If the username could be retrieved from the token
            if (username != null) {
                socketIOClient.joinRoom(SocketMessageTypes.LOGGED_IN_ROOM);

                // Try to find the corresponding user-object to reuse it
                user = manager.getUserByNameIfExist(username);

                // If user object for that user has not been created yet, create it and add it to the manager
                if (user == null) {
                    user = new User(username);
                    user.setState(State.IDLE);
                    user.setSessionID(socketIOClient.getSessionId());
                    manager.addUser(user);
                } else {
                    // If user object exists, set the state to IDLE and and the session ID of this socketIOClient
                    user.setState(State.IDLE);
                    user.setSessionID(socketIOClient.getSessionId());
                }

                // Send the HelloMessage to the client
                socketIOClient.sendEvent(SocketMessageTypes.HELLO, new HelloMessage());
            } else {
                socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new InvalidInputErrorMessage());
                socketIOClient.disconnect();
            }
        } else {
            // Already logged in
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
        }
        System.out.println("Login Method End");
    }
}
