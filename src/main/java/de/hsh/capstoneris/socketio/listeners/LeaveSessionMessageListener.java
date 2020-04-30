package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.messages.client.LeaveSessionMessage;

public class LeaveSessionMessageListener implements DataListener<LeaveSessionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, LeaveSessionMessage leaveSessionMessage, AckRequest ackRequest) throws Exception {

    }
}
