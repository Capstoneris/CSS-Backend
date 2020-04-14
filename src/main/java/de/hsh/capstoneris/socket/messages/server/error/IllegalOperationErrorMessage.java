package de.hsh.capstoneris.socket.messages.server.error;

import de.hsh.capstoneris.socket.messages.server.ErrorMessage;

public class IllegalOperationErrorMessage extends ErrorMessage {
    public IllegalOperationErrorMessage() {
        super(403, "Illegal Operation");
    }
}
