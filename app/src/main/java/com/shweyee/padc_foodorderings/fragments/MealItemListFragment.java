package com.shweyee.padc_foodorderings.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shweyee.padc_foodorderings.FoodOrderingsApp;
import com.shweyee.padc_foodorderings.R;
import com.shweyee.padc_foodorderings.adapters.MealAdapter;
import com.shweyee.padc_foodorderings.data.models.FoodItemModel;
import com.shweyee.padc_foodorderings.data.vos.FoodVO;
import com.shweyee.padc_foodorderings.events.DataEvent;
import com.shweyee.padc_foodorderings.views.holders.MealViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MealItemListFragment extends Fragment  {
    public static String CURRENT_SCREENSTYLE="CURRENT_SCREENSTYLE";
    public static String SHOW_ONECOLUMN="1";
    public static String SHOW_TWOCOLUMN="2";
    private String showcol;
    private int gridColumnSpanCount;

    @BindView(R.id.rv_meals)
    RecyclerView rvMeals;

    private MealAdapter mMealAdapter;
    private MealViewHolder.ControllerMealItem controllerMealItem;

    public static MealItemListFragment newInstance(String currentstlye) {
        MealItemListFragment fragment = new MealItemListFragment();
        Bundle bundle=new Bundle();
        bundle.putString(fragment.CURRENT_SCREENSTYLE,currentstlye);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerMealItem = (MealViewHolder.ControllerMealItem) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView= inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            showcol=bundle.getString(MealItemListFragment.CURRENT_SCREENSTYLE);
            Log.d(FoodOrderingsApp.TAG,"SHOW COLUMN"+showcol);
        }
        if(showcol==MealItemListFragment.SHOW_ONECOLUMN)
        {
            gridColumnSpanCount = getResources().getInteger(R.integer.meal_list_grid);
        }
        if(showcol==MealItemListFragment.SHOW_TWOCOLUMN)
        {
            gridColumnSpanCount = getResources().getInteger(R.integer.meal_list_grid2);
        }
        rvMeals.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        List<FoodVO> mealList = FoodItemModel.getInstance().getFoodItemList();
        mMealAdapter = new MealAdapter(mealList, controllerMealItem);
        rvMeals.setAdapter(mMealAdapter);







        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.MealDataLoadedEvent event) {
        String extra = event.getExtraMessage();
        Toast.makeText(getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<FoodVO> newMealList = event.getMealList();
        mMealAdapter.setNewData(newMealList);
    }
}
