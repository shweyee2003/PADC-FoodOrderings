package com.shweyee.padc_foodorderings.events;

import com.shweyee.padc_foodorderings.data.vos.FoodVO;

import java.util.List;

/**
 * Created by windows on 8/20/2016.
 */
public class DataEvent {
    public static class MealDataLoadedEvent{

        private String extraMessage;
        private List<FoodVO> MealList;

        public MealDataLoadedEvent(String extraMessage, List<FoodVO> mealList) {
            this.extraMessage = extraMessage;
            this.MealList = mealList;
        }

        public String getExtraMessage() {
            return extraMessage;
        }

        public List<FoodVO> getMealList() {
            return MealList;
        }
    }
}
