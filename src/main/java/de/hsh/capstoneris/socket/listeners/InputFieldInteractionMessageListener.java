package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.InputFieldInteractionMessage;

public class InputFieldInteractionMessageListener implements DataListener<InputFieldInteractionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, InputFieldInteractionMessage inputFieldInteractionMessage, AckRequest ackRequest) throws Exception {

    }
}
