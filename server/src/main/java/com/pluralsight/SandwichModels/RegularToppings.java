package com.pluralsight.SandwichModels;

public class RegularToppings
{
    private int regularToppingsId;
    private String regularToppingsType;

    public RegularToppings() {
    }

    public RegularToppings(int regularToppingsId, String regularToppingsType)
    {
        this.regularToppingsId = regularToppingsId;
        this.regularToppingsType = regularToppingsType;
    }

    public int getRegularToppingsId() {
        return regularToppingsId;
    }

    public void setRegularToppingsId(int regularToppingsId) {
        this.regularToppingsId = regularToppingsId;
    }

    public String getRegularToppingsType() {
        return regularToppingsType;
    }

    public void setRegularToppingsType(String regularToppingsType) {
        this.regularToppingsType = regularToppingsType;
    }
}
