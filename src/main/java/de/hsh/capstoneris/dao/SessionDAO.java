package de.hsh.capstoneris.dao;

import de.hsh.capstoneris.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class SessionDAO extends Connection {

    private long id;
    private String host;
    private String location;
    private String group;
    private String time;
    private ArrayList<MessageDAO> messages = new ArrayList<>();


    public SessionDAO (long sessionId) {
        // Use prepared statements here to get data from PostgreSQL database

        String sql = "select css.sessions.id as session_id, css.users.name ,css.groups.title as group, css.sessions.host, css.sessions.location, css.sessions.host, css.sessions.time as session_time, css.messages.id as message_id, css.messages.content, css.messages.sent_by, css.messages.sent_in_session, css.messages.time as message_time from css.groups join css.sessions on (sessions.group=groups.id) join css.users on (sessions.host=users.id) join css.messages on (sessions.id=messages.sent_in_session) Where css.sessions.id="+ sessionId +";";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            this.id = resultSet.getLong("session_id");
            this.host = resultSet.getString("name");
            this.location = resultSet.getString("location");
            this.group = resultSet.getString("group");
            this.time = resultSet.getString("session_time");

            MessageDAO messageDummy = new MessageDAO();

            messageDummy.setId(resultSet.getLong("message_id"));
            messageDummy.setSent_by(resultSet.getLong("sent_by"));
            messageDummy.setSent_in(resultSet.getLong("sent_in_session"));
            messageDummy.setTime(resultSet.getString("message_time"));
            messageDummy.setContent(resultSet.getString("content"));

            messages.add(messageDummy);




            while(resultSet.next()) {
                messageDummy = new MessageDAO();

                messageDummy.setId(resultSet.getLong("message_id"));
                messageDummy.setSent_by(resultSet.getLong("sent_by"));
                messageDummy.setSent_in(resultSet.getLong("sent_in_session"));
                messageDummy.setTime(resultSet.getString("message_time"));
                messageDummy.setContent(resultSet.getString("content"));

                messages.add(messageDummy);
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }

    }
}