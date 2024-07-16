package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Sandwich;
import com.pluralsight.data.SandwichDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
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
                        side_id
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

                sandwiches.add(new Sandwich(sandwichId, breadId, meatId, cheeseId, toppingId, sauceId, sideId));
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
                    FROM sandwiches
                    WHERE sandwich_id = ?;
                    """;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Sandwich create(Sandwich sandwich) {
        return null;
    }

    @Override
    public void update(int sandwichId, Sandwich sandwich) {

    }

    @Override
    public void delete(int sandwichId) {

    }


}
