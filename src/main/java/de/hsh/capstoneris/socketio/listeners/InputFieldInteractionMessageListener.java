package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.messages.client.InputFieldInteractionMessage;

public class InputFieldInteractionMessageListener implements DataListener<InputFieldInteractionMessage> {
    private Manager manager;

    public InputFieldInteractionMessageListener(Manager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, InputFieldInteractionMessage inputFieldInteractionMessage, AckRequest ackRequest) throws Exception {

    }
}
