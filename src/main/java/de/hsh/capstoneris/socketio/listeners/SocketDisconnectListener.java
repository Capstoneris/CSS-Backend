package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.State;
import de.hsh.capstoneris.socketio.User;

public class SocketDisconnectListener implements DisconnectListener {
    private Manager manager;

    public SocketDisconnectListener(Manager manager) {
        super();
        this.manager = manager;
    }


    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        // IMPORTANT: Clients get removed from rooms before this method is called!!!

        User user = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Only if user is actually logged in
        if (user != null) {
            user.setSessionID(null);
            user.setState(State.OFFLINE);
            user.setCurrentSession(null);
        }

    }
}
