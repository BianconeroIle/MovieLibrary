package com.example.ilijaangeleski.careem.di.components;

import android.content.Context;

import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.di.modules.AppModule;
import com.example.ilijaangeleski.careem.di.modules.NetworkModule;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */
@Component(modules = {AppModule.class, NetworkModule.class})
public interface BaseComponent {
    Context getContext();

    NetworkApi getNetworkApi();
}
