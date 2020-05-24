package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonInvitation;

import java.util.ArrayList;

public class InvitationListUpdateMessage implements ServerMessage {

    //List of hostnames that invited the user
    public ArrayList<JsonInvitation> invitations;

    public void setInvitations(ArrayList<JsonInvitation> invitations) {
        this.invitations = invitations;
    }

    public InvitationListUpdateMessage(ArrayList<JsonInvitation> invitations) {
        this.invitations = invitations;
    }

    public ArrayList<JsonInvitation> getInvitations() {
        return invitations;
    }

    public InvitationListUpdateMessage() {
    }

    /*TODO invitation-list-update
    Namen aller Hosts, die den Nutzer eingeladen haben mit Zeitstempeln wann die letzte Einladung war
    → Client zeigt aktuelle Einladungsliste an und zeigt ggf. Eine Notification “neue Einladungen”
    → Client zeigt jeweils Button zum joinen → join-session*/
}
