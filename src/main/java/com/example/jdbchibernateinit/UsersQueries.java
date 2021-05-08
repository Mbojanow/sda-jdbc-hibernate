package com.example.jdbchibernateinit;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

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
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM users WHERE email=? AND name=?"
             );
             PreparedStatement insertStatement = connection.prepareStatement(
                     "INSERT INTO users(email, name, password) VALUES (?, ?, ?)"
             )) {
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE email='"
//                    + email + "' AND name='" + name + "'");
            statement.setString(1, email);
            statement.setString(2, name);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return false;
            }

            insertStatement.setString(1, email);
            insertStatement.setString(2, name);
            insertStatement.setString(3, password);
            insertStatement.executeUpdate();
//            statement.executeUpdate("INSERT INTO users(email, name, password) VALUES('"
//                    + email + "','" + name + "','" + password +"')");
            return true;
        } catch (SQLException exp) {
            exp.printStackTrace();
            return false;
        }
    }

    // fix syntax
    public void createSearchUserByNameProcedure() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("DELIMITER //\n" +
                    "CREATE PROCEDURE GetUsersByName(IN name_param VARCHAR(255)) " +
                    "BEGIN\n" +
                    "SELECT * FROM users WHERE name = name_param\n" +
                    "END //\n" +
                    "DELIMITER ;");
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public void printUsersWithName(String name) {
        try (Connection connection = dataSource.getConnection();
        CallableStatement callableStatement = connection.prepareCall("CALL GetUserByName(?)")) {
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String email = resultSet.getString(3);
                String password = resultSet.getString(4);
                System.out.println("id=" + id + ", name=" + userName + " email=" + email + ", password=" + password);
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

}
