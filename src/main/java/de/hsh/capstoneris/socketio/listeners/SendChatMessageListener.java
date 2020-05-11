package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.messages.client.SendChatMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;

public class SendChatMessageListener implements DataListener<SendChatMessage> {
    private Manager manager;

    public SendChatMessageListener(Manager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, SendChatMessage sendChatMessage, AckRequest ackRequest) throws Exception {
        if (!manager.isLoggedInBySessionID(socketIOClient.getSessionId())) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
        }
    }

}
