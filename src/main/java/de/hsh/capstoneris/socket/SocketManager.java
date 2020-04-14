package de.hsh.capstoneris.socket;

import java.util.HashMap;
import java.util.UUID;

public class SocketManager {
    private HashMap<String, String> loggedInUsers = new HashMap<>();

    public HashMap<String, String> getLoggedInUsers() {
        return loggedInUsers;
    }

    public void addLoggedInUser(String user, UUID id) {
        loggedInUsers.put(id.toString(), user);
    }

    public void removeLoggedInUser(UUID id) {
        loggedInUsers.remove(id.toString());
    }
}
