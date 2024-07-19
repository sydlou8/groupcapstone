package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Order;
import com.pluralsight.SandwichModels.Sandwich;
import com.pluralsight.SandwichModels.Chips;
import com.pluralsight.SandwichModels.Drinks;
import com.pluralsight.data.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Component
public class MyOrderDao extends MySqlDaoBase implements OrderDao {

    @Autowired
    public MyOrderDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String orderSql = "SELECT * FROM orders";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(orderSql);

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderPrice(rs.getDouble("order_price"));

                // Populate sandwiches
                String sandwichSql = "SELECT s.* FROM sandwich_order so JOIN sandwiches s ON so.sandwich_id = s.sandwich_id WHERE so.order_id = ?";
                PreparedStatement sandwichStmt = connection.prepareStatement(sandwichSql);
                sandwichStmt.setInt(1, order.getOrderId());
                ResultSet sandwichRs = sandwichStmt.executeQuery();
                List<Sandwich> sandwiches = new ArrayList<>();
                while (sandwichRs.next()) {
                    Sandwich sandwich = new Sandwich();
                    sandwich.setSandwichId(sandwichRs.getInt("sandwich_id"));
                    sandwich.setBreadId(sandwichRs.getInt("bread_id"));
                    sandwich.setMeatId(sandwichRs.getInt("meat_id"));
                    sandwich.setCheeseId(sandwichRs.getInt("cheese_id"));
                    sandwich.setToppingId(sandwichRs.getInt("topping_id"));
                    sandwich.setSauceId(sandwichRs.getInt("sauce_id"));
                    sandwich.setSideId(sandwichRs.getInt("side_id"));
                    sandwich.setPrice(sandwichRs.getDouble("sandwich_price"));
                    // Set other fields as necessary
                    sandwiches.add(sandwich);
                }
                order.setSandwiches(sandwiches);

                // Populate chips
                String chipsSql = "SELECT c.* FROM chips_order co JOIN chips c ON co.chip_id = c.chip_id WHERE co.order_id = ?";
                PreparedStatement chipsStmt = connection.prepareStatement(chipsSql);
                chipsStmt.setInt(1, order.getOrderId());
                ResultSet chipsRs = chipsStmt.executeQuery();
                List<Chips> chipsList = new ArrayList<>();
                while (chipsRs.next()) {
                    Chips chips = new Chips();
                    chips.setChipsId(chipsRs.getInt("chip_id"));
                    chips.setChipType(chipsRs.getString("chip_type")); // Update to chipsType
                    chips.setChipsPrice(chipsRs.getDouble("chip_price"));
                    // Set other fields as necessary
                    chipsList.add(chips);
                }
                order.setChips(chipsList);

                // Populate drinks
                String drinksSql = "SELECT d.* FROM drink_order do JOIN drinks d ON do.drink_id = d.drink_id WHERE do.order_id = ?";
                PreparedStatement drinksStmt = connection.prepareStatement(drinksSql);
                drinksStmt.setInt(1, order.getOrderId());
                ResultSet drinksRs = drinksStmt.executeQuery();
                List<Drinks> drinksList = new ArrayList<>();
                while (drinksRs.next()) {
                    Drinks drink = new Drinks();
                    drink.setDrinkId(drinksRs.getInt("drink_id"));
                    drink.setDrinkType(drinksRs.getString("drink_type")); // Update to drinkType
                    drink.setDrinkPrice(drinksRs.getDouble("drink_price"));
                    // Set other fields as necessary
                    drinksList.add(drink);
                }
                order.setDrinks(drinksList);

                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Use e.printStackTrace() for detailed error logs
        }
        return orders;
    }


    @Override
    public Order create(Order order) {
        double totalOrderPrice = order.getSandwiches().stream().mapToDouble(Sandwich::getPrice).sum()
            + order.getChips().stream().mapToDouble(Chips::getChipsPrice).sum()
            + order.getDrinks().stream().mapToDouble(Drinks::getDrinkPrice).sum();
        try (Connection connection = getConnection()) {
            String orderSql = "INSERT INTO orders (order_price) VALUES (?)";
            PreparedStatement orderStmt = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setDouble(1, totalOrderPrice);
            orderStmt.executeUpdate();

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                for (Sandwich sandwich : order.getSandwiches()) {
                    // Verify sandwich_id exists in sandwiches table
                    String verifySandwichSql = "SELECT 1 FROM sandwiches WHERE sandwich_id = ?";
                    PreparedStatement verifySandwichStmt = connection.prepareStatement(verifySandwichSql);
                    verifySandwichStmt.setInt(1, sandwich.getSandwichId());
                    ResultSet rs = verifySandwichStmt.executeQuery();
                    if (rs.next()) {
                        String sandwichSql = "INSERT INTO sandwich_order (order_id, sandwich_id) VALUES (?, ?)";
                        PreparedStatement sandwichStmt = connection.prepareStatement(sandwichSql);
                        sandwichStmt.setInt(1, orderId);
                        sandwichStmt.setInt(2, sandwich.getSandwichId());
                        sandwichStmt.executeUpdate();
                    } else {
                        System.out.println("Invalid sandwich_id: " + sandwich.getSandwichId());
                    }
                }

                for (Chips chips : order.getChips()) {
                    String chipsSql = "INSERT INTO chips_order (order_id, chip_id) VALUES (?, ?)";
                    PreparedStatement chipsStmt = connection.prepareStatement(chipsSql);
                    chipsStmt.setInt(1, orderId);
                    chipsStmt.setInt(2, chips.getChipsId());
                    chipsStmt.executeUpdate();
                }

                for (Drinks drink : order.getDrinks()) {
                    String drinksSql = "INSERT INTO drink_order (order_id, drink_id) VALUES (?, ?)";
                    PreparedStatement drinksStmt = connection.prepareStatement(drinksSql);
                    drinksStmt.setInt(1, orderId);
                    drinksStmt.setInt(2, drink.getDrinkId());
                    drinksStmt.executeUpdate();
                }

                order.setOrderId(orderId);
                order.setOrderPrice(totalOrderPrice);
                return order;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}