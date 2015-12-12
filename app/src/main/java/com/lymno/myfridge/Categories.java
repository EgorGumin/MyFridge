package com.lymno.myfridge;

import com.lymno.myfridge.model.Category;

import java.util.ArrayList;

public class Categories extends ArrayList<Category> {
    static private Categories list;

    public static Categories get() {
        if (list == null) {
            ArrayList<Category> cat = Category.getAll();
            list = new Categories();
            //list.add(new Category(1, "SUKA"));
            list.addAll(cat);
        }
        return list;
    }


}
