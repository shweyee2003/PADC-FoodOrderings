package com.shweyee.padc_foodorderings.data.models;

import com.shweyee.padc_foodorderings.data.agents.FoodDataAgent;
import com.shweyee.padc_foodorderings.data.agents.retrofit.RetrofitDataAgent;

import de.greenrobot.event.EventBus;

/**
 * Created by aung on 7/15/16.
 */
public abstract class BaseModel {

    private static final int INIT_DATA_AGENT_RETROFIT = 1;

    protected FoodDataAgent dataAgent;

    public BaseModel() {
        initDataAgent(INIT_DATA_AGENT_RETROFIT);

        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    private void initDataAgent(int initType) {
        dataAgent = RetrofitDataAgent.getInstance();
        /*switch (initType) {
            case INIT_DATA_AGENT_OK_HTTP:
                dataAgent = OkHttpDataAgent.getInstance();
                break;
            case INIT_DATA_AGENT_RETROFIT:
                dataAgent = RetrofitDataAgent.getInstance();
                break;
        }*/
    }

    public void onEventMainThread(Object obj) {

    }
}