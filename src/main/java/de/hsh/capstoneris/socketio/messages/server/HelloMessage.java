package de.hsh.capstoneris.socketio.messages.server;

import java.util.ArrayList;

public class HelloMessage implements ServerMessage {
    public ArrayList<String> invites;

    public HelloMessage(ArrayList<String> invites) {
        this.invites = invites;
    }

    public ArrayList<String> getInvites() {
        return invites;
    }

    public void setInvites(ArrayList<String> invites) {
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
