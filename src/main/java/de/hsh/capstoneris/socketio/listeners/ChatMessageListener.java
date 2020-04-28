package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.messages.ChatMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;

public class ChatMessageListener implements DataListener<ChatMessage> {
    private Manager manager;

    public ChatMessageListener(Manager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, ChatMessage chatMessage, AckRequest ackRequest) throws Exception {
        if (!manager.isLoggedInBySessionID(socketIOClient.getSessionId())) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
        }
    }

}
