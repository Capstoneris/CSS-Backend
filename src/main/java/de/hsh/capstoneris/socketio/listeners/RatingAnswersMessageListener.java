package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.messages.client.RatingAnswersMessage;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

public class RatingAnswersMessageListener implements DataListener<RatingAnswersMessage> {
    private final Manager manager;
    private final SocketIOServer socketIOServer;

    public RatingAnswersMessageListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.socketIOServer = socketIOServer;
        this.manager = manager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, RatingAnswersMessage ratingAnswersMessage, AckRequest ackRequest) throws Exception {
        Logger.log(Service.SOCKET, "Getting RatingAnswersMessage");

    }
}