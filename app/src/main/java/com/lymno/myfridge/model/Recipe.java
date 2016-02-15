package com.lymno.myfridge.model;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

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
    @SerializedName("Images")
    private ArrayList<String> images;

    @Column(name = "images")
    private String storedImages;

    public void loadImages() {
        Gson gson = new Gson();
        images = gson.fromJson(storedImages, new TypeToken<ArrayList<String>>() {
        }.getType());
    }

    private void saveImages() {
        Gson gson = new Gson();
        this.storedImages = gson.toJson(images);
    }

    @Expose
    @SerializedName("Ingredients")
    private ArrayList<Ingredient> ingredients;

    public Recipe(int recipeID, String name, String description, ArrayList<Ingredient> ingredients, ArrayList<String> images) {
        super();
        this.recipeID = recipeID;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.images = images;
    }

    public Recipe() {
        super();
    }


    public static ArrayList<Recipe> getAll() {
        List<Recipe> recipes = new Select().from(Recipe.class).execute();
        ArrayList<Recipe> recipesWithIngredients = new ArrayList<>();
        for (Recipe recipe : recipes) {
            recipe.setIngredients(new ArrayList<>(recipe.getIngredientsBase()));
            recipe.loadImages();
            recipesWithIngredients.add(recipe);
        }
        return recipesWithIngredients;
    }

    //Сохраняет рецепт и ингредиенты
    public void saveAll() {
        saveImages();
        save();
        for (Ingredient i : ingredients) {
            i.recipe = this;
            i.save();
        }
    }

    public static void recreate(ArrayList<Recipe> recipes) {
        truncate(Recipe.class);
        truncate(Ingredient.class); //возможно, это лишнее
        save(recipes);
    }

    public static void truncate(Class<? extends Model> type) {
        TableInfo tableInfo = Cache.getTableInfo(type);
        // Not the cleanest way, but...
        ActiveAndroid.execSQL("delete from " + tableInfo.getTableName() + ";");
        ActiveAndroid.execSQL("delete from sqlite_sequence where name='" + tableInfo.getTableName() + "';");
    }

    //Сохраняет список рецептов и их ингредиенты
    public static void save(ArrayList<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            recipe.saveAll();
        }
    }


    public List<Ingredient> getIngredientsBase() {
        List<Ingredient> ingredients = getMany(Ingredient.class, "Recipe");
        Ingredient.setUserProducts(ingredients);
        return ingredients;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getName() {
        return name;
    }

    public String getMainImage() {
        return images.get(0);
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
