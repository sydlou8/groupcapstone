package com.pluralsight.data;

import com.pluralsight.models.toppings.Side;

import javax.print.attribute.standard.Sides;
import java.util.List;

public interface SidesDao
{
    List<Side> getAllSides();
    Side getById(int sideId);
    Side create(Side side);
    void update(int sideId, Side side);
    void delete(int sideId);
}
