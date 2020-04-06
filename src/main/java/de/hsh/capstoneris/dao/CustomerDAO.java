package de.hsh.capstoneris.dao;

import de.hsh.capstoneris.sql.Connection;

//import java.sql.*;

public class CustomerDAO extends Connection {

    public String getCustomer() {
        // Use prepared statements here to get data from PostgreSQL database
//        String sql = "SELECT * FROM customer LIMIT 100";
//
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//
//        try {
//            preparedStatement = setupPreparedStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//
//            String customerName = resultSet.getString("customer_name");
//
//            return customerName;
//
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            closeConnections(resultSet, preparedStatement);
//        }
//        return null;
        return "A customer!";
    }

}
