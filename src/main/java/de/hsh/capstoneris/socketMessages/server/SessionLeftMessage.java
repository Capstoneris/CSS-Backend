package de.hsh.capstoneris.socketMessages.server;

public class SessionLeftMessage implements ServerMessage {

    public String reason;

    public SessionLeftMessage(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    /*TODO session-left
    Grund: Verlassen/Gekickt
    → Client wechselt zu Hauptseite
    */
}
