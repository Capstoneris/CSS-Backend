package de.hsh.capstoneris.socket;

import com.corundumstudio.socketio.SocketIOClient;
import de.hsh.capstoneris.socket.session.SocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SocketManager {
    public ArrayList<SocketSession> sessions;

    private HashMap<String, SocketUser> loggedInUsers = new HashMap<>();

    public HashMap<String, SocketUser> getLoggedInUsers() {
        return loggedInUsers;
    }

    public void addLoggedInUser(SocketUser user, UUID id) {
        loggedInUsers.put(id.toString(), user);
    }

    public void removeLoggedInUser(UUID id) {
        loggedInUsers.remove(id.toString());
    }

    public static boolean isLoggedIn(SocketIOClient socketIOClient) {
        return socketIOClient.getAllRooms().contains(Socket.LOGGED_IN_ROOM);
    }

    public ArrayList<SocketSession> getSessions() {
        return sessions;
    }
}
