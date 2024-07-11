package com.pluralsight.data;

import com.pluralsight.SandwichModels.RegularToppings;

import java.util.List;

public interface RegularToppingsDao
{
    List<RegularToppings> getAllRegularToppings();
    RegularToppings getById(int regularToppingsId);
    RegularToppings create(RegularToppings regularToppings);
    void update(int regularToppingsId, RegularToppings regularToppings);
    void delete(int regularToppingsId);
}
