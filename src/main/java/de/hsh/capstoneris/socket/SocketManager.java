package de.hsh.capstoneris.socket;

import com.corundumstudio.socketio.SocketIOClient;

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

    public static boolean isLoggedIn(SocketIOClient socketIOClient) {
        return socketIOClient.getAllRooms().contains(Socket.LOGGED_IN_ROOM);
    }
}
