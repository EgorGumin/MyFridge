package com.lymno.myfridge.model;


import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("")
    private int userProductID;
    @SerializedName("")
    private int category;
    @SerializedName("")
    private int recipeID;
    @SerializedName("")
    private int importance;
    @SerializedName("")
    private int quantity;

    public Ingredient(int userProductID, int category, int recipeID, int importance, int quantity) {
        this.userProductID = userProductID;
        this.category = category;
        this.recipeID = recipeID;
        this.importance = importance;
        this.quantity = quantity;
    }

    public int getUserProductID() {
        return userProductID;
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
