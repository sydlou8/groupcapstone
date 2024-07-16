package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Cheese;
import com.pluralsight.data.CheeseDao;
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
public class MyCheeseDao extends MySqlDaoBase implements CheeseDao {
    @Autowired
    public MyCheeseDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Cheese> getAllCheeses()
    {
        List<Cheese> cheeses = new ArrayList<>();

        try (Connection connection = getConnection())
        {
            String sql = """
                    SELECT cheese_id
                    	,cheese_type
                    	,extra_cheese
                    FROM cheese;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next()) {
                int cheeseId = row.getInt("cheese_id");
                String cheeseName = row.getString("cheese_type");
                boolean extraCheese = row.getBoolean("extra_cheese");

                cheeses.add(new Cheese(cheeseId, cheeseName, extraCheese));
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return cheeses;
    }

    @Override
    public Cheese getById(int cheeseId)
    {
       try(Connection connection = getConnection())
       {
           String sql = """
                   SELECT cheese_id
                   	,cheese_type
                   	,extra_cheese
                   FROM cheese
                   WHERE cheese_id = ?;
                   """;

           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setInt(1,cheeseId);

           ResultSet row = statement.executeQuery();

              if(row.next())
              {
                int cheeseId1 = row.getInt("cheese_id");
                String cheeseName = row.getString("cheese_type");
                boolean extraCheese = row.getBoolean("extra_cheese");
                return new Cheese(cheeseId1, cheeseName, extraCheese);
              }

       }catch (Exception e)
       {
           System.out.println(e);
       }
       return null;
    }


    @Override
    public Cheese create(Cheese cheese) {
        return null;
    }

    @Override
    public void update(int cheeseId, Cheese cheese) {

    }

    @Override
    public void delete(int cheeseId) {

    }

}

