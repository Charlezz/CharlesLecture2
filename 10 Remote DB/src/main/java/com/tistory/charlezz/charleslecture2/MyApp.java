package com.tistory.charlezz.charleslecture2;

import android.app.Application;
import android.content.Context;

/**
 * Created by Charles on 16. 1. 17..
 */
public class MyApp extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
