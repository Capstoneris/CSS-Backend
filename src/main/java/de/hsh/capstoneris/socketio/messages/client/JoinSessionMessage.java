package de.hsh.capstoneris.socketio.messages.client;

import de.hsh.capstoneris.rest.json.JsonUser;

public class JoinSessionMessage implements ClientMessage {

    public JsonUser host;

    public JoinSessionMessage(JsonUser host) {
        this.host = host;
    }

    public JoinSessionMessage() {
    }

    public JsonUser getHost() {
        return host;
    }

    public void setHost(JsonUser host) {
        this.host = host;
    }

    /*TODO join-session
    Name des Hosts
    → Server prüft, ob dieser Host Session gestartet hat
    → Server prüft, ob Benutzer überhaupt zu der Sitzung dieses Hosts eingeladen wurde
    → Server prüft, ob Nutzer bereits Teil einer Sitzung
    → Server merkt sich Nutzerzustand: “teilgenommen an session von host X”
    → Server löscht entsprechendes “eingeladen zu session von host X” aus Nutzerzustand
    → Server schickt session-joined an Mitglied, das gerade gejoined ist
    → Server schickt invitation-list-update an Mitglied, das gerade gejoined ist
    → Server schickt member-list-update an alle Mitglieder (auch den Host)
    * */
}
