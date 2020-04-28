package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.SOCKETInvitation;
import de.hsh.capstoneris.rest.json.JsonLoginResponse;
import de.hsh.capstoneris.rest.json.JsonUser;

import java.util.ArrayList;

public class HelloMessage implements ServerMessage {
    public ArrayList<SOCKETInvitation> invitations;

    public HelloMessage() {
        this.invitations = new ArrayList<>();
        invitations.add(new SOCKETInvitation(0, new JsonLoginResponse(new JsonUser(0, "Admin"), "asdk")));
        invitations.add(new SOCKETInvitation(5, new JsonLoginResponse(new JsonUser(3, "Herbert"), "asdkasff")));
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
