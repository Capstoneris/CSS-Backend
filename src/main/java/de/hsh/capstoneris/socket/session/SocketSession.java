package de.hsh.capstoneris.socket.session;

import de.hsh.capstoneris.socket.SocketUser;

import java.util.ArrayList;

public class SocketSession {

    public String room;
    public SocketUser host;
    public ArrayList<SocketUser> joinedUsers;
    public ArrayList<String> chatHistory;

}
