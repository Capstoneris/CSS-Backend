package de.hsh.capstoneris.socketio.messages.server.error;

import de.hsh.capstoneris.socketio.SocketMessageTypes;

public class NotLoggedInError extends IllegalOperationErrorMessage {
    public NotLoggedInError() {
        super(SocketMessageTypes.NOT_LOGGED_IN);
    }
}
