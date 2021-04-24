package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/sda_users");
    dataSource.setUser("root");
    dataSource.setPassword("example");

    try (final Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {
      boolean execute = statement.execute("CREATE TABLE IF NOT EXISTS users(" +
          "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
          "name VARCHAR(255) NOT NULL," +
          "email VARCHAR(127) NOT NULL," +
          "password VARCHAR(100) NOT NULL" +
          ")");
      System.out.println(execute);
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

}
