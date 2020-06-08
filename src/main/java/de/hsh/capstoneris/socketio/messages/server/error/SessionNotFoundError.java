package de.hsh.capstoneris.socketio.messages.server.error;

import de.hsh.capstoneris.socketio.SocketMessageTypes;

public class SessionNotFoundError extends InvalidInputErrorMessage {
    public SessionNotFoundError() {
        super(SocketMessageTypes.SESSION_NOT_FOUND);
    }
}
