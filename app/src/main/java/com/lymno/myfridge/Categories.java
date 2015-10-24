package com.lymno.myfridge;

import java.util.ArrayList;

/**
 * Created by Andre on 25.10.2015.
 */
public class Categories extends ArrayList<String> {
    static private Categories instance;

    public static void create(){
        instance=new Categories();
        instance.add("сок");
        instance.add("йогурт");
        instance.add("мята");
        instance.add("чеснок");
        instance.add("оливковое масло");
        instance.add("соль");
        instance.add("черный молотый перец");
        instance.add("лимон");
        instance.add("сахар");
        instance.add("вода");
        instance.add("сыр");
        instance.add("мука");
        instance.add("яйцо");
        instance.add("масло");
        instance.add("молоко");
        instance.add("маслины");
    }

    public static Categories get(){
        if (instance==null)create();
        return instance;
    }

    static public String getItem(int i){
        return get().get(i);
    }

}
