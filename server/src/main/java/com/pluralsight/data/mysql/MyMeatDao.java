package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Meat;
import com.pluralsight.data.MeatDao;
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
public class MyMeatDao extends MySqlDaoBase implements MeatDao
{
    @Autowired
    public MyMeatDao(DataSource dataSource)
    {
        super(dataSource);
    }


    @Override
    public List<Meat> getAllMeats()
    {
       List<Meat> meats = new ArrayList<>();

       try(Connection connection = getConnection())
       {
           String sql = """
                   SELECT meat_id
                   	,meat_type
                       ,extra_meat
                   FROM meats;
                   """;

              Statement statement = connection.createStatement();
              ResultSet row = statement.executeQuery(sql);

              while (row.next())
              {
                  int meatId = row.getInt("meat_id");
                  String meatName = row.getString("meat_type");
                  boolean extraMeat = row.getBoolean("extra_meat");

                  meats.add(new Meat(meatId, meatName, extraMeat));
              }
       }catch (Exception e)
       {
           System.out.println(e);
       }
         return meats;
    }

    @Override
    public Meat getById(int meatId)
    {
         try(Connection connection = getConnection())
         {
              String sql = """
                     SELECT meat_id
                     	,meat_type
                     	,extra_meat
                     FROM meats
                     WHERE meat_id = ?;
                     """;

              PreparedStatement statement = connection.prepareStatement(sql);
              statement.setInt(1, meatId);
              ResultSet row = statement.executeQuery();

              if(row.next())
              {
                String meatName = row.getString("meat_type");
                boolean extraMeat = row.getBoolean("extra_meat");

                return new Meat(meatId, meatName, extraMeat);
              }
         }catch (Exception e)
         {
              System.out.println(e);
         }
            return null;
    }

    @Override
    public Meat create(Meat meat) {
        return null;
    }

    @Override
    public void update(int meatId, Meat meat) {

    }

    @Override
    public void delete(int meatId) {

    }
}
