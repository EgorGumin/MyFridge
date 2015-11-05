package com.lymno.myfridge.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lymno.myfridge.model.UserProduct;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Egor on 24.10.2015.
 */
public class UserProductsDatabase {
    static final String NAME = "MyFridgeUserProductsDB";
    static final String ID = "id";
    static final String FRIDGE_ID = "fridge_id";
    static final String BASE_PRODUCT_ID = "base_product_id";
    static final String CATEGORY = "category";
    static final String USER_PRODUCT_NAME = "user_product_name";
    static final String MEASURE = "measure";
    static final String QUANTITY = "quantity";
    static final String QUANTITY_BY_DEFAULT = "quantity_by_default";
    static final String DATE = "date";

    static private DBHelper dbHelper;
    static private ContentValues cv;
    static private SQLiteDatabase db;
    static private Cursor cursor;
    static private String[] columns;

    static public ArrayList<UserProduct> getUserProducts() {
        dbHelper = DBHelper.get();
        ArrayList<UserProduct> UserProducts = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        columns = new String[]{ID, FRIDGE_ID, BASE_PRODUCT_ID, CATEGORY, USER_PRODUCT_NAME, MEASURE, QUANTITY, QUANTITY_BY_DEFAULT, DATE};
        cursor = db.query(NAME, columns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                int fridgeID = cursor.getInt(cursor.getColumnIndex(FRIDGE_ID));
                int baseProductID = cursor.getInt(cursor.getColumnIndex(BASE_PRODUCT_ID));
                int category = cursor.getInt(cursor.getColumnIndex(CATEGORY));
                String userProductName = cursor.getString(cursor.getColumnIndex(USER_PRODUCT_NAME));
                int measure = cursor.getInt(cursor.getColumnIndex(MEASURE));
                int quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
                int quantityByDefault = cursor.getInt(cursor.getColumnIndex(QUANTITY_BY_DEFAULT));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(DATE)));//может упасть

                UserProducts.add(new UserProduct(id, fridgeID, baseProductID, category, userProductName, measure,
                        quantity, quantityByDefault, date));
                cursor.moveToNext();
            }
        }

        cursor.close();
        dbHelper.close();
        return UserProducts;
    }

    static public void addUserProduct(UserProduct userProduct) {
        dbHelper = DBHelper.get();
        cv = new ContentValues();
        db = dbHelper.getWritableDatabase();

        cv.put(ID, userProduct.getId());
        cv.put(FRIDGE_ID, userProduct.getFridgeID());
        cv.put(BASE_PRODUCT_ID, userProduct.getBaseProductID());
        cv.put(CATEGORY, userProduct.getCategory());
        cv.put(USER_PRODUCT_NAME, userProduct.getName());
        cv.put(MEASURE, userProduct.getMeasure());
        cv.put(QUANTITY, userProduct.getQuantity());
        cv.put(QUANTITY_BY_DEFAULT, userProduct.getQuantityByDefault());
        cv.put(DATE, userProduct.getDate().getTime()); //ох, ну и жесть. потом узнаем, как класть дату

        db.insert(NAME, null, cv);
        dbHelper.close();
    }

    static public void recreateDataBase(ArrayList<UserProduct> userProducts) {
        deleteDataBase();
        dbHelper = DBHelper.get();
        for (int i = 0; i < userProducts.size(); ++i) {
            addUserProduct(userProducts.get(i));
        }
        dbHelper.close();
    }

    static private void deleteDataBase() {
        dbHelper = DBHelper.get();
        db = dbHelper.getWritableDatabase();
        db.delete(NAME, null, null);
        dbHelper.close();
    }
}
