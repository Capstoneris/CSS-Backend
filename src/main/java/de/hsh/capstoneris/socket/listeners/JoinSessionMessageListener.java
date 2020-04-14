package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.JoinSessionMessage;

public class JoinSessionMessageListener implements DataListener<JoinSessionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, JoinSessionMessage joinSessionMessage, AckRequest ackRequest) throws Exception {

    }
}
