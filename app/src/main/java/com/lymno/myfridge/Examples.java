package com.lymno.myfridge;


import com.lymno.myfridge.model.UserProduct;

import java.util.ArrayList;


public class Examples {
    public static ArrayList<UserProduct> getAllFood(){
        ArrayList<UserProduct> foodArray = new ArrayList<>();
        java.sql.Date date=  new java.sql.Date(14142);
        foodArray.add(new UserProduct(1,1,1,"name",1,50,100,  date));
        foodArray.add(new UserProduct(1,1,1,"name",1,70,100,  date));
        foodArray.add(new UserProduct(1,1,1,"name",1,80,100,  date));
        foodArray.add(new UserProduct(1,1,1,"name",1,40,100,  date));
        foodArray.add(new UserProduct(1,1,1,"name",1,20,100,  date));
        foodArray.add(new UserProduct(1,1,1,"name",1,50,100,  date));
        return foodArray;
    }
    public static ArrayList<Recipe> getAllRecipes(){
        ArrayList<Recipe> recipeArray = new ArrayList<>();
        ArrayList<UserProduct> sand = new ArrayList<>();
//        sand.add(new Food("Колбаса", "Вареная"));
//        sand.add(new Food("Хлеб", "Черный"));
//        sand.add(new Food("Сыр", "Патриотический"));
//        recipeArray.add(new Recipe("Бутерброд", "Возьми хлеб, положи на него колбасу", sand));
        return recipeArray;
    }
}
