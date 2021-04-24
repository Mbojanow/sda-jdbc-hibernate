package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306");
    dataSource.setUser("root");
    dataSource.setPassword("example");

    try (final Connection connection = dataSource.getConnection()) {

    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

}
