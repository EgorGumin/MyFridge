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

@Table(name = "Categories", id = "_id")
public class Category extends Model {
    @Expose
    @SerializedName("Name")
    @Column(name = "name")
    private String name;

    public Category(String name) {
        super();
        this.name = name;
    }

    public Category() {
        super();
    }

    public static ArrayList<Category> getAll() {
        List<Category> categories = new Select().from(Category.class).execute();
        return new ArrayList<>(categories);
    }

    public static ArrayList<String> getTextArrayList() {
        List<Category> categories = new Select().from(Category.class).execute();
        ArrayList<String> categoriesString = new ArrayList<>();
        for (Category cat : categories) {
            categoriesString.add(cat.getName());
        }
        return categoriesString;
    }

    public static void recreate(ArrayList<Category> categories) {
        truncate(Category.class);
        saveList(categories);
    }

    public static void truncate(Class<? extends Model> type) {
        TableInfo tableInfo = Cache.getTableInfo(type);
        // Not the cleanest way, but...
        ActiveAndroid.execSQL("delete from " + tableInfo.getTableName() + ";");
        ActiveAndroid.execSQL("delete from sqlite_sequence where name='" + tableInfo.getTableName() + "';");
    }

    public static void saveList(ArrayList<Category> categories) {
        for (Category category : categories) {
            category.save();
        }
    }

    public String getName() {
        return name;
    }
}
