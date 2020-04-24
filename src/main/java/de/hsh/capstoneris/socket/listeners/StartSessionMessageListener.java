package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.SocketManager;
import de.hsh.capstoneris.socket.messages.client.StartSessionMessage;
import de.hsh.capstoneris.socket.session.SocketSession;

public class StartSessionMessageListener implements DataListener<StartSessionMessage> {

    private SocketManager socketManager;
    private String room;

    public StartSessionMessageListener(SocketManager socketManager) {
        this.socketManager = socketManager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, StartSessionMessage startSessionMessage, AckRequest ackRequest) throws Exception {



        // Create new Session Object ...socket manager
        //SocketSession session = new SocketSession(socketIOClient.getSessionId().toString()+"session", ); //socketManager.getLoggedInUsers().get(socketIOClient.getSessionId().toString()))
        // Get group from Database (message only gives group id/name?) attach to Session object

        // Create Invites for all users (maybe check if user is in same group here)

        // Send invite messages to all users

        // Send Session started message to the creator

        //add session to socket manager
    }
}
