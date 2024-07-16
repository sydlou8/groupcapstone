package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Orders;
import com.pluralsight.data.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyOrderDao extends MySqlDaoBase implements OrderDao
{
    @Autowired
    public MyOrderDao(DataSource dataSource){super(dataSource);}

    @Override
    public List<Orders> getAllOrders() {

        List<Orders> orders = new ArrayList<>();

        try (Connection connection = getConnection()) {
            String sql = """
                    SELECT order_id, sandwich_id, drink_id, order_date, size, price
                    FROM orders
                    """;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                int sandwichId = resultSet.getInt("sandwich_id");
                int drinkId = resultSet.getInt("drink_id");
                LocalDate orderDate = resultSet.getDate("order_date").toLocalDate();
                int size = resultSet.getInt("size");
                double price = resultSet.getDouble("price");

                Orders order = new Orders(orderId, sandwichId, drinkId, orderDate, size, price);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Orders getById(int orderId)
    {
        Orders order = null;

        try (Connection connection = getConnection()) {
            String sql = """
                    SELECT order_id,
                    sandwich_id,
                    drink_id,
                    order_date,
                    size,
                    price
                    FROM orders
                    WHERE order_id = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int sandwichId = resultSet.getInt("sandwich_id");
                int drinkId = resultSet.getInt("drink_id");
                LocalDate orderDate = resultSet.getDate("order_date").toLocalDate();
                int size = resultSet.getInt("size");
                double price = resultSet.getDouble("price");

                order = new Orders(orderId, sandwichId, drinkId, orderDate, size, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public Orders create(Orders order)
    {
        try (Connection connection = getConnection()) {
            String sql = """
                    INSERT INTO orders (sandwich_id, drink_id, order_date, size, price)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getSandwichId());
            statement.setInt(2, order.getDrinkId());
            statement.setDate(3, Date.valueOf(order.getOrderDate()));
            statement.setInt(4, order.getSize());
            statement.setDouble(5, order.getPrice());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                order.setOrderId(orderId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public void update(int orderId, Orders order)
    {
        try (Connection connection = getConnection()) {
            String sql = """
                    UPDATE orders
                    SET sandwich_id = ?, drink_id = ?, order_date = ?, size = ?, price = ?
                    WHERE order_id = ?
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getSandwichId());
            statement.setInt(2, order.getDrinkId());
            statement.setDate(3, Date.valueOf(order.getOrderDate()));
            statement.setInt(4, order.getSize());
            statement.setDouble(5, order.getPrice());
            statement.setInt(6, order.getOrderId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int orderId)
    {

    }

}
