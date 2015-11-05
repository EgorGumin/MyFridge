package com.lymno.myfridge.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Recipe {
    @SerializedName("RecipeID")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    @SerializedName("Ingredients")
    private ArrayList<Ingredient> ingredients;
    @SerializedName("Image")
    private String image;

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

    public String getImageUrl() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public static class List extends ArrayList<Recipe> {
    }
}
