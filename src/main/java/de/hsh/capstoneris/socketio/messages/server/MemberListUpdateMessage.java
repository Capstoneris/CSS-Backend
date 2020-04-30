package de.hsh.capstoneris.socketio.messages.server;

import java.util.ArrayList;

public class MemberListUpdateMessage implements ServerMessage {
    ArrayList<String> users;

    public MemberListUpdateMessage(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    /*TODO member-list-update
Neue Mitgliederliste
→ Client zeigt aktuelle Mitgliederliste an
*/
}
