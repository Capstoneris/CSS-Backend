package de.hsh.capstoneris.socket.messages.server.error;

import de.hsh.capstoneris.socket.messages.server.ErrorMessage;

public class InvalidInputErrorMessage extends ErrorMessage {
    public InvalidInputErrorMessage() {
        super(400, "Invalid Input");
    }
}
