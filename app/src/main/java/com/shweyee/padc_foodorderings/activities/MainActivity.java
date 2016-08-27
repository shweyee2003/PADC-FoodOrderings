package com.shweyee.padc_foodorderings.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.shweyee.padc_foodorderings.FoodOrderingsApp;
import com.shweyee.padc_foodorderings.R;
import com.shweyee.padc_foodorderings.data.vos.FoodVO;
import com.shweyee.padc_foodorderings.fragments.MealItemListFragment;
import com.shweyee.padc_foodorderings.views.holders.MealViewHolder;

public class MainActivity extends AppCompatActivity implements MealViewHolder.ControllerMealItem {

   public String currentstyle="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (savedInstanceState == null) {
            navigateToMealRecyclerView();
        }
      //  navigateToMealRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                return true;
            case R.id.action_onecolumn:
                showonecolumnlist();
                return true;
            case R.id.action_twocloumn:
                showtwocolumnlist();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToMealRecyclerView() {
        MealItemListFragment mealItemListFragment=MealItemListFragment.newInstance(currentstyle);
        //Bundle bundle=new Bundle();
        //bundle.putString(mealItemListFragment.CURRENT_SCREENSTYLE,currentstyle);
        //mealItemListFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, mealItemListFragment)
                .commit();
    }

    private void showonecolumnlist()
    {
        currentstyle="1";
        navigateToMealRecyclerView();
        Toast.makeText(getApplicationContext(),"onecolumn"+currentstyle,Toast.LENGTH_SHORT).show();
    }

    private void showtwocolumnlist()
    {
        currentstyle="2";
        navigateToMealRecyclerView();
        Toast.makeText(getApplicationContext(),"twocolumn"+currentstyle,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onTapMealItem(FoodVO food, ImageView ivMeal) {

        Intent intent = MealDetailActivity.newIntent(food.getid());
        startActivity(intent);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                new Pair(ivMeal, getString(R.string.meal_list_detail_transition_name)));
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle());
    }


}
