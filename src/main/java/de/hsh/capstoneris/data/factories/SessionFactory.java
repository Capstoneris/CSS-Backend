package de.hsh.capstoneris.data.factories;

import de.hsh.capstoneris.data.dto.MessageDTO;
import de.hsh.capstoneris.data.dto.SessionDTO;
import de.hsh.capstoneris.data.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SessionFactory extends Connection {

    public SessionDTO getSessionById(long sessionId) {
        String sql = "select css.sessions.id as session_id, css.users.name ,css.groups.title as group, css.sessions.host, css.sessions.location, css.sessions.host, css.sessions.time as session_time, css.messages.id as message_id, css.messages.content, css.messages.sent_by, css.messages.sent_in_session, css.messages.time as message_time from css.groups join css.sessions on (sessions.group=groups.id) join css.users on (sessions.host=users.id) join css.messages on (sessions.id=messages.sent_in_session) Where css.sessions.id=" + sessionId + ";";

        SessionDTO resultSession = new SessionDTO();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            resultSession.setId(resultSet.getLong("session_id"));
            resultSession.setHost(resultSet.getString("name"));
            resultSession.setLocation(resultSet.getString("location"));
            resultSession.setGroup(resultSet.getString("group"));
            resultSession.setTime(resultSet.getLong("session_time"));

            MessageDTO firstMessage = new MessageDTO();

            firstMessage.setId(resultSet.getLong("message_id"));
            firstMessage.setSent_by(resultSet.getLong("sent_by"));
            firstMessage.setSent_in(resultSet.getLong("sent_in_session"));
            firstMessage.setTime(resultSet.getLong("message_time"));
            firstMessage.setContent(resultSet.getString("content"));

            resultSession.addMessages(firstMessage);

            while (resultSet.next()) {
                MessageDTO nextMessage = new MessageDTO();

                nextMessage.setId(resultSet.getLong("message_id"));
                nextMessage.setSent_by(resultSet.getLong("sent_by"));
                nextMessage.setSent_in(resultSet.getLong("sent_in_session"));
                nextMessage.setTime(resultSet.getLong("message_time"));
                nextMessage.setContent(resultSet.getString("content"));

                resultSession.addMessages(firstMessage);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultSession;
    }

    public ArrayList<SessionDTO> getSessionsByLocation(String location){
        String sql = "select distinct css.sessions.id as sessionid, css.sessions.location from css.sessions where UPPER(css.sessions.location) like Upper('"+location+"');";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<SessionDTO> resultSessions = new ArrayList<>();
        ArrayList<Long> sessionIds = new ArrayList<>();

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                sessionIds.add(resultSet.getLong("sessionid"));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        for (Long id : sessionIds) {
            resultSessions.add(this.getSessionById(id));
        }
        return resultSessions;
    }

    public void saveSession(SessionDTO session){
        if(session.getId() == -1){
            String sql = "insert into css.sessions values (default, "+session.getHost()+",'"+session.getLocation()+"', "+session.getGroup()+", "+session.getTime()+");";

            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            MessageFactory messageFactory = new MessageFactory();

            try {
                preparedStatement = setupPreparedStatement(sql);
                preparedStatement.executeUpdate();
                for (MessageDTO message : session.getMessages()){
                    messageFactory.saveMessage(message);
                }

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeConnections(resultSet, preparedStatement);
            }
        }
    }
}
