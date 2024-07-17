package com.pluralsight.data.mysql;

import com.pluralsight.SandwichModels.Chips;
import com.pluralsight.data.ChipsDao;
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
public class MyChipsDao extends MySqlDaoBase implements ChipsDao
{
    @Autowired
    public MyChipsDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Chips> getAllChips()
    {
        List<Chips> chips = new ArrayList<>();

        try (Connection connection = getConnection())
        {
            String sql = """
                    SELECT chip_id
                    	,chip_type
                    FROM chips;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next())
            {
                int chipsId = row.getInt("chip_id");
                String chipsName = row.getString("chip_type");

                chips.add(new Chips(chipsId, chipsName));
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return chips;
    }

    @Override
    public Chips getById(int chipsId)
    {
        try(Connection connection = getConnection())
        {
            String sql = """
                    SELECT chip_id
                    	,chip_type
                    FROM chips
                    WHERE chip_id = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, chipsId);

            ResultSet row = statement.executeQuery();

            if (row.next())
            {
                return new Chips(row.getInt("chips_id"), row.getString("chip_type"));
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Chips create(Chips chips) {
        return null;
    }

    @Override
    public void update(int chipsId, Chips chips) {

    }

    @Override
    public void delete(int chipsId) {

    }


}
