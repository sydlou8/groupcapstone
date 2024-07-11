package com.pluralsight.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDeliDao implements DeliDao {
    private static final String URL = "jdbc:mysql://localhost:3306/your_database";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }

    @Override
    public List<Deli> getAllDelis() {
        List<Deli> delis = new ArrayList<>();
        String sql = "SELECT * FROM delis";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Deli deli = new Deli();
                deli.setId(resultSet.getInt("id"));
                deli.setName(resultSet.getString("name"));
                deli.setDescription(resultSet.getString("description"));
                delis.add(deli);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all delis: " + e.getMessage());
        }

        return delis;
    }

    @Override
    public Deli getDeliById(int id) {
        Deli deli = null;
        String sql = "SELECT * FROM delis WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                deli = new Deli();
                deli.setId(resultSet.getInt("id"));
                deli.setName(resultSet.getString("name"));
                deli.setDescription(resultSet.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching deli by id: " + e.getMessage());
        }

        return deli;
    }

    @Override
    public void addDeli(Deli deli) {
        String sql = "INSERT INTO delis (name, description) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, deli.getName());
            statement.setString(2, deli.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding deli: " + e.getMessage());
        }
    }

    @Override
    public void updateDeli(Deli deli) {
        String sql = "UPDATE delis SET name = ?, description = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, deli.getName());
            statement.setString(2, deli.getDescription());
            statement.setInt(3, deli.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating deli: " + e.getMessage());
        }
    }

    @Override
    public void deleteDeli(int id) {
        String sql = "DELETE FROM delis WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting deli: " + e.getMessage());
        }
    }
}