package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonInvite;

import java.util.ArrayList;

public class HelloMessage implements ServerMessage {
    public ArrayList<JsonInvite> invites;

    public HelloMessage(ArrayList<JsonInvite> invites) {
        this.invites = invites;
    }

    public ArrayList<JsonInvite> getInvites() {
        return invites;
    }

    public void setInvites(ArrayList<JsonInvite> invites) {
        this.invites = invites;
    }

    /*TODO hello
    Liste aller Einladungen (nur für Sitzungen, die noch laufen!)
    Falls aktuell in einer Session:
    Name des Hosts
    Aktuelle Eingabefelder-Zustände
    Aktuelle Mitgliederliste
    Aktuellen Chatverlauf
    → Client vom Mitglied zeigt Formularinhalte des Hosts, Mitgliederliste und Chat an, Sitzung läuft
    */
}
