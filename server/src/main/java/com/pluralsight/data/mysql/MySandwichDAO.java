package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Sandwich;
import com.pluralsight.data.SandwichDao;
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
public class MySandwichDAO extends MySqlDaoBase implements SandwichDao
{
    @Autowired
    public MySandwichDAO(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Sandwich> getAllSandwiches()
    {
        List<Sandwich> sandwiches = new ArrayList<>();

        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT sandwich_id
                    	,bread_id
                        ,meat_id
                        ,cheese_id
                        ,topping_id
                        ,sauce_id
                        ,side_id
                        ,sandwich_price
                    FROM sandwiches;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next())
            {
                int sandwichId = row.getInt("sandwich_id");
                int breadId = row.getInt("bread_id");
                int meatId = row.getInt("meat_id");
                int cheeseId = row.getInt("cheese_id");
                int toppingId = row.getInt("topping_id");
                int sauceId = row.getInt("sauce_id");
                int sideId = row.getInt("side_id");
                double price = row.getDouble("sandwich_price");

                sandwiches.add(new Sandwich(sandwichId, breadId, meatId, cheeseId, toppingId, sauceId, sideId, price ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return sandwiches;
    }

    @Override
    public Sandwich getById(int sandwichId)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT sandwich_id
                    	,bread_id
                        ,meat_id
                        ,cheese_id
                        ,topping_id
                        ,sauce_id
                        ,side_id
                        ,sandwich_price
                    FROM sandwiches
                    WHERE sandwich_id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, sandwichId);

            ResultSet row = statement.executeQuery();

            if(row.next())
            {
                int breadId = row.getInt("bread_id");
                int meatId = row.getInt("meat_id");
                int cheeseId = row.getInt("cheese_id");
                int toppingId = row.getInt("topping_id");
                int sauceId = row.getInt("sauce_id");
                int sideId = row.getInt("side_id");
                double price = row.getDouble("sandwich_price");

                return new Sandwich(sandwichId, breadId, meatId, cheeseId, toppingId, sauceId, sideId, price);
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Sandwich create(Sandwich sandwich) {
        try (Connection connection = getConnection()) {
            String sql = """
                INSERT INTO sandwiches (bread_id, meat_id, cheese_id, topping_id, sauce_id, side_id, sandwich_price)
                VALUES (?, ?, ?, ?, ?, ?, ?);
                """;

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sandwich.getBreadId());
            statement.setInt(2, sandwich.getMeatId());
            statement.setInt(3, sandwich.getCheeseId());
            statement.setInt(4, sandwich.getToppingId());
            statement.setInt(5, sandwich.getSauceId());
            statement.setInt(6, sandwich.getSideId());
            statement.setDouble(7, sandwich.getPrice());

            System.out.println("Inserting sandwich with bread_id: " + sandwich.getBreadId() +
                    ", meat_id: " + sandwich.getMeatId() +
                    ", cheese_id: " + sandwich.getCheeseId() +
                    ", topping_id: " + sandwich.getToppingId() +
                    ", sauce_id: " + sandwich.getSauceId() +
                    ", side_id: " + sandwich.getSideId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int sandwichId = generatedKeys.getInt(1);
                    sandwich.setSandwichId(sandwichId);
                    return sandwich;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @Override
    public void update(int sandwichId, Sandwich sandwich) {

    }

    @Override
    public void delete(int sandwichId) {

    }


}
