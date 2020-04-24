package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.Socket;
import de.hsh.capstoneris.socket.SocketManager;
import de.hsh.capstoneris.socket.messages.ChatMessage;
import de.hsh.capstoneris.socket.messages.Message;
import de.hsh.capstoneris.socket.messages.server.error.IllegalOperationErrorMessage;

public class ChatMessageListener implements DataListener<ChatMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, ChatMessage chatMessage, AckRequest ackRequest) throws Exception {
        if (!SocketManager.isLoggedIn(socketIOClient)) {
            socketIOClient.sendEvent(Socket.ERROR_MESSAGE, new IllegalOperationErrorMessage());
        }
    }

}
