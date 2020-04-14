package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.Authenticator;
import de.hsh.capstoneris.socket.messages.client.LoginMessage;
import de.hsh.capstoneris.socket.messages.server.ErrorMessage;
import de.hsh.capstoneris.socket.messages.server.error.IllegalOperationErrorMessage;

public class LoginMessageListener implements DataListener<LoginMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, LoginMessage loginMessage, AckRequest ackRequest) throws Exception {
        if (socketIOClient.getAllRooms().contains("lobby")) {
            String user = Authenticator.verifyToken(loginMessage.token);
            socketIOClient.leaveRoom("lobby");
            if (user != null) {
                socketIOClient.joinRoom("logged-in");
                socketIOClient.sendEvent("hello", "[TEMPORARY MESSAGE] Hello " + user + ", you are logged in");
            } else {
                socketIOClient.sendEvent("error", new ErrorMessage(401, "Token invalid"));
                socketIOClient.disconnect();
            }
        } else {
            socketIOClient.sendEvent("error", new IllegalOperationErrorMessage());
        }
    }
}
