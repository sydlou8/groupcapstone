package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.RegularToppings;
import com.pluralsight.data.RegularToppingsDao;
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
public class MyRegularToppingsDao extends MySqlDaoBase implements RegularToppingsDao
{
    @Autowired
    public MyRegularToppingsDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<RegularToppings> getAllRegularToppings()
    {
        List<RegularToppings> regularToppings = new ArrayList<>();

        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT topping_id
                    	,topping_type
                    FROM regular_toppings;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next())
            {
                int toppingId = row.getInt("topping_id");
                String toppingType = row.getString("topping_type");

                regularToppings.add(new RegularToppings(toppingId, toppingType));
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }return regularToppings;
    }

    @Override
    public RegularToppings getById(int regularToppingsId)
    {
      try(Connection connection = getConnection())
            {
                String sql = """
                        SELECT topping_id
                        	,topping_type
                        FROM regular_toppings
                        WHERE topping_id = ?;
                        """;

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, regularToppingsId);

                ResultSet row = statement.executeQuery();

                if(row.next())
                {
                    int toppingId = row.getInt("topping_id");
                    String toppingType = row.getString("topping_type");

                    return new RegularToppings(toppingId, toppingType);
                }
            }catch (Exception e)
            {
                System.out.println(e);
            }return null;
    }

    @Override
    public RegularToppings create(RegularToppings regularToppings) {
        return null;
    }

    @Override
    public void update(int regularToppingsId, RegularToppings regularToppings) {

    }

    @Override
    public void delete(int regularToppingsId) {

    }

}