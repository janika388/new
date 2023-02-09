package com.example.vizsga_02_10_23_backend;

import java.sql.*;

public class DbConnect {
    final String connectionUrl = "jdbc:mysql://localhost:8080/myexam?serverTimezone=UTC";

    Connection dbConnection;
    Statement dbStatement;

    DbConnect(String connectionAddress, String username, String password) throws SQLException  {
        this.dbConnection = DriverManager.getConnection(connectionAddress, username, password);
        this.dbStatement = this.dbConnection.createStatement();
    }

    public ResultSet sendQuery(String queryStatement) throws SQLException {
        return this.dbStatement.executeQuery(queryStatement);
    }
}
