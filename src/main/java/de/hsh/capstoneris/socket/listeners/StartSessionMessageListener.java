package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.StartSessionMessage;

public class StartSessionMessageListener implements DataListener<StartSessionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, StartSessionMessage startSessionMessage, AckRequest ackRequest) throws Exception {
        // Create new Session Object

        // Get group from Database (message only gives group id/name?) attach to Session object

        // Create Invites for all users (maybe check if user is in same group here)

        // Send invite messages to all users

        // Send Session started message to the creator

    }
}
