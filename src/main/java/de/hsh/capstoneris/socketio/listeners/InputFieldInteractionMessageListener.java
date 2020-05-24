package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.rest.json.JsonInputfieldState;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.Manager;
import de.hsh.capstoneris.socketio.SharedSession;
import de.hsh.capstoneris.socketio.SocketMessageTypes;
import de.hsh.capstoneris.socketio.User;
import de.hsh.capstoneris.socketio.messages.client.InputFieldInteractionMessage;
import de.hsh.capstoneris.socketio.messages.server.InputfieldChangedMessage;
import de.hsh.capstoneris.util.Logger;
import de.hsh.capstoneris.util.Service;

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
        Logger.log(Service.SOCKET, "Getting InputfieldInteractionMessage. Content: fieldId: "
                + inputFieldInteractionMessage.fieldId +
                " oldValue: " + inputFieldInteractionMessage.oldValue +
                " newValue: " + inputFieldInteractionMessage.newValue +
                " changed: " + inputFieldInteractionMessage.changed);

        if (!inputFieldInteractionMessage.changed) {
            Logger.log(Service.SOCKET, "No change detected, returning for now (Selection will be handled soon (tm))");
            return;
        }
        User changingUser = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());
        if (changingUser != null) {
            Logger.log(Service.SOCKET, "Working on changing inputfield for " + changingUser.getUsername());
            String fieldId = inputFieldInteractionMessage.fieldId;
            SharedSession session = changingUser.getCurrentSession();
            JsonInputfieldState state = session.getInputfieldStateIfExists(fieldId);
            if (state == null) {
                Logger.log(Service.SOCKET, "Inputfield " + fieldId + " not yet existing in the manager, creating...");
                String newValue = "";
                if (inputFieldInteractionMessage.changed) {
                    newValue = inputFieldInteractionMessage.newValue;
                }
                state = new JsonInputfieldState(fieldId, newValue, null);
                session.getInputFieldStates().add(state);
                Logger.log(Service.SOCKET, "Sending value of inputfield " + fieldId + " to all users");
                socketIOClient.leaveRoom(session.getRoom().getName());
                socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(
                        SocketMessageTypes.INPUTFIELD_CHANGED,
                        new InputfieldChangedMessage(new JsonUser(changingUser), state)
                );
            } else {
                Logger.log(Service.SOCKET, "Comparing old values (" + state.value + " == " + inputFieldInteractionMessage.getOldValue() + ")");
                if (inputFieldInteractionMessage.getOldValue().equals(state.value)) {
                    Logger.log(Service.SOCKET, "Change accepted. Setting new value " + inputFieldInteractionMessage.newValue);
                    state.value = inputFieldInteractionMessage.newValue;
                    socketIOClient.leaveRoom(session.getRoom().getName());
                } else {
                    Logger.log(Service.SOCKET, "Sending old value " + state.value + " to all users.");
                }

                socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(
                        SocketMessageTypes.INPUTFIELD_CHANGED,
                        new InputfieldChangedMessage(new JsonUser(changingUser), state)
                );
            }
            socketIOClient.joinRoom(session.getRoom().getName());

        }


    }
}
