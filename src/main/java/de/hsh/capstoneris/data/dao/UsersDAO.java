package de.hsh.capstoneris.data.dao;

import de.hsh.capstoneris.data.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersDAO extends Connection {


    private ArrayList<UserDAO> list = new ArrayList<>();


    public String getUsers() {
        // Use prepared statements here to get data from PostgreSQL database
        String sql = "SELECT DISTINCT css.users.\"id\" AS userid, css.users.\"name\" AS username FROM css.users;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                UserDAO userDummy = new UserDAO();

                userDummy.setUserId(resultSet.getLong("userid"));
                userDummy.setUserName(resultSet.getString("username"));

                if (!list.contains(userDummy)) {
                    list.add(userDummy);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        //return null;

        return list.toString();
    }

    public String getUser(long id) {
        String sql = "SELECT css.users.\"name\" AS username FROM css.users WHERE WHERE css.users.id=" + id + ";";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDAO userDummy = new UserDAO();

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                userDummy.setUserId(id);
                userDummy.setUserName(resultSet.getString("username"));

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return userDummy.getUserName();
    }

    public long getUser(String username) {
        String sql = "SELECT css.users.id AS userid FROM css.users WHERE css.users.name LIKE '" + username + "';";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserDAO userDummy = new UserDAO();

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                userDummy.setUserId(resultSet.getLong("userid"));
                userDummy.setUserName(username);

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        return userDummy.getUserId();
    }


}
