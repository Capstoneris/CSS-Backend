package de.hsh.capstoneris.socketio;

import java.util.ArrayList;
import java.util.UUID;

import static de.hsh.capstoneris.socketio.State.OFFLINE;

public class User {
    // SocketIO session ID, should be null if disconnected
    private UUID sessionID;

    // State of this user:
    //  IDLE: user is online but not hosting/joined a session
    //  HOSTING: user is currently hosting a session (currentSession)
    //  JOINED: user has joined and is taking part in a session (currentSession)
    //  OFFLINE: user is not connected to the socket server
    private State state;
    private SharedSession currentSession;

    private final String username;
    private ArrayList<SharedSession> invitedTo;


    public User(String username) {
        this.username = username;
        this.sessionID = null;
        this.state = OFFLINE;
        this.invitedTo = new ArrayList<>();
        this.currentSession = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public SharedSession getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(SharedSession session) {
        this.currentSession = currentSession;
    }

    public ArrayList<SharedSession> getInvitedTo() {
        return invitedTo;
    }

    public void addSessionUserIsInvitedTo(SharedSession session) {
        if (!invitedTo.contains(session)) {
            invitedTo.add(session);
        }
    }

    public UUID getSessionID() {
        return sessionID;
    }

    public void setSessionID(UUID sessionID) {
        this.sessionID = sessionID;
    }

    public String getUsername() {
        return username;
    }

}
