package com.pluralsight.data;

import com.pluralsight.SandwichModels.Meat;

import java.util.List;

public interface MeatDao
{
    List<Meat> getAllMeats();
    Meat getById(int meatId);
    Meat create(Meat meat);
    void update(int meatId, Meat meat);
    void delete(int meatId);
}
