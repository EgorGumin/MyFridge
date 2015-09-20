package com.lymno.myfridge;

import java.util.ArrayList;

public class Recipe {
    private String Name;
    private String Description;
    private ArrayList<Food> foodList;

    public String getFoodListString(){
        if (foodList.size() == 0){
            return "";
        }
        String allFood = "";
        for (int i = 0; i < foodList.size(); i++) {
            if (i == foodList.size() - 1){
                allFood = allFood + foodList.get(i).getName().toLowerCase() + ".";
            }
            else{
                if (i == 0){
                    allFood = allFood + foodList.get(i).getName() + ", ";
                }
                else {
                    allFood = allFood + foodList.get(i).getName().toLowerCase() + ", ";
                }
            }
        }
        return allFood;
    }

    public Recipe(String name, String description, ArrayList<Food> foodList) {
        Name = name;
        Description = description;
        this.foodList = foodList;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }
}
