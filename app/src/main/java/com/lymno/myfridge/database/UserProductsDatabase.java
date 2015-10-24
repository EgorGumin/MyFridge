package com.lymno.myfridge.database;

import android.content.ContentValues;
import android.content.Context;
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
    static final String BASE_PRODUCT_ID = "base_product_id";
    static final String CATEGORY = "category";
    static final String USER_PRODUCT_NAME = "user_product_name";
    static final String MEASURE = "measure";
    static final String QUANTITY = "quantity";
    static final String QUANTITY_BY_DEFAULT = "quantity_by_default";
    static final String DATE = "date";

    private Context context;
    private DBHelper dbHelper;
    private ContentValues cv;
    private SQLiteDatabase db;
    private Cursor cursor;
    private String[] columns;

    public UserProductsDatabase(Context context)
    {
        this.context = context;
    }

    public ArrayList<UserProduct> getUserProducts()
    {
        dbHelper = new DBHelper(context, NAME);
        ArrayList<UserProduct> UserProducts = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        columns = new String[]{ID, BASE_PRODUCT_ID, CATEGORY, USER_PRODUCT_NAME, MEASURE, QUANTITY, QUANTITY_BY_DEFAULT, DATE};
        cursor = db.query(NAME, columns, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(ID));
                int baseProductID = cursor.getInt(cursor.getColumnIndex(BASE_PRODUCT_ID));
                int category = cursor.getInt(cursor.getColumnIndex(CATEGORY));
                String userProductName = cursor.getString(cursor.getColumnIndex(USER_PRODUCT_NAME));
                int measure = cursor.getInt(cursor.getColumnIndex(MEASURE));
                int quantity = cursor.getInt(cursor.getColumnIndex(QUANTITY));
                int quantityByDefault = cursor.getInt(cursor.getColumnIndex(QUANTITY_BY_DEFAULT));
                Date date = Date.valueOf(cursor.getString(cursor.getColumnIndex(DATE)));//может упасть

                UserProducts.add(new UserProduct(id, baseProductID, category,userProductName, measure,
                                                 quantity, quantityByDefault, date));
                cursor.moveToNext();
            }
        }

        cursor.close();
        dbHelper.close();
        return UserProducts;
    }

    public void addUserProduct(UserProduct userProduct)
    {
        dbHelper = new DBHelper(context, NAME);
        cv = new ContentValues();
        db = dbHelper.getWritableDatabase();

        cv.put(ID, userProduct.getId());
        cv.put(BASE_PRODUCT_ID, userProduct.getBaseProductID());
        cv.put(CATEGORY, userProduct.getCategory());
        cv.put(USER_PRODUCT_NAME, userProduct.getName());
        cv.put(MEASURE, userProduct.getMeasure());
        cv.put(QUANTITY, userProduct.getQuantity());
        cv.put(QUANTITY_BY_DEFAULT, userProduct.getQuantityByDefault());
        cv.put(DATE, userProduct.getDate().toString()); //ох, ну и жесть. потом узнаем, как класть дату

        db.insert(NAME, null, cv);
        dbHelper.close();
    }

    public void recreateDataBase(ArrayList<UserProduct> userProducts)
    {
        deleteDataBase();
        dbHelper = new DBHelper(context, NAME);
        for (int i = 0; i < userProducts.size(); ++i)
        {
            addUserProduct(userProducts.get(i));
        }
        dbHelper.close();
    }

    private void deleteDataBase()
    {
        dbHelper = new DBHelper(context, NAME);
        db = dbHelper.getWritableDatabase();
        db.delete(NAME, null, null);
        dbHelper.close();
    }
}
