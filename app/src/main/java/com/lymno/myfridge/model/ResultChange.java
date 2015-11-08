package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Andre on 25.10.2015.
 */
public class ResultChange {

    @Expose
    @SerializedName("IsCorrect")
    int isCorrect;

    public int getIsCorrect() {
        return isCorrect;
    }

}
