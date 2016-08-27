package com.shweyee.padc_foodorderings.data.agents.retrofit;

import com.shweyee.padc_foodorderings.data.agents.FoodDataAgent;
import com.shweyee.padc_foodorderings.data.models.FoodItemModel;
import com.shweyee.padc_foodorderings.data.responses.FoodListResponse;
import com.shweyee.padc_foodorderings.utils.CommonInstances;
import com.shweyee.padc_foodorderings.utils.FoodOrderingsConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by windows on 8/20/2016.
 */
public class RetrofitDataAgent implements FoodDataAgent{

    private static RetrofitDataAgent objInstance;

    private final FoodApi theApi;

    private RetrofitDataAgent() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoodOrderingsConstants.MEAL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(CommonInstances.getGsonInstance()))
                .client(okHttpClient)
                .build();

        theApi = retrofit.create(FoodApi.class);
    }

    public static RetrofitDataAgent getInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgent();
        }
        return objInstance;
    }


    @Override
    public void loadFoods() {
        Call<FoodListResponse> loadFoodCall = theApi.loadFoods(FoodOrderingsConstants.ACCESS_TOKEN);
        loadFoodCall.enqueue(new Callback<FoodListResponse>() {
            @Override
            public void onResponse(Call<FoodListResponse> call, Response<FoodListResponse> response) {
                FoodListResponse foodListResponse=response.body();
                if(foodListResponse==null)
                {
                    FoodItemModel.getInstance().notifyErrorInLoadingFoodItems(response.message());
                }
                else
                {
                    FoodItemModel.getInstance().notifyAttractionsLoaded(foodListResponse.getMealList());
                }
            }

            @Override
            public void onFailure(Call<FoodListResponse> call, Throwable throwable) {

                FoodItemModel.getInstance().notifyErrorInLoadingFoodItems(throwable.getMessage());
            }
        });

    }
}
