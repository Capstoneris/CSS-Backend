package de.hsh.capstoneris.socketio.messages.client;

public class KickMemberMessage implements ClientMessage {
    public String username;

    public KickMemberMessage() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    /* TODO kick-member
    → Server entfernt von allen anderen Feldern, die von diesem Mitglied fokussiert sind, den Fokus und schickt dann ggf. Ein inputfield-changed für diese Felder an alle Mitglieder (außer dieses)
    → Server setzt Nutzerzustand zurück
    → Server sendet session-leaved an Mitglied
    → Server sendet member-list-update an alle Mitglieder (auch den Host)
            */
}
