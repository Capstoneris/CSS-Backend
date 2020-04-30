package de.hsh.capstoneris.socketio.messages.server;

import java.util.ArrayList;

public class InvitationListUpdateMessage implements ServerMessage {

    //List of hostnames that invited the user
    public ArrayList<String> invites;

    public void setInvites(ArrayList<String> invites) {
        this.invites = invites;
    }

    public InvitationListUpdateMessage(ArrayList<String> invites) {
        this.invites = invites;
    }

    public ArrayList<String> getInvites() {
        return invites;
    }

    public InvitationListUpdateMessage() {
    }

    /*TODO invitation-list-update
    Namen aller Hosts, die den Nutzer eingeladen haben mit Zeitstempeln wann die letzte Einladung war
    → Client zeigt aktuelle Einladungsliste an und zeigt ggf. Eine Notification “neue Einladungen”
    → Client zeigt jeweils Button zum joinen → join-session*/
}
