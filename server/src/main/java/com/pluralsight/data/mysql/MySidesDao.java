package com.pluralsight.data.mysql;

import com.pluralsight.data.SidesDao;
import com.pluralsight.SandwichModels.Sides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySidesDao extends MySqlDaoBase implements SidesDao
{
    @Autowired
    public MySidesDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Sides> getAllSides()
    {
      List<Sides> sides = new ArrayList<>();

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

                sides.add(new Sides(sideId, sideType));
            }
      }catch (Exception e)
      {
          System.out.println(e);
      }return sides;
    }

    @Override
    public Sides getById(int sideId)
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
    public Sides create(Sides side) {
        return null;
    }

    @Override
    public void update(int sideId, Sides side) {

    }

    @Override
    public void delete(int sideId) {

    }
}
