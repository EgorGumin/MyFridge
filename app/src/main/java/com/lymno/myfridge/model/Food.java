package com.lymno.myfridge.model;

import java.util.ArrayList;

/**
 * Created by Colored on 07.10.2015.
 */
public class Food {
    public String Name;
    public String Description;

    public Food(String name, String description) {
        Name = name;
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    @SuppressWarnings("serial")
    public static class List extends ArrayList<Food> {
    }
}
