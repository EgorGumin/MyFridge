package com.lymno.myfridge.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper instance;

    public static DBHelper get() {
        return instance;
    }


    public DBHelper(Context context) {
        super(context, "DB", null, 1);
        instance = this;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table " + UserProductsDatabase.NAME + "("
                + UserProductsDatabase.ID + " int,"
                + UserProductsDatabase.FRIDGE_ID + " int,"
                + UserProductsDatabase.BASE_PRODUCT_ID + " int,"
                + UserProductsDatabase.CATEGORY + " int,"
                + UserProductsDatabase.USER_PRODUCT_NAME + " text,"
                + UserProductsDatabase.MEASURE + " int,"
                + UserProductsDatabase.QUANTITY + " int,"
                + UserProductsDatabase.QUANTITY_BY_DEFAULT + " int,"
                + UserProductsDatabase.DATE + " long" + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }
}
