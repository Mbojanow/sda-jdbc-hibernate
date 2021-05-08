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

    try (Connection connection = dataSource.getConnection()) {
      AddressRepository addressRepository = new AddressRepository(connection);
      addressRepository.createAddressTable();
    } catch (SQLException exp) {
      exp.printStackTrace();
    }
//    try(Connection connection = dataSource.getConnection()) {
//
//    } catch (SQLException exp) {
//      exp.printStackTrace();
//    }
  }

  private static void executeUsersQueries(MysqlDataSource dataSource) {
    UsersQueries usersQueries = new UsersQueries(dataSource);
    usersQueries.createUsersTableIfNotExists();
    usersQueries.createUserIfNotExists("Andrzej", "andrzej@test.com", "Andrzej_123");
    usersQueries.createUserIfNotExists("Ala", "ala@test.com", "Ala_123");

    usersQueries.createUserIfNotExists("Ala", "ala2@test.com", "Ala_123");
    usersQueries.createUserIfNotExists("Ala", "ala3@test.com", "Ala_123");
    //usersQueries.createSearchUserByNameProcedure();
    usersQueries.printAllUsers();
    usersQueries.printUsersWithName("Ala");

  }

}
