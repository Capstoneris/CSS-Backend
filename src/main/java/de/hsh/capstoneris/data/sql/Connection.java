package de.hsh.capstoneris.data.sql;

import java.io.IOException;
import java.security.Permissions;
import java.sql.*;
import java.util.List;

public class Connection {
    private static final String DB_CONN = "jdbc:postgresql://" + (
            (System.getenv("DATABASE_HOST") == null)
                    ? "localhost"
                    : System.getenv("DATABASE_HOST")) + ":5432/css-db";
    private static final String DB_USER = "css-db";
    private static final String DB_PW = "password";

    public Statement setupStatement() throws SQLException, IOException {
        java.sql.Connection connection = setUpCommonConnection();
        return connection.createStatement();
    }

    public PreparedStatement setupPreparedStatement(String sql) throws SQLException, IOException {
        java.sql.Connection connection = setUpCommonConnection();
        return connection.prepareStatement(sql);
    }

    public Array generateArray(List<Permissions> list) throws SQLException, IOException {
        java.sql.Connection connection = setUpCommonConnection();
        return connection.createArrayOf("varchar", list.toArray());
    }

    public void closeConnections(ResultSet rs, Statement ps) {
        java.sql.Connection conn = null;

        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                conn = ps.getConnection();
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private java.sql.Connection setUpCommonConnection() throws SQLException, IOException {
        return DriverManager.getConnection(DB_CONN, DB_USER, DB_PW);
    }
}
