package com.example.ilijaangeleski.careem.di.modules;

import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.manager.MovieLibraryManager;
import com.example.ilijaangeleski.careem.presenter.MovieLibraryPresenter;
import com.example.ilijaangeleski.careem.view.MovieLibraryView;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */
@Module
public class MovieLibraryActivityModule {
    WeakReference<MovieLibraryView> view;

    public MovieLibraryActivityModule(MovieLibraryView view){
        this.view=new WeakReference<>(view);
    }

    @Provides
    MovieLibraryPresenter provideMovieLibraryPresenter(MovieLibraryManager movieLibraryManager){
        return new MovieLibraryPresenter(movieLibraryManager,view);
    }

    @Provides
    MovieLibraryManager provideMovieLibraryManager(NetworkApi networkApi){
        return new MovieLibraryManager(networkApi);
    }
}
