package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.JoinSessionMessage;

public class JoinSessionMessageListener implements DataListener<JoinSessionMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, JoinSessionMessage joinSessionMessage, AckRequest ackRequest) throws Exception {
        // Check if Session is started
        //   if already over: send error message
        // Check if user is really invited
        //   if not: send error message
        // Check if user is already in session
        //   if so: send error message
        // Remove invite from user's invite-list
        // Set user's state to in session xyz
        // Send session-joined to client
        // Send invitation-list-update to client (now without the current invite)
        // Send member-list-update to all clients in the session (host too)
    }
}
