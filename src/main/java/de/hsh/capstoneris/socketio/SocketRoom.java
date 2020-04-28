package de.hsh.capstoneris.socketio;

import java.util.ArrayList;

public class SocketRoom {
    private final String name;
    private final ArrayList<User> users;

    public SocketRoom(String name) {
        this.name = name;
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
