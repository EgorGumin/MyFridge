package com.lymno.myfridge.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


@Table(name = "Ingredients", id = "_id")
public class Ingredient extends Model {
    @Expose
    @SerializedName("IngredientID")
    @Column(name = "id")
    private int ingredientID;

    @Expose
    @SerializedName("CategoryName")
    @Column(name = "category_name")
    private String category;

    @Expose
    @SerializedName("ImportanceLevel")
    @Column(name = "importance")
    private int importance;

    @Expose
    @SerializedName("Amount")
    @Column(name = "amount")
    private int quantity;

    @Column(name = "recipe", onDelete = Column.ForeignKeyAction.CASCADE)
    public Recipe recipe;

    @Expose
    @SerializedName("SubstituteIDs")
    private int[] substitutes;

    @Column(name = "substitutes")
    private String subsString;

    private ArrayList<Integer> userProducts;

    public int[] getSubs() {
        Gson gson = new Gson();
        return gson.fromJson(subsString, int[].class);
    }

    private void setSubs(int[] array) {
        Gson gson = new Gson();
        this.subsString = gson.toJson(array);
    }

    public Ingredient(int ingredientID, String category, int importance, int quantity, int[] substitutes) {
        super();
        this.ingredientID = ingredientID;
        this.category = category;
        this.importance = importance;
        this.quantity = quantity;
        this.substitutes = substitutes;
        this.userProducts = new ArrayList<Integer>();
        setSubs(substitutes);
    }

    public Ingredient() {
        super();
    }

    public static ArrayList<Ingredient> getAll() {
        List<Ingredient> ingredients = new Select().from(Ingredient.class).orderBy("RANDOM()").execute();
        return new ArrayList<>(ingredients);
    }


    public int getIngredientID() {
        return ingredientID;
    }

    //Получить
    public static void setUserProducts(List<Ingredient> ingredients) {
        ArrayList<UserProduct> userProducts = UserProduct.getAll();
        for (Ingredient i : ingredients) {
            i.userProducts = new ArrayList<>();
            for (UserProduct up : userProducts) {
                if (i.getCategory().equals(up.getCategory())) {
                    i.userProducts.add(up.getUserProductID());
                }
            }
        }
    }

    public String getCategory() {
        return category;
    }

    public int getImportance() {
        return importance;
    }

    public int getQuantity() {
        return quantity;
    }
}
