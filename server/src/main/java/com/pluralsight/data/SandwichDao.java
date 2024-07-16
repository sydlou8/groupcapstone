package com.pluralsight.data;

import com.pluralsight.SandwichModels.Sandwich;

import java.util.List;

public interface SandwichDao
{
    List<Sandwich> getAllSandwiches();
    Sandwich getById(int sandwichId);
    Sandwich create(Sandwich sandwich);
    void update(int sandwichId, Sandwich sandwich);
    void delete(int sandwichId);
}
