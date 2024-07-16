package com.pluralsight.data;

import com.pluralsight.SandwichModels.Sauces;

import java.util.List;

public interface SaucesDao
{
    List<Sauces> getAllSauces();
    Sauces getById(int sauceId);
    Sauces create(Sauces sauce);
    void update(int sauceId, Sauces sauce);
    void delete(int sauceId);
}
