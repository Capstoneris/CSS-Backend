package de.hsh.capstoneris.socket;

import de.hsh.capstoneris.socket.session.SocketSession;

import java.util.ArrayList;

public class SocketUser {
    public int sessionID;
    public ArrayList<String> currentRooms;

    public SocketUser(int sessionID, ArrayList<String> currentRooms) {
        this.sessionID = sessionID;
        this.currentRooms = currentRooms;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public ArrayList<String> getCurrentRooms() {
        return currentRooms;
    }

    public void setCurrentRooms(ArrayList<String> currentRooms) {
        this.currentRooms = currentRooms;
    }
}
