package com.example.ilijaangeleski.careem.di.modules;

import android.graphics.Movie;

import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.manager.MovieDetailsManager;
import com.example.ilijaangeleski.careem.presenter.MovieDetailsPresenter;
import com.example.ilijaangeleski.careem.view.MovieDetailsView;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */
@Module
public class MovieDetailsActivityModule {
    WeakReference<MovieDetailsView> view;

    public MovieDetailsActivityModule(MovieDetailsView view) {
        this.view = new WeakReference<>(view);
    }

    @Provides
    MovieDetailsPresenter provideMovieDetailsPresenter(MovieDetailsManager movieDetailsManager){
        return new MovieDetailsPresenter(movieDetailsManager,view);
    }
    @Provides
    MovieDetailsManager provideMovieDetailsManager(NetworkApi networkApi){
        return new MovieDetailsManager(networkApi);
    }
}

