package de.hsh.capstoneris.socketio.listeners;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.data.sql.Connection;
import de.hsh.capstoneris.rest.json.JsonUser;
import de.hsh.capstoneris.socketio.*;
import de.hsh.capstoneris.socketio.messages.client.StartSessionMessage;
import de.hsh.capstoneris.socketio.messages.server.SessionStartedMessage;
import de.hsh.capstoneris.socketio.messages.server.error.IllegalOperationErrorMessage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartSessionMessageListener implements DataListener<StartSessionMessage> {

    private Manager manager;

    public StartSessionMessageListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, StartSessionMessage startSessionMessage, AckRequest ackRequest) throws Exception {
        // Check if user is logged in
        if (!manager.isLoggedInBySessionID(socketIOClient.getSessionId())) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            socketIOClient.disconnect();
            return;
        }

        User host = manager.getUserBySessionIdIfExist(socketIOClient.getSessionId());

        // Check if user is currently not in a session
        if (host.getState() != State.IDLE) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            socketIOClient.disconnect();
            return;
        }

        Group group = null;
        // Get group from Database (message only gives group id/name?) attach to Session object
        try {
            Connection conn = new Connection();

            PreparedStatement stmt = conn.setupPreparedStatement("select id, title from css.groups g where g.title=?");

            stmt.setString(1, startSessionMessage.groupName);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            if (result.next()) {
                group = new Group(result.getString("title"), result.getInt("id"));
            }
            conn.closeConnections(result, stmt);

        } catch (SQLException | IOException | JWTCreationException e) {
            e.printStackTrace();
        }

        if (group == null) {
            socketIOClient.sendEvent(SocketMessageTypes.ERROR_MESSAGE, new IllegalOperationErrorMessage());
            return;
        }

        SharedSession session = new SharedSession(new SocketRoom(socketIOClient.getSessionId().toString() + "-session"), host, group);
        manager.addSession(session);

        // Create Invites for all users (maybe check if user is in same group here)
        for (JsonUser jsonUser : startSessionMessage.getUsers()) {
            User user = manager.getUserByNameIfExist(jsonUser.getUsername());
            if (user == null) {
                user = new User(jsonUser.getUsername());
            }
            user.addSessionUserIsInvitedTo(session);
            session.invite(user);
        }

        // Send invite messages to all users

        // Send Session started message to the creator
        host.setCurrentSession(session);
        host.setState(State.HOSTING);
        socketIOClient.sendEvent(SocketMessageTypes.SESSION_STARTED, new SessionStartedMessage());
    }
}
