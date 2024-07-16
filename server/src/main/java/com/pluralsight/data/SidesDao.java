package com.pluralsight.data;

import com.pluralsight.SandwichModels.Sides;
import java.util.List;

public interface SidesDao
{
    List<Sides> getAllSides();
    Sides getById(int sideId);
    Sides create(Sides side);
    void update(int sideId, Sides side);
    void delete(int sideId);
}
