package com.lymno.myfridge;

import java.util.ArrayList;

public class Recipe {
    private String Name;
    private String Description;
    private ArrayList<Food> foodList;

    public Recipe(String name, String description, ArrayList<Food> foodList) {
        Name = name;
        Description = description;
        this.foodList = foodList;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }
}
