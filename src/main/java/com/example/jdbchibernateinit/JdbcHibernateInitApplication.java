package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/sda_users");
    dataSource.setUser("root");
    dataSource.setPassword("example");

    try (final Connection connection = dataSource.getConnection()) {
      createUsersTableIfNotExists(connection);
      createUserIfNotExists(connection, "Michal", "test@test.com", "Password_123");
      printAllUsers(connection);
      printAllUsersWithName(connection, "Michal");

    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

  private static void printAllUsersWithName(final Connection connection, final String name) {
    try (CallableStatement statement = connection.prepareCall("CALL GetUserByName(?)")) {
      statement.setString(1, name);
      boolean hasResultSet = statement.execute();
      if (hasResultSet) {
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
          int id = resultSet.getInt(1);
          String fetchedName = resultSet.getString(2);
          String email = resultSet.getString(3);
          String password = resultSet.getString(4);
          System.out.println("id=" + id + ", name=" + fetchedName + ", email=" + email + ", password=" + password);
        }
      }
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

  private static void createUsersTableIfNotExists(Connection connection) {
    try (Statement statement = connection.createStatement()) {
      boolean execute = statement.execute("CREATE TABLE IF NOT EXISTS users(" +
          "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
          "uname VARCHAR(255) NOT NULL," +
          "email VARCHAR(127) NOT NULL," +
          "password VARCHAR(100) NOT NULL" +
          ")");
      System.out.println("Table users created");
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

  private static void createUserIfNotExists(Connection connection, String name, String email, String password) {
    if (!userExists(connection, name, email)) {
      createUser(connection, name, email, password);
    }
  }

  private static boolean userExists(Connection connection, String name, String email) {
    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users u WHERE u.uname = ? AND u.email = ?")) {
      statement.setString(1, name);
      statement.setString(2, email);
      return statement.executeQuery().next();
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
    return false;
  }

  private static void createUser(Connection connection, String name, String email, String password) {
    try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users(uname, email, password) VALUES (?, ?, ?)")) {
      statement.setString(1, name);
      statement.setString(2, email);
      statement.setString(3, password);
      int res = statement.executeUpdate();
      System.out.println("INSERT RESULT " + res);
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

  private static void printAllUsers(Connection connection) {
    try (Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
      while (resultSet.next()) {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String email = resultSet.getString(3);
        String password = resultSet.getString(4);
        System.out.println("id=" + id + ", name=" + name + ", email=" + email + ", password=" + password);
      }
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

}
