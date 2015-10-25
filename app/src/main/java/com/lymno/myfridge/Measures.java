package com.lymno.myfridge;

import java.util.ArrayList;

/**
 * Created by Andre on 25.10.2015.
 */
public class Measures extends ArrayList<String> {
    private static Measures instance;

    public static void create(){
        instance=new Measures();
        instance.add("мл");
        instance.add("гр");
        instance.add("шт");
    }

    public static Measures get(){
        if (instance==null)create();
        return instance;
    }

    static public String getItem(int i){
//        if (i>=get().size()-1)i=get().size()-2;
       return get().get(i-1);
    }


}
