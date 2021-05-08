package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersQueries {

    private final MysqlDataSource dataSource;

    public UsersQueries(MysqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createUsersTableIfNotExists() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            boolean hasResultSet = statement.execute("CREATE TABLE IF NOT EXISTS users(" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "email VARCHAR(127) NOT NULL, " +
                    "password VARCHAR(100) NOT NULL" +
                    ")");
            System.out.println("has result set? " + hasResultSet);
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
}
