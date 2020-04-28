package de.hsh.capstoneris.socket.session;

import de.hsh.capstoneris.socket.SocketUser;

import java.util.ArrayList;

public class SocketSession {

    public String room;
    public SocketUser host;
    public String group;
    public ArrayList<SocketUser> joinedUsers;
    public ArrayList<SocketUser> invitedUsers;
    public ArrayList<String> chatHistory;

    public SocketSession(String room, SocketUser host) {
        this.room = room;
        this.host = host;
    }

    public void addInvitedUser(SocketUser user) {
        invitedUsers.add(user);
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
