package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.Authenticator;
import de.hsh.capstoneris.socket.Socket;
import de.hsh.capstoneris.socket.messages.client.LoginMessage;
import de.hsh.capstoneris.socket.messages.server.HelloMessage;
import de.hsh.capstoneris.socket.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socket.messages.server.error.InvalidInputErrorMessage;

public class LoginMessageListener implements DataListener<LoginMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, LoginMessage loginMessage, AckRequest ackRequest) throws Exception {
        if (socketIOClient.getAllRooms().contains(Socket.CONNECTION_ROOM)) {
            String user = Authenticator.verifyToken(loginMessage.token);
            socketIOClient.leaveRoom(Socket.CONNECTION_ROOM);
            if (user != null) {
                socketIOClient.joinRoom(Socket.LOGGED_IN_ROOM);
                socketIOClient.sendEvent(Socket.HELLO, new HelloMessage());
            } else {
                socketIOClient.sendEvent(Socket.ERROR_MESSAGE, new InvalidInputErrorMessage());
                socketIOClient.disconnect();
            }
        } else {
            socketIOClient.sendEvent(Socket.ERROR_MESSAGE, new IllegalOperationErrorMessage());
        }
    }
}