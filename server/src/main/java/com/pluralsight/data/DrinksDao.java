package com.pluralsight.data;

import com.pluralsight.SandwichModels.Drinks;

import java.util.List;

public interface DrinksDao
{
    List<Drinks> getAllDrinks();
    Drinks getById(int drinkId);
    Drinks create(Drinks drink);
    void update(int drinkId, Drinks drink);
    void delete(int drinkId);
}
