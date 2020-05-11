package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonInvitation;

import java.util.ArrayList;

public class HelloMessage implements ServerMessage {
    public ArrayList<JsonInvitation> invitations;

    public HelloMessage(ArrayList<JsonInvitation> invitations) {
        this.invitations = invitations;
    }

    public ArrayList<JsonInvitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(ArrayList<JsonInvitation> invitations) {
        this.invitations = invitations;
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
