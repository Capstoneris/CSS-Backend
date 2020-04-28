package de.hsh.capstoneris.socket;

import de.hsh.capstoneris.socket.messages.server.InvitationListUpdateMessage;
import de.hsh.capstoneris.socket.session.SocketSession;

import java.util.ArrayList;

public class SocketUser {
    public int sessionID;


    public String username;
    public ArrayList<String> currentRooms;
    public String socketSessionID;
    public ArrayList<SocketSession> invitedTo;

    public ArrayList<SocketSession> getInvitedTo() {
        return invitedTo;
    }

    public void setInvitedTo(ArrayList<SocketSession> invitedTo) {
        this.invitedTo = invitedTo;
    }

    public void addInvitedTo(SocketSession session) {
        invitedTo.add(session);
    }


    public String getSocketSessionID() {
        return socketSessionID;
    }

    public void setSocketSessionID(String socketSessionID) {
        this.socketSessionID = socketSessionID;
    }

    public SocketUser(String username) {
        this.username = username;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getCurrentRooms() {
        return currentRooms;
    }

    public void setCurrentRooms(ArrayList<String> currentRooms) {
        this.currentRooms = currentRooms;
    }
}
