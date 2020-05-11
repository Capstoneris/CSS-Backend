package de.hsh.capstoneris.socketio.messages.server;

import de.hsh.capstoneris.rest.json.JsonUser;

import java.util.ArrayList;

public class MemberListUpdateMessage implements ServerMessage {
    ArrayList<JsonUser> users;

    public MemberListUpdateMessage(ArrayList<JsonUser> users) {
        this.users = users;
    }

    public ArrayList<JsonUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<JsonUser> users) {
        this.users = users;
    }

    /*TODO member-list-update
Neue Mitgliederliste
→ Client zeigt aktuelle Mitgliederliste an
*/
}
