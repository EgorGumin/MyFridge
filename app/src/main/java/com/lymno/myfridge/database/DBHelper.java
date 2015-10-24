package com.lymno.myfridge.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper
{
    String name;

    public DBHelper(Context context, String name)
    {
        super(context, name, null, 1);
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        switch (name)
        {
            case UserProductsDatabase.NAME:
                database.execSQL("create table " + UserProductsDatabase.NAME + "("

                        + UserProductsDatabase.ID + " int,"
                        + UserProductsDatabase.BASE_PRODUCT_ID + " int,"
                        + UserProductsDatabase.CATEGORY + " int,"
                        + UserProductsDatabase.USER_PRODUCT_NAME + " text,"
                        + UserProductsDatabase.MEASURE + " int,"
                        + UserProductsDatabase.QUANTITY + " int,"
                        + UserProductsDatabase.QUANTITY_BY_DEFAULT + " int,"
                        + UserProductsDatabase.DATE + " text"  + ");");
                break;
            default:
                break;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {

    }
}
