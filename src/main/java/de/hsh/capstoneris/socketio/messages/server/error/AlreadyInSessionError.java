package de.hsh.capstoneris.socketio.messages.server.error;

import de.hsh.capstoneris.socketio.SocketMessageTypes;

public class AlreadyInSessionError extends IllegalOperationErrorMessage {
    public AlreadyInSessionError() {
        super(SocketMessageTypes.ALREADY_IN_SESSION);
    }
}
