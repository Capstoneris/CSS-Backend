package de.hsh.capstoneris.socket.messages.server;

import de.hsh.capstoneris.jsonObjects.Invitation;
import de.hsh.capstoneris.jsonObjects.LoginResponse;

import java.util.ArrayList;

public class HelloMessage implements ServerMessage {
    public ArrayList<Invitation> invitations;

    public HelloMessage() {
        this.invitations = new ArrayList<>();
        invitations.add(new Invitation(0, new LoginResponse(0, "Admin")));
        invitations.add(new Invitation(5, new LoginResponse(3, "Herbert")));
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
