package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

public class SocketConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        Logger.log(Service.SOCKET, "Client connecting (" + socketIOClient.getSessionId() + ") from " + socketIOClient.getRemoteAddress());
        socketIOClient.joinRoom(SocketMessageTypes.CONNECTION_ROOM);
    }
}
