package com.pluralsight.data.mysql;

import com.pluralsight.data.SaucesDao;
import com.pluralsight.SandwichModels.Sauces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySaucesDao extends MySqlDaoBase implements SaucesDao
{
    @Autowired
    public MySaucesDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Sauces> getAllSauces()
    {
        List<Sauces> sauces = new ArrayList<>();

        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT sauce_id
                    	,sauce_type
                    FROM sauces;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next())
            {
                int sauceId = row.getInt("sauce_id");
                String sauceType = row.getString("sauce_type");

                sauces.add(new Sauces(sauceId, sauceType));
            }

        }catch (Exception e)
        {
            System.out.println(e);
        }
        return sauces;
    }

    @Override
    public Sauces getById(int sauceId)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT sauce_id
                    	,sauce_type
                    FROM sauces
                    WHERE sauce_id = ?;
                    """;
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Sauces create(Sauces sauce) {
        return null;
    }

    @Override
    public void update(int sauceId, Sauces sauce) {

    }

    @Override
    public void delete(int sauceId) {

    }
}
