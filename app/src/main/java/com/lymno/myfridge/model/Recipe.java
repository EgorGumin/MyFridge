package com.lymno.myfridge.model;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Recipes", id = "_id")
public class Recipe extends Model {
    @Expose
    @SerializedName("RecipeID")
    @Column(name = "id")
    private int recipeID;

    @Expose
    @SerializedName("Name")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("Description")
    @Column(name = "description")
    private String description;

    @Expose
    @SerializedName("Image")
    @Column(name = "image")
    private String image;

    @Expose
    @SerializedName("Ingredients")
    private ArrayList<Ingredient> ingredients;

    public Recipe(int recipeID, String name, String description, ArrayList<Ingredient> ingredients) {
        super();
        this.recipeID = recipeID;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Recipe() {
        super();
    }


    public static Recipe getRandom() {
        Recipe recipe = new Select().from(Recipe.class).orderBy("RANDOM()").executeSingle();
        recipe.setIngredients(new ArrayList<Ingredient>(recipe.getIngredientsBase()));
        return recipe;
    }

    public static ArrayList<Recipe> getAll() {
        List<Recipe> recipes = new Select().from(Recipe.class).orderBy("RANDOM()").execute();
        ArrayList<Recipe> recipesWithIngredients = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipe.setIngredients(new ArrayList<Ingredient>(recipe.getIngredientsBase()));
            recipesWithIngredients.add(recipe);
        }
        return recipesWithIngredients;
    }

    //Сохраняет рецепт и ингредиенты
    public void saveAll() {
        save();
        for (Ingredient i : ingredients) {
            i.recipe = this;
            i.save();
        }
    }

    public static void recreate(ArrayList<Recipe> recipes) {
        truncate(Recipe.class);
        truncate(Ingredient.class); //возможно, это лишнее
        saveList(recipes);
    }

    public static void truncate(Class<? extends Model> type) {
        TableInfo tableInfo = Cache.getTableInfo(type);
        // Not the cleanest way, but...
        ActiveAndroid.execSQL("delete from " + tableInfo.getTableName() + ";");
        ActiveAndroid.execSQL("delete from sqlite_sequence where name='" + tableInfo.getTableName() + "';");
    }

    //Сохраняет список рецептов и их ингредиенты
    public static void saveList(ArrayList<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            recipe.saveAll();
        }
    }


    public List<Ingredient> getIngredientsBase() {
        return getMany(Ingredient.class, "Recipe");
    }

    public int getRecipeID() {
        return recipeID;
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

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
