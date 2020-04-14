package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.KickMemberMessage;

public class KickMemberMessageListener implements DataListener<KickMemberMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, KickMemberMessage kickMemberMessage, AckRequest ackRequest) throws Exception {

    }
}
