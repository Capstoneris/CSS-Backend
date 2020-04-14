package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import de.hsh.capstoneris.socket.Socket;

public class SocketConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        System.out.println("[SOCKET] Client connecting (" + socketIOClient.getSessionId() + ") from " + socketIOClient.getRemoteAddress());
        socketIOClient.joinRoom(Socket.CONNECTION_ROOM);
    }
}
