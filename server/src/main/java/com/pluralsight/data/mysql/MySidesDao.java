package com.pluralsight.data.mysql;

import com.pluralsight.data.SidesDao;
import com.pluralsight.models.toppings.Side;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Sides;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySidesDao extends MySqlDaoBase implements SidesDao
{
    public MySidesDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Side> getAllSides()
    {
      List<Side> sides = new ArrayList<>();

      try(Connection connection = getConnection())
      {
          String sql = """
                  SELECT side_id
                  	,side_type
                  FROM sides;
                  """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while(row.next())
            {
                int sideId = row.getInt("side_id");
                String sideType = row.getString("side_type");

                sides.add(new Side(sideId, sideType));
            }
      }catch (Exception e)
      {
          System.out.println(e);
      }return sides;
    }

    @Override
    public Side getById(int sideId)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT side_id
                    	,side_type
                    FROM sides
                    WHERE side_id = ?;
                    """;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Side create(Side side) {
        return null;
    }

    @Override
    public void update(int sideId, Side side) {

    }

    @Override
    public void delete(int sideId) {

    }
}
