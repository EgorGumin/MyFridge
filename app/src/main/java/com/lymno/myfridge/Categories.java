package com.lymno.myfridge;

import java.util.ArrayList;

/**
 * Created by Andre on 25.10.2015.
 */
public class Categories extends ArrayList<String> {
    static private Categories instance;

    public static void create() {
        instance = new Categories();
//        instance.add("HARDCODE FOR GODS!!!");
        instance.add("Сок");
        instance.add("Йогурт");
        instance.add("Мята");
        instance.add("Чеснок");
        instance.add("Масло оливковое");
        instance.add("Соль");
        instance.add("Черный молотый перец");
        instance.add("Лимон");
        instance.add("Сахар");
        instance.add("Вода");
        instance.add("Сыр");
        instance.add("Мука");
        instance.add("Яйцо");
        instance.add("Масло");
        instance.add("Молоко");
        instance.add("Маслины");
    }

    public static Categories get() {
        if (instance == null) create();
        return instance;
    }

    static public String getItem(int i) {
//        if (i>=get().size()-rec1)
//            i=get().size()-2;
        return get().get(i - 1);
    }

}
