package com.lymno.myfridge.model;


import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("UserProductID")
    private int userProductID;
    @SerializedName("IngredientID")
    private int ingredientID;
    @SerializedName("CategoryID")
    private int category;
    @SerializedName("RecipeID")
    private int recipeID;
    @SerializedName("ImportanceLevelID")
    private int importance;
    @SerializedName("Amount")
    private int quantity;

    public Ingredient(int userProductID, int ingredientID, int category, int recipeID, int importance, int quantity) {
        this.userProductID = userProductID;
        this.ingredientID = ingredientID;
        this.category = category;
        this.recipeID = recipeID;
        this.importance = importance;
        this.quantity = quantity;
    }

    public int getUserProductID() {
        return userProductID;
    }

    public int getIngredientID() {
        return ingredientID;
    }

    public int getCategory() {
        return category;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public int getImportance() {
        return importance;
    }

    public int getQuantity() {
        return quantity;
    }
}
