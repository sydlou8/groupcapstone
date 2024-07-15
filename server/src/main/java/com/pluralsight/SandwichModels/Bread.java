package com.pluralsight.SandwichModels;

public class Bread
{
    private int breadId;
    private String breadName;
    private String breadSize;

    public Bread() {
    }

    public Bread(int breadId, String breadName, String breadSize) {
        this.breadId = breadId;
        this.breadName = breadName;
        this.breadSize = breadSize;
    }

    public int getBreadId() {
        return breadId;
    }

    public void setBreadId(int breadId) {
        this.breadId = breadId;
    }

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public String getBreadSize() {
        return breadSize;
    }

    public void setBreadSize(String breadSize) {
        this.breadSize = breadSize;
    }
}
