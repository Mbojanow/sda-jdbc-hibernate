package com.example.jdbchibernateinit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHibernateInitApplication {

  public static void main(String[] args) {

    try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/information_schema",
            "root", "example")) {

    } catch (SQLException exp) {
      exp.printStackTrace();
    }
  }

}
