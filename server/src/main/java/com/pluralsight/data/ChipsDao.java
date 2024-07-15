package com.pluralsight.data;

import com.pluralsight.SandwichModels.Chips;

import java.util.List;

public interface ChipsDao
{
    List<Chips> getAllChips();
    Chips getById(int chipsId);
    Chips create(Chips chips);
    void update(int chipsId, Chips chips);
    void delete(int chipsId);
}
