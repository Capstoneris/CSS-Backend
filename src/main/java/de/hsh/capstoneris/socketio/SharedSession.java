package de.hsh.capstoneris.socketio;

import de.hsh.capstoneris.rest.json.JsonInputfieldState;

import java.util.ArrayList;

public class SharedSession {
    private final SocketRoom room;
    private final User host;
    private final Group group;
    private final ArrayList<User> joinedUsers = new ArrayList<>();
    private final ArrayList<User> invitedUsers = new ArrayList<>();
    private final ArrayList<String> chatHistory = new ArrayList<>();
    private boolean alive = true;
    private ArrayList<JsonInputfieldState> inputFieldStates = new ArrayList<>();
    private String inviteMessage;
    private long timeStamp;

    public SharedSession(SocketRoom room, User host, Group group, String inviteMessage, long timeStamp) {
        this.room = room;
        this.host = host;
        this.group = group;
        this.inviteMessage = inviteMessage;
        this.timeStamp = timeStamp;
        joinedUsers.add(this.host);
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void invite(User user) {
        if (!invitedUsers.contains(user)) {
            invitedUsers.add(user);
        }
    }

    public void invite(ArrayList<User> users) {
        for (User user : users) {
            invite(users);
        }
    }

    public void join(User user) {
        if (!joinedUsers.contains(user)) {
            joinedUsers.add(user);
            user.setState(State.JOINED);
            user.setCurrentSession(this);
            user.getInvitedTo().remove(this);
        }
    }

    public void kick(User user) {
        if (joinedUsers.contains(user)) {
            joinedUsers.remove(user);
            user.setCurrentSession(null);
            user.setState(State.IDLE);
        }
    }

    public JsonInputfieldState getInputfieldStateIfExists(String fieldId) {
        for (JsonInputfieldState state : inputFieldStates) {
            if (state.fieldId.equals(fieldId)) {
                return state;
            }
        }
        return null;
    }

    public SocketRoom getRoom() {
        return room;
    }

    public User getHost() {
        return host;
    }

    public ArrayList<User> getInvitedUsers() {
        return invitedUsers;
    }

    public ArrayList<User> getJoinedUsers() {
        return joinedUsers;
    }

    public ArrayList<String> getChatHistory() {
        return chatHistory;
    }

    public String getInviteMessage() {
        return inviteMessage;
    }

    public ArrayList<JsonInputfieldState> getInputFieldStates() {
        return inputFieldStates;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isAlive() {
        return alive;
    }

    public void end() {
        alive = false;
    }
}
