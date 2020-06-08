package de.hsh.capstoneris.socketio.messages.server.error;

import de.hsh.capstoneris.socketio.messages.server.ErrorMessage;

public class IllegalOperationErrorMessage extends ErrorMessage {
    public IllegalOperationErrorMessage() {
        super(403, "Illegal Operation");
    }

    public IllegalOperationErrorMessage(String details) {
        super(403, "Illegal Operation: " + details);
    }
}
