package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;

public class SocketConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        socketIOClient.joinRoom("lobby");
    }
}
