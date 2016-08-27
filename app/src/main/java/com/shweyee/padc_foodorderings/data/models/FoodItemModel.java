package com.shweyee.padc_foodorderings.data.models;

import com.shweyee.padc_foodorderings.data.vos.FoodVO;
import com.shweyee.padc_foodorderings.events.DataEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by windows on 8/20/2016.
 */
public class FoodItemModel extends BaseModel {
    public static final String BROADCAST_DATA_LOADED = "BROADCAST_DATA_LOADED";

    private static FoodItemModel objInstance;

    private List<FoodVO> mFoodItemList;

    private FoodItemModel() {
        super();
        mFoodItemList = new ArrayList<>();
        dataAgent.loadFoods();
    }

    public static FoodItemModel getInstance() {
        if (objInstance == null) {
            objInstance = new FoodItemModel();
        }
        return objInstance;
    }

    public  void loadFoods()
    {
        dataAgent.loadFoods();
    }

    public List<FoodVO> getFoodItemList() {
        return mFoodItemList;
    }

    public FoodVO getMealById(int mealid) {
        for (FoodVO meal : mFoodItemList) {
            if (meal.getid()==mealid)
                return meal;
        }

        return null;
    }

    public void notifyAttractionsLoaded(List<FoodVO> fooditemList) {
        //Notify that the data is ready - using LocalBroadcast
        mFoodItemList = fooditemList;

        //keep the data in persistent layer.
        //FoodVO.saveAttractions(mAttractionList);

        broadcastMealLoadedWithEventBus();
        //broadcastAttractionLoadedWithLocalBroadcastManager();
    }

    public void notifyErrorInLoadingFoodItems(String message) {

    }

    private void broadcastMealLoadedWithEventBus() {
        EventBus.getDefault().post(new DataEvent.MealDataLoadedEvent("extra-in-broadcast", mFoodItemList));
    }

}
