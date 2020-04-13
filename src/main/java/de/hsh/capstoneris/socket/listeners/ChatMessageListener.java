package de.hsh.capstoneris.socket.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socket.messages.client.ChatMessage;

public class ChatMessageListener implements DataListener<ChatMessage> {
    @Override
    public void onData(SocketIOClient socketIOClient, ChatMessage chatMessage, AckRequest ackRequest) throws Exception {

    }
}
