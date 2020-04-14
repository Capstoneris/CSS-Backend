package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.LeaveSessionMessage;

public class LeaveSessionMessageListener implements DataListener<LeaveSessionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, LeaveSessionMessage leaveSessionMessage, AckRequest ackRequest) throws Exception {

    }
}
