package com.lymno.myfridge.model;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

@Table(name = "UserProducts", id = "_id")
public class UserProduct extends Model {

    @Expose
    @SerializedName("UserProductID")
    @Column(name = "id")
    private int userProductID;

    @Expose
    @SerializedName("FridgeID")
    @Column(name = "fridge_id")
    private int fridgeID;

    @Expose
    @SerializedName("ProductID")
    @Column(name = "base_product_id")
    private int baseProductID;

    @Expose
    @SerializedName("CategoryID")
    @Column(name = "category_id")
    private int category;

    @Expose
    @SerializedName("Name")
    @Column(name = "name")
    private String name;

    @Expose
    @SerializedName("UnitMeasureID")
    @Column(name = "measure")
    private int measure;

    @Expose
    @SerializedName("Amount")
    @Column(name = "quantity")
    private int quantity;

    @Expose
    @SerializedName("AmountDefault")
    @Column(name = "quantity_by_default")
    private int quantityByDefault;

    @Expose
    @SerializedName("ExpirationDate")
    @Column(name = "date")
    private Date date;


    public UserProduct(int id, int fridgeID, int baseProductID, int category, String name,
                       int measure, int quantity, int quantityByDefault, Date date) {
        super();
        this.userProductID = id;
        this.fridgeID = fridgeID;
        this.baseProductID = baseProductID;
        this.category = category;
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
        this.quantityByDefault = quantityByDefault;
        this.date = date;
    }

    public UserProduct() {
        super();
    }

//  Active Android

    public static ArrayList<UserProduct> getAll() {
        java.util.List<UserProduct> userProducts = new Select().from(UserProduct.class).execute(); //order by?
        return new ArrayList<UserProduct>(userProducts);
    }

    public static void recreate(ArrayList<UserProduct> userProducts) {
        truncate(UserProduct.class);
        saveList(userProducts);
    }

    public static void truncate(Class<? extends Model> type) {
        TableInfo tableInfo = Cache.getTableInfo(type);
        // Not the cleanest way, but...
        ActiveAndroid.execSQL("delete from " + tableInfo.getTableName() + ";");
        ActiveAndroid.execSQL("delete from sqlite_sequence where name='" + tableInfo.getTableName() + "';");
    }

    //Сохраняет список рецептов и их ингредиенты
    //TODO запилить транзакции, отрефакторить код, написать комменты
    public static void saveList(ArrayList<UserProduct> userProducts) {
        for (UserProduct userProduct : userProducts) {
            userProduct.save();
        }
    }

    public int getUserProductID() {
        return userProductID;
    }

    public int getBaseProductID() {
        return baseProductID;
    }

    public int getFridgeID() {
        return fridgeID;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getMeasure() {
        return measure;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantityByDefault() {
        return quantityByDefault;
    }

    public Date getDate() {
        return date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class List extends ArrayList<UserProduct> {
    }
}
