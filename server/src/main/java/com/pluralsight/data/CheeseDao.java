package com.pluralsight.data;

import com.pluralsight.SandwichModels.Cheese;

import java.util.List;

public interface CheeseDao
{
    List<Cheese> getAllCheeses();
    Cheese getById(int cheeseId);
    Cheese create(Cheese cheese);
    void update(int cheeseId, Cheese cheese);
    void delete(int cheeseId);
}

