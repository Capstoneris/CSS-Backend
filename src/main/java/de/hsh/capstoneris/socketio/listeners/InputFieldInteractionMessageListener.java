package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SharedSession;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.User;
import de.hsh.capstoneris.socketio.messages.client.InputFieldInteractionMessage;
import de.hsh.capstoneris.socketio.messages.server.InputfieldChangedMessage;

public class InputFieldInteractionMessageListener implements DataListener<InputFieldInteractionMessage> {
    private Manager manager;
    private SocketIOServer socketIOServer;

    // TODO
    // Message:
    // public String fieldID;
    // public boolean changed;
    // public String oldValue;
    // public String newValue;
    // public int cursorStart;
    // public int cursorEnd;
    // Server checks if oldValue equals currently known value
    // if true, Server removes focus of sending user from all other input fields, sends inputfield-changed for all those other fields
    // Server sends inputfield-changed for changed field to all users with the current state (even if the change was not accepted)
    public InputFieldInteractionMessageListener(Manager manager, SocketIOServer socketIOServer) {
        super();
        this.manager = manager;
        this.socketIOServer = socketIOServer;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, InputFieldInteractionMessage inputFieldInteractionMessage, AckRequest ackRequest) throws Exception {
        User changingUser = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());
        String fieldId = inputFieldInteractionMessage.fieldID;
        SharedSession session = changingUser.getCurrentSession();
        if (inputFieldInteractionMessage.getOldValue().equals(session.getInputFieldStates().get(fieldId))) {
            String newValue = inputFieldInteractionMessage.newValue;
            session.getInputFieldStates().replace(fieldId, newValue);
            socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(
                    SocketMessageTypes.INPUTFIELD_CHANGED,
                    new InputfieldChangedMessage(changingUser.getUsername(), fieldId, newValue)
            );
        }
    }
}
