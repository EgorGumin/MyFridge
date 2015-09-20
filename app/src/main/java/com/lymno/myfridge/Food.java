package com.lymno.myfridge;

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

}
