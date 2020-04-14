package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.StartSessionMessage;

public class StartSessionMessageListener implements DataListener<StartSessionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, StartSessionMessage startSessionMessage, AckRequest ackRequest) throws Exception {

    }
}
