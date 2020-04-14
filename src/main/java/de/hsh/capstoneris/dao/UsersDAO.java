package de.hsh.capstoneris.dao;

import de.hsh.capstoneris.sql.Connection;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO extends Connection{


    private ArrayList<UserDAO> list = new ArrayList<>();


    public String getUsers() {
        // Use prepared statements here to get data from PostgreSQL database
        String sql = "SELECT DISTINCT css.users.\"id\" AS userid, css.users.\"name\" AS username FROM css.users;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = setupPreparedStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {

              UserDAO userDummy = new UserDAO();

              userDummy.setUserId(resultSet.getLong("userid"));
              userDummy.setUserName(resultSet.getString("username"));

              if(!list.contains(userDummy)){
                  list.add(userDummy);
              }

                //System.out.println(list.toString());

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnections(resultSet, preparedStatement);
        }
        //return null;

        return list.toString();
    }


}
