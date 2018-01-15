package com.example.ilijaangeleski.careem;

import android.app.Application;

import com.example.ilijaangeleski.careem.di.components.BaseComponent;
import com.example.ilijaangeleski.careem.di.components.DaggerBaseComponent;
import com.example.ilijaangeleski.careem.di.modules.AppModule;
import com.example.ilijaangeleski.careem.di.modules.NetworkModule;

public class MyApp extends Application {
    private BaseComponent baseComponent;
    private static MyApp myApp;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        baseComponent = DaggerBaseComponent
                .builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public BaseComponent getBaseComponent(){
        return baseComponent;
    }

    public static MyApp getMyApp(){
        return myApp;
    }
}