package com.demo.eventmanagement.db.connector;


import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory {
  private static java.sql.Connection sqlConnection;

  public static java.sql.Connection getConnection() {
    //TODO pull from properties
    String driver = "com.mysql.jdbc.Driver";
    String dbName = "hackathon2020";
    String dbURL = "jdbc:mysql://localhost:3306/" + dbName;
    String username = "root";
    String password = "root";

    if (sqlConnection != null) return sqlConnection;

    try {
      Class.forName(driver);
      sqlConnection = DriverManager.getConnection(dbURL, username, password);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    return sqlConnection;
  }

  //TODO use it
  public static void closeConnection(java.sql.Connection sqlConnection) {
    try {
      sqlConnection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
