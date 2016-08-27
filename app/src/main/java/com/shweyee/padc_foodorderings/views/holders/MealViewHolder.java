package com.shweyee.padc_foodorderings.views.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shweyee.padc_foodorderings.FoodOrderingsApp;
import com.shweyee.padc_foodorderings.R;
import com.shweyee.padc_foodorderings.data.vos.FoodVO;
import com.shweyee.padc_foodorderings.utils.FoodOrderingsConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 8/20/2016.
 */
public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.tv_meal_name)
    TextView tvMealName;

    @BindView(R.id.iv_meal)
    ImageView ivMeal;

    @BindView(R.id.tv_meal_price)
    TextView tvMealPrice;

    private ControllerMealItem mController;
    private FoodVO mfood;

    public MealViewHolder(View itemView, ControllerMealItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(FoodVO meal) {
        mfood = meal;
        tvMealName.setText(meal.getname());
        tvMealPrice.setText("PRICE : "+meal.getprice()+" Ks");

        String imageUrl =  meal.getimgurl();
        Log.d(FoodOrderingsApp.TAG,"imageurl:"+imageUrl);
        Glide.with(ivMeal.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivMeal);
    }

    @Override
    public void onClick(View v) {

        Log.d(FoodOrderingsApp.TAG,mfood.getname());
        mController.onTapMealItem(mfood,ivMeal);
    }

    public interface ControllerMealItem {
        void onTapMealItem(FoodVO food,ImageView ivMeal);
    }
}
