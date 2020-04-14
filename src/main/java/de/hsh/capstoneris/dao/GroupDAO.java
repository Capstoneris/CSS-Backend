package de.hsh.capstoneris.dao;

import de.hsh.capstoneris.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;


public class GroupDAO extends Connection {

    private ArrayList<UserDAO> users = new ArrayList<>();


    public String getUsersInMyGroup() {
        // Use prepared statements here to get data from PostgreSQL database
        //
        long userId = 1;
        String sql = "SELECT distinct id AS userid, name AS username from css.users join css.users_in_groups ON (css.users.id = css.users_in_groups.user) WHERE css.users_in_groups.user IN ( SELECT css.users_in_groups.\"group\" FROM css.users_in_groups WHERE users_in_groups.user = "+ userId +");";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                UserDAO userDummy = new UserDAO();

                userDummy.setUserId(resultSet.getLong("userid"));
                userDummy.setUserName(resultSet.getString("username"));

                if(!users.contains(userDummy)){
                    users.add(userDummy);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        //return null;

        return users.toString();
    }
}