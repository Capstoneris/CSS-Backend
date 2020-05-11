package de.hsh.capstoneris.socketio.messages.server;

public class SessionLeftMessage implements ServerMessage {
    public String reason;

    public SessionLeftMessage(String reason) {
        this.reason = reason;
    }
}
