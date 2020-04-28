package de.hsh.capstoneris.socket.listeners;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import de.hsh.capstoneris.jsonObjects.User;
import de.hsh.capstoneris.socket.Group;
import de.hsh.capstoneris.socket.Socket;
import de.hsh.capstoneris.socket.SocketManager;
import de.hsh.capstoneris.socket.SocketUser;
import de.hsh.capstoneris.socket.messages.client.StartSessionMessage;
import de.hsh.capstoneris.socket.messages.server.InvitationListUpdateMessage;
import de.hsh.capstoneris.socket.messages.server.SessionStartedMessage;
import de.hsh.capstoneris.socket.messages.server.error.IllegalOperationErrorMessage;
import de.hsh.capstoneris.socket.session.SocketSession;
import de.hsh.capstoneris.sql.Connection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StartSessionMessageListener implements DataListener<StartSessionMessage> {

    private SocketManager socketManager;
    public String groupName;
    public Group group;

    public StartSessionMessageListener(SocketManager socketManager) {
        this.socketManager = socketManager;
    }

    @Override
    public void onData(SocketIOClient socketIOClient, StartSessionMessage startSessionMessage, AckRequest ackRequest) throws Exception {


        // Create new Session Object ...socket manager
        SocketSession session = new SocketSession(socketIOClient.getSessionId().toString() + "session", socketManager.getLoggedInUsers().get(socketIOClient.getSessionId()));


        // Get group from Database (message only gives group id/name?) attach to Session object
        try {
            Connection conn = new Connection();

            PreparedStatement stmt = conn.setupPreparedStatement("select id from css.groups g where g.title=?"); //holt id für name
            //PreparedStatement stmt = conn.setupPreparedStatement("select title from css.groups g where g.id=?"); //holt name für id

            stmt.setString(1, groupName);
            stmt.execute();
            ResultSet result = stmt.getResultSet();
            if (result.next()) {
                group = new Group(groupName, result.getInt(1));
            }
            conn.closeConnections(result, stmt);

        } catch (SQLException | IOException | JWTCreationException e) {
            e.printStackTrace();
        }


        // Create Invites for all users (maybe check if user is in same group here)

        for (SocketUser user : startSessionMessage.getUsers()) {
            user.addInvitedTo(session);
            session.addInvitedUser(user);
        }

        // Send invite messages to all users


        // Send Session started message to the creator
        socketIOClient.sendEvent(Socket.SESSION_STARTED, new SessionStartedMessage());


        //add session to socket manager
        //setSession
        socketManager.addSession(session);

    }
}
