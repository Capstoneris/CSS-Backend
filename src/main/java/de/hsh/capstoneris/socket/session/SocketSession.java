package de.hsh.capstoneris.socket.session;

import de.hsh.capstoneris.socket.SocketUser;

import java.util.ArrayList;

public class SocketSession {

    public String room;
    public SocketUser host;
    public ArrayList<SocketUser> joinedUsers;
    public ArrayList<String> chatHistory;

    public SocketSession(String room, SocketUser host, ArrayList<SocketUser> joinedUsers, ArrayList<String> chatHistory) {
        this.room = room;
        this.host = host;
        this.joinedUsers = joinedUsers;
        this.chatHistory = chatHistory;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public SocketUser getHost() {
        return host;
    }

    public void setHost(SocketUser host) {
        this.host = host;
    }

    public ArrayList<SocketUser> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(ArrayList<SocketUser> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    public ArrayList<String> getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ArrayList<String> chatHistory) {
        this.chatHistory = chatHistory;
    }
}
