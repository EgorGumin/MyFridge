package com.lymno.myfridge.model;

import java.util.ArrayList;

/**
 * Created by Egor on 24.10.2015.
 */
public class Recipe {
    private int id;
    private String name;
    private String description;
    private ArrayList<Ingredient> ingredients;

    public Recipe(int id, String name, String description, ArrayList<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
