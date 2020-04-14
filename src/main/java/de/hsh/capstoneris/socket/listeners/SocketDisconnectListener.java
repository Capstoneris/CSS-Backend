package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import de.hsh.capstoneris.socket.SocketManager;

public class SocketDisconnectListener implements DisconnectListener {
    private SocketManager socketManager;

    public SocketDisconnectListener(SocketManager socketManager) {
        super();
        this.socketManager = socketManager;
    }


    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        // IMPORTANT: Clients get removed from rooms before this method is called!!!

        System.out.println("[SOCKET] Client disconnecting (" + socketIOClient.getSessionId() + ") from " + socketIOClient.getRemoteAddress());
        if (socketManager.getLoggedInUsers().containsKey(socketIOClient.getSessionId().toString())) {
            System.out.println("[SOCKET] Client was logged in...");
            socketManager.removeLoggedInUser(socketIOClient.getSessionId());
        }
    }
}
