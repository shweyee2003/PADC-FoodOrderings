package com.shweyee.padc_foodorderings.data.agents.retrofit;

import com.shweyee.padc_foodorderings.data.responses.FoodListResponse;
import com.shweyee.padc_foodorderings.utils.FoodOrderingsConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by windows on 8/19/2016.
 */
public interface FoodApi {
    @FormUrlEncoded
    @POST(FoodOrderingsConstants.API_GET_MEAL_LIST)
    Call<FoodListResponse> loadFoods(
            @Field(FoodOrderingsConstants.PARAM_ACCESS_TOKEN) String param);
}
