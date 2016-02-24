package com.example.user.moodleapp;

import android.app.Application;

import java.net.CookieHandler;
import java.net.CookieManager;

/**
 * Created by USER on 20-02-2016.
 */
public class MyApp2 extends Application {
//class for custom apllication definition for cookie handling in the app
    @Override
    public void onCreate() {
        super.onCreate();
        CookieManager manager = new CookieManager();
        CookieHandler.setDefault( manager  );
    }
}
