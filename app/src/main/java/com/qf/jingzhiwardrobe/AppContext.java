package com.qf.jingzhiwardrobe;

import android.app.Application;

import com.qf.utils.VolleyUtil;

/**
 * Created by Ken on 2015/11/16.
 */
public class AppContext extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.initVolley(this);
    }
}
