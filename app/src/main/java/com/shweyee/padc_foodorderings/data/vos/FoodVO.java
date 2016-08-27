package com.shweyee.padc_foodorderings.data.vos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by windows on 8/20/2016.
 */
public class FoodVO {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String mealname;

    @SerializedName("img_url")
    private String img_url;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private String price;

    @SerializedName("ingredients")
    private String[] ingredients;
    private int ingredientlen;
    private String ingrident;

    public int getid(){return id;}

    public String getname(){return mealname;}

    public String getimgurl(){return img_url;}

    public String getDescription(){return description;}

    public String getprice(){return price;}

    public String[] getingredients(){
        //ingredients = new String[ingredients.length];
        //ingredients.toArray(imageArray);
        return ingredients;
        //return ingredients.length;
    }
}
