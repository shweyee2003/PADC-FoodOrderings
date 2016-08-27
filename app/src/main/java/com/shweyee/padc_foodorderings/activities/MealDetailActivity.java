package com.shweyee.padc_foodorderings.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shweyee.padc_foodorderings.FoodOrderingsApp;
import com.shweyee.padc_foodorderings.R;
import com.shweyee.padc_foodorderings.data.models.FoodItemModel;
import com.shweyee.padc_foodorderings.data.vos.FoodVO;
import com.shweyee.padc_foodorderings.utils.FoodOrderingsConstants;
import com.shweyee.padc_foodorderings.views.holders.MealViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by windows on 8/22/2016.
 */
public class MealDetailActivity extends AppCompatActivity{

    private static final String IE_MEAL_NAME = "IE_MEAL_NAME";
    private static final String IE_MEAL = "IE_MEAL";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tv_meal_desc)
    TextView tvMealDesc;

    @BindView(R.id.tv_price)
    TextView tvMealPrice;

    @BindView(R.id.iv_meal)
    ImageView ivMeal;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.tv_meal_ingrident)
    TextView tvMealIngrident;

    private String mMealTitle;
    private FoodVO mMeal;
    public int mMealid;
    public  String ingident="Ingridents : " +  "\n\n" ;
    public static Intent newIntent(int mealid) {

        Intent intent = new Intent(FoodOrderingsApp.getContext(), MealDetailActivity.class);
        intent.putExtra(IE_MEAL_NAME, mealid);
        //Bundle bundle=new Bundle();

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageUrl =  mMeal.getimgurl();

            }
        });

        int mMealId=getIntent().getIntExtra(IE_MEAL_NAME,0);
        mMeal= FoodItemModel.getInstance().getMealById(mMealId);
       // Log.d(mMealid);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Context context = FoodOrderingsApp.getContext();
            String transitionName = context.getResources().getString(R.string.meal_list_detail_transition_name);
            ivMeal.setTransitionName(transitionName);
        }

       mMealTitle = mMeal.getname();

        bindData(mMeal);
    }

    private void bindData(FoodVO meal) {
        String[] ingidents=meal.getingredients();

        for(int index=0;index<ingidents.length;index++)
        {

                ingident = ingident + ingidents[index] + "\n";
        }

        tvMealDesc.setText(meal.getDescription() );
        tvMealPrice.setText("PRICE : " +meal.getprice()+" Ks");
        tvMealIngrident.setText(ingident);
       // Log.d(FoodOrderingsApp.TAG,"ingrident"+meal.getingredients().length+ingident);

        String imageUrl = meal.getimgurl();
        Glide.with(ivMeal.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.stock_photo_placeholder)
                .error(R.drawable.stock_photo_placeholder)
                .into(ivMeal);



        collapsingToolbar.setTitle(mMealTitle);
    }


}
