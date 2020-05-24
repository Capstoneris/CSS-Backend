package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.data.dto.MessageDTO;
import de.hsh.capstoneris.data.factories.MessageFactory;
import de.hsh.capstoneris.rest.json.JsonChatMessage;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SharedSession;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.User;
import de.hsh.capstoneris.socketio.messages.client.SendChatMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

public class SendChatMessageListener implements DataListener<SendChatMessage> {
    private Manager manager;
    private SocketIOServer socketIOServer;

    public SendChatMessageListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.manager = manager;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, SendChatMessage sendChatMessage, AckRequest ackRequest) throws Exception {
        Logger.log(Service.SOCKET, "Getting ChatMessage Content: messageContent: " + sendChatMessage.message.getMessage());
        if (!manager.isLoggedInBySessionID(socketIOClient.getSessionId())) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            return;
        }
        User user = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());
        if (user != null) {

            JsonChatMessage message = sendChatMessage.message;
            SharedSession session = user.getCurrentSession();
            if (session != null) {
                session.getChatHistory().add(message);

                //send Messages to clients
                socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(SocketMessageTypes.SERVER_CHAT_MESSAGE, new SendChatMessage(message));

                //saving messages to Database
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setSent_by(new JsonUser(user).getId());
                messageDTO.setContent(message.getMessage());
                messageDTO.setTime(message.getTimestamp());
                messageDTO.setSent_in(0); //Zero for now

                new MessageFactory().saveMessage(messageDTO);
            } else {
                socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            }
        }
    }

}
