package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Drinks;
import com.pluralsight.data.DrinksDao;
import com.pluralsight.models.addedExtras.Drink;
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
public class MyDrinksDao extends MySqlDaoBase implements DrinksDao
{
    @Autowired
    public MyDrinksDao(DataSource dataSource)
    {
        super(dataSource);
    }


    @Override
    public List<Drinks> getAllDrinks()
    {
       List<Drinks> drinks = new ArrayList<>();

       try(Connection connection = getConnection())
       {
           String sql = """
                   SELECT drink_id
                   	,drink_type
                   FROM drinks;
                   """;

           Statement statement = connection.createStatement();
           ResultSet row = statement.executeQuery(sql);

           while (row.next())
           {
               int drinkId = row.getInt("drink_id");
               String drinkName = row.getString("drink_type");

               drinks.add(new Drinks(drinkId, drinkName));
           }
       }catch (Exception e)
       {
           System.out.println(e);
       }
         return drinks;
    }

    @Override
    public Drinks getById(int drinkId)
    {
      try(Connection connection = getConnection())
      {
          String sql = """
                  SELECT drink_id
                  	,drink_type
                  FROM drinks
                  WHERE drink_id = ?;
                  """;

          PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,drinkId);

            ResultSet row = statement.executeQuery();

            if(row.next())
            {
                int drinkId1 = row.getInt("drink_id");
                String drinkName = row.getString("drink_type");

                return new Drinks(drinkId1, drinkName);
            }
      }catch (Exception e)
      {
          System.out.println(e);
      }
        return null;
    }

    @Override
    public Drinks create(Drinks drink) {
        return null;
    }

    @Override
    public void update(int drinkId, Drinks drink) {

    }

    @Override
    public void delete(int drinkId) {

    }
}
