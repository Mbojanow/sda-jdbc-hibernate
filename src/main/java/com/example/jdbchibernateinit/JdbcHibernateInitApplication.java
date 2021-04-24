package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    try (Statement statement = connection.createStatement()) {
      return statement.executeQuery("SELECT * FROM users u WHERE u.uname = '" + name + "' AND u.email = '" + email +"'").next();
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
    return false;
  }

  private static void createUser(Connection connection, String name, String email, String password) {
    try (Statement statement = connection.createStatement()) {
      int res = statement.executeUpdate("INSERT INTO users(uname, email, password) VALUES ('" + name + "','" + email + "','" + password + "')");
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
