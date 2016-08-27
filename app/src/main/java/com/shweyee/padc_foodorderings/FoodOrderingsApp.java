package com.shweyee.padc_foodorderings;

import android.app.Application;
import android.content.Context;

/**
 * Created by windows on 8/19/2016.
 */
public class FoodOrderingsApp extends Application {
    public static final String TAG = "FoodOrderingsApp";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
