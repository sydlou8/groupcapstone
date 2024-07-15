package com.pluralsight.data;

import com.pluralsight.SandwichModels.Bread;

import java.util.List;

public interface BreadDao
{
    List<Bread> getAllBreads();
    Bread getById(int breadId);
    Bread create(Bread bread);
    void update(int breadId, Bread bread);
    void delete(int breadId);
}
