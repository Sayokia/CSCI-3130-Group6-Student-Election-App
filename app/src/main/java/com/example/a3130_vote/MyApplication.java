package com.example.a3130_vote;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class MyApplication extends Application {
    SharedPrefs sharedPrefs;
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());

    }
}
