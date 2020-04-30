package de.hsh.capstoneris.socketio.messages.server.error;

import de.hsh.capstoneris.socketio.messages.server.ErrorMessage;

public class InvalidInputErrorMessage extends ErrorMessage {
    public InvalidInputErrorMessage() {
        super(400, "Invalid Input");
    }
}
