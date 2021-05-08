package com.example.jdbchibernateinit;

import java.sql.*;
import java.util.Optional;

// klasa reprezentująca możliwość wykonywania zapytań na tabeli address (+ jej stworzenie)
public class AddressRepository {

    private final Connection connection;

    public AddressRepository(Connection connection) {
        this.connection = connection;
    }

    public void createAddressTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS addresses (" +
                    "id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    "street_name VARCHAR(255), " +
                    "building_number INTEGER," +
                    "house_number INTEGER," +
                    "is_primary BOOLEAN, " +
                    "zip_code VARCHAR(6), " +
                    "country VARCHAR(100), " +
                    "city VARCHAR(100)," +
                    "user_id INTEGER," +
                    "FOREIGN KEY (user_id) REFERENCES users(id))");
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public Optional<Address> findById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM addresses a WHERE a.id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(toAddress(resultSet));
            }
            return Optional.empty();
        } catch (SQLException exp) {
            return Optional.empty();
        }
    }

    public void createAddress(Address address) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO addresses(street_name, building_number, house_number, is_primary, zip_code, country, city, user_id)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)"
        )) {
            // ustawić ? przy pomocy setX
            // exeucuteUpdate()
        } catch (SQLException exp) {
            throw new RuntimeException(exp);
        }
    }

    private Address toAddress(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt(1);
        String streetName = resultSet.getString(2);
        Integer buildingNumber = resultSet.getInt(3);
        Integer houseNumber = resultSet.getInt(4);
        Boolean isPrimary = resultSet.getBoolean(5);
        String zipCode = resultSet.getString(6);
        String country = resultSet.getString(7);
        String city = resultSet.getString(8);
        Integer userId = resultSet.getInt(9);
        // ewentualnie przepisać na builder
        return new Address(id, streetName, buildingNumber, houseNumber, isPrimary, zipCode, country, city, userId);
    }
}
