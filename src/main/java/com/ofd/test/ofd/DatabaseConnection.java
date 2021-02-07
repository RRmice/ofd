package com.ofd.test.ofd;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Objects;

@Component
public class DatabaseConnection {

    private static Connection connection;

    public DatabaseConnection() {

        if (!Objects.isNull(connection)){return;}

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDB.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public void setAutoCommit(boolean bool)  {
        try {
            connection.setAutoCommit(bool);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}