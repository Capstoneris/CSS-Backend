package de.hsh.capstoneris.data.factories;

import de.hsh.capstoneris.data.dto.MessageDTO;
import de.hsh.capstoneris.data.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MessageFactory extends Connection {
    public MessageDTO getMessageById(long id) {

        String sql = "select * from css.messages where id="+ id +";";

        MessageDTO resultMessage = new MessageDTO();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

                resultMessage.setId(id);
                resultMessage.setSent_by(resultSet.getLong("sent_by"));
                resultMessage.setSent_in(resultSet.getLong("sent_in_session"));
                resultMessage.setTime(resultSet.getLong("time"));
                resultMessage.setContent(resultSet.getString("content"));

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return resultMessage;
    }
    // public List<MessageDTO> getMessageBySessionId(long id)   // might be useful when a SessionDTO is created and filled with all its messages

    public void saveMessages(ArrayList<MessageDTO> messages) {
        for (MessageDTO message : messages){
            saveMessage(message);
        }
    }

    public void saveMessage(MessageDTO message){
        if(message.getId() == -1){
        String sql = "insert into css.messages VALUES (default," + message.getSent_by() + ","+message.getSent_in() + ","+message.getTime()+"," + message.getContent() + "');";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        }
    }
}
