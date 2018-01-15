package com.example.ilijaangeleski.careem.di.modules;

import com.example.ilijaangeleski.careem.api.NetworkApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */
@Module
public class NetworkModule {

    @Provides
    NetworkApi provideNetworkApi(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }

    @Provides
    Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
