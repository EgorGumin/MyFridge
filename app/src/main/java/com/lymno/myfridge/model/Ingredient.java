package com.lymno.myfridge.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


@Table(name = "Ingredients", id = "_id")
public class Ingredient extends Model {
    @Expose
    @SerializedName("UserProductID")
    @Column(name = "user_product_id")
    private int userProductID;

    @Expose
    @SerializedName("IngredientID")
    @Column(name = "id")
    private int ingredientID;

    @Expose
    @SerializedName("CategoryID")
    @Column(name = "category")
    private int category;

    @Expose
    @SerializedName("RecipeID")
    @Column(name = "recipe_id")
    private int recipeID;

    @Expose
    @SerializedName("ImportanceLevelID")
    @Column(name = "importance")
    private int importance;

    @Expose
    @SerializedName("Amount")
    @Column(name = "amount")
    private int quantity;

    @Column(name = "recipe", onDelete = Column.ForeignKeyAction.CASCADE)
    public Recipe recipe;

    public Ingredient(int userProductID, int ingredientID, int category, int recipeID, int importance, int quantity) {
        super();
        this.userProductID = userProductID;
        this.ingredientID = ingredientID;
        this.category = category;
        this.recipeID = recipeID;
        this.importance = importance;
        this.quantity = quantity;
    }

    public Ingredient() {
        super();
    }

    public static ArrayList<Ingredient> getAll() {
        List<Ingredient> ingredients = new Select().from(Ingredient.class).orderBy("RANDOM()").execute();
        return new ArrayList<>(ingredients);
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
