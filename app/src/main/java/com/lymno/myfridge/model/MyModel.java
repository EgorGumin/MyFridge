package com.lymno.myfridge.model;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;

/**
 * Created by Colored on 15.02.2016.
 */
public class MyModel extends Model {

    public static void truncate(Class<? extends Model> type) {
        TableInfo tableInfo = Cache.getTableInfo(type);
        // Not the cleanest way, but...
        ActiveAndroid.execSQL("delete from " + tableInfo.getTableName() + ";");
        ActiveAndroid.execSQL("delete from sqlite_sequence where name='" + tableInfo.getTableName() + "';");
    }
}
