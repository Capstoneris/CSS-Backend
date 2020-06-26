package de.hsh.capstoneris.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.rest.json.JsonInputfieldSelection;
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

import java.util.ArrayList;

public class InputFieldInteractionMessageListener implements DataListener<InputFieldInteractionMessage> {
    private Manager manager;
    private SocketIOServer socketIOServer;

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


        User changingUser = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());
        if (changingUser != null) {

            Logger.log(Service.SOCKET, "Working on changing inputfield for " + changingUser.getUsername());
            String fieldId = inputFieldInteractionMessage.fieldId;
            SharedSession session = changingUser.getCurrentSession();
            socketIOClient.leaveRoom(session.getRoom().getName());
            JsonInputfieldState state = session.getInputfieldStateIfExists(fieldId);
            if (state == null) {
                Logger.log(Service.SOCKET, "Inputfield " + fieldId + " not yet existing in the manager, creating...");
                String newValue = "";
                if (inputFieldInteractionMessage.changed) {
                    newValue = inputFieldInteractionMessage.newValue;
                }
                JsonUser changingUserJson = new JsonUser(changingUser);
                ArrayList<JsonInputfieldState> changedSelections = new ArrayList<>();
                for (JsonInputfieldState inputfieldState : session.getInputFieldStates()) {
                    ArrayList<JsonInputfieldSelection> newSelections = new ArrayList<>();
                    for (JsonInputfieldSelection selection : inputfieldState.selections) {
                        if (!selection.user.username.equals(changingUser.getUsername())) {
                            newSelections.add(selection);
                        } else {
                            changedSelections.add(inputfieldState);
                        }
                    }
                    inputfieldState.selections = newSelections.toArray(new JsonInputfieldSelection[0]);
                }
                state = new JsonInputfieldState(fieldId, newValue, new JsonInputfieldSelection[]{new JsonInputfieldSelection(changingUserJson, inputFieldInteractionMessage.selectionStart, inputFieldInteractionMessage.selectionEnd)});
                for (JsonInputfieldState inputfieldState : changedSelections) {
                    socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(
                            SocketMessageTypes.INPUTFIELD_CHANGED,
                            new InputfieldChangedMessage(new JsonUser(changingUser), inputfieldState)
                    );
                }
                session.getInputFieldStates().add(state);
                Logger.log(Service.SOCKET, "Sending value of inputfield " + fieldId + " to all users");
                socketIOClient.leaveRoom(session.getRoom().getName());
                socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(
                        SocketMessageTypes.INPUTFIELD_CHANGED,
                        new InputfieldChangedMessage(changingUserJson, state)
                );
            } else {
                ArrayList<JsonInputfieldState> changedSelections = new ArrayList<>();
                for (JsonInputfieldState inputfieldState : session.getInputFieldStates()) {
                    ArrayList<JsonInputfieldSelection> newSelections = new ArrayList<>();

                    for (JsonInputfieldSelection selection : inputfieldState.selections) {
                        if (!selection.user.username.equals(changingUser.getUsername())) {
                            newSelections.add(selection);
                        } else {
                            changedSelections.add(inputfieldState);
                        }
                    }

                    if (inputfieldState.fieldId.equals(state.fieldId)) {
                        newSelections.add(new JsonInputfieldSelection(new JsonUser(changingUser), inputFieldInteractionMessage.selectionStart, inputFieldInteractionMessage.selectionEnd));
                    }

                    inputfieldState.selections = newSelections.toArray(new JsonInputfieldSelection[0]);
                }
                for (JsonInputfieldState inputfieldState : changedSelections) {
                    socketIOServer.getRoomOperations(session.getRoom().getName()).sendEvent(
                            SocketMessageTypes.INPUTFIELD_CHANGED,
                            new InputfieldChangedMessage(new JsonUser(changingUser), inputfieldState)
                    );
                }
                Logger.log(Service.SOCKET, "Comparing old values (" + state.value + " == " + inputFieldInteractionMessage.getOldValue() + ")");
                if ((inputFieldInteractionMessage.getOldValue() == null && state.value == null) || inputFieldInteractionMessage.getOldValue().equals(state.value)) {
                    Logger.log(Service.SOCKET, "Change accepted. Setting new value " + inputFieldInteractionMessage.newValue);
                    state.value = inputFieldInteractionMessage.newValue;


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
