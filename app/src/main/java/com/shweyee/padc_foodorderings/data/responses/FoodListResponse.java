package com.shweyee.padc_foodorderings.data.responses;

import com.google.gson.annotations.SerializedName;
import com.shweyee.padc_foodorderings.data.vos.FoodVO;

import java.util.ArrayList;

/**
 * Created by windows on 8/20/2016.
 */
public class FoodListResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("meal_list")
    private ArrayList<FoodVO> mealList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<FoodVO> getMealList() {
        return mealList;
    }

}
