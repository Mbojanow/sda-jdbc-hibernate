package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
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

    public void printAllUsers() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                System.out.println("id=" + id + ", name=" + name + "email=" + email + ", password=" + password);
            }

        } catch (SQLException exp) {
            exp.printStackTrace();
        }

    }

    public boolean createUserIfNotExists(String name, String email, String password) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email='"
                    + email + "' AND name='" + name + "'");
            if (resultSet.next()) {
                return false;
            }

            statement.executeUpdate("INSERT INTO users(email, name, password) VALUES('"
                    + email + "','" + name + "','" + password +"')");
            return true;
        } catch (SQLException exp) {
            exp.printStackTrace();
            return false;
        }
    }
}
