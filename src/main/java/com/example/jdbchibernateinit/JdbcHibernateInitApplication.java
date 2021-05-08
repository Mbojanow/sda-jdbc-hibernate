package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {

    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3307/sda_users");
    dataSource.setUser("root");
    dataSource.setPassword("example");

    UsersQueries usersQueries = new UsersQueries(dataSource);
    usersQueries.createUsersTableIfNotExists();

//    try(Connection connection = dataSource.getConnection()) {
//
//    } catch (SQLException exp) {
//      exp.printStackTrace();
//    }
  }

}
