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
      printAllUsers(connection);

    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

  private static void createUsersTableIfNotExists(Connection connection) {
    try (Statement statement = connection.createStatement()) {
      boolean execute = statement.execute("CREATE TABLE IF NOT EXISTS users(" +
          "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
          "name VARCHAR(255) NOT NULL," +
          "email VARCHAR(127) NOT NULL," +
          "password VARCHAR(100) NOT NULL" +
          ")");
      System.out.println("Table users created");
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
