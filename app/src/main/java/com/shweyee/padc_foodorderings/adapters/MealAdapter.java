package com.shweyee.padc_foodorderings.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shweyee.padc_foodorderings.FoodOrderingsApp;
import com.shweyee.padc_foodorderings.R;
import com.shweyee.padc_foodorderings.data.vos.FoodVO;
import com.shweyee.padc_foodorderings.views.holders.MealViewHolder;

import java.util.List;

/**
 * Created by windows on 8/20/2016.
 */
public class MealAdapter extends RecyclerView.Adapter<MealViewHolder> {
    private List<FoodVO> mFoodList;
    private LayoutInflater inflater;

    private MealViewHolder.ControllerMealItem mcontrollerMealItem;

    public MealAdapter(List<FoodVO> mealList,MealViewHolder.ControllerMealItem controllerMealItem){
        inflater = LayoutInflater.from(FoodOrderingsApp.getContext());
        mFoodList = mealList;
        mcontrollerMealItem = controllerMealItem;
    }




    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_meal, parent, false);
        return new MealViewHolder(itemView, mcontrollerMealItem);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        holder.bindData(mFoodList.get(position));
    }


    @Override
    public int getItemCount() {
        return  mFoodList.size();
    }

    public void setNewData(List<FoodVO> newMealList) {
        mFoodList = newMealList;
        notifyDataSetChanged();//framework method
    }
}
