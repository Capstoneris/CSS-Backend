package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import de.hsh.capstoneris.socket.Socket;

public class SocketDisconnectListener implements DisconnectListener {
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        System.out.println("[SOCKET] Client disconnecting (" + socketIOClient.getSessionId() + ") from " + socketIOClient.getRemoteAddress());
        socketIOClient.leaveRoom(Socket.LOGGED_IN_ROOM);
        socketIOClient.joinRoom(Socket.CONNECTION_ROOM);
    }
}
