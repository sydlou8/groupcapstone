package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Bread;
import com.pluralsight.data.BreadDao;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyBreadDao extends MySqlDaoBase implements BreadDao
{
    public MyBreadDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Bread> getAllBreads()
    {
        List<Bread> breads = new ArrayList<>();

        try (Connection connection = getConnection())
        {
            String sql = """
                    SELECT bread_id
                    	,bread_type
                        ,bread_size
                    FROM sandwich_shop.breads;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next()) {
                int breadId = row.getInt("bread_id");
                String breadName = row.getString("bread_type");
                String breadDescription = row.getString("bread_size");

                breads.add(new Bread(breadId, breadName, breadDescription));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return breads;
    }

    @Override
    public Bread getById(int breadId)
    {
        try (Connection connection = getConnection())
        {
            String sql = """
                    SELECT bread_id
                    	,bread_type
                        ,bread_size
                    FROM breads
                    WHERE bread_id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, breadId);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return mapRow(row);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Bread create(Bread bread) {
        return null;
    }

    @Override
    public void update(int breadId, Bread bread) {

    }

    @Override
    public void delete(int breadId) {

    }

    private Bread mapRow(ResultSet row) throws Exception
    {
        int breadId = row.getInt("bread_id");
        String breadName = row.getString("bread_type");
        String breadDescription = row.getString("bread_size");

        return new Bread(breadId, breadName, breadDescription);
    }
}