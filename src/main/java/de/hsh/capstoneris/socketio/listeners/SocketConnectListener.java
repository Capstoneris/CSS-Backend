package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import de.hsh.capstoneris.socketio.SocketMessageTypes;

public class SocketConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        System.out.println("[SOCKET] Client connecting (" + socketIOClient.getSessionId() + ") from " + socketIOClient.getRemoteAddress());
        socketIOClient.joinRoom(SocketMessageTypes.CONNECTION_ROOM);
    }
}
