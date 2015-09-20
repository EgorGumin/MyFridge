package com.lymno.myfridge;

import java.util.ArrayList;


public class Examples {
    public static ArrayList<Food> getAllFood(){
        ArrayList<Food> foodArray = new ArrayList<>();
        foodArray.add(new Food("Чай", "Lipton Earl Grey"));
        foodArray.add(new Food("Кофе", "Carte Noire"));
        foodArray.add(new Food("Колбаса", "Вареная"));
        foodArray.add(new Food("Сыр", "Патриотический"));
        foodArray.add(new Food("Варенье", "Малиновое"));
        foodArray.add(new Food("Хлеб", "Черный"));
        return foodArray;
    }
    public static ArrayList<Recipe> getAllRecipes(){
        ArrayList<Recipe> recipeArray = new ArrayList<>();
        ArrayList<Food> sand = new ArrayList<>();
        sand.add(new Food("Колбаса", "Вареная"));
        sand.add(new Food("Хлеб", "Черный"));
        sand.add(new Food("Сыр", "Патриотический"));
        recipeArray.add(new Recipe("Бутерброд", "Возьми хлеб, положи на него колбасу", sand));
        return recipeArray;
    }
}
