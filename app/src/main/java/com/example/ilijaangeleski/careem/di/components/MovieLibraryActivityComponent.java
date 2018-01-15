package com.example.ilijaangeleski.careem.di.components;

import com.example.ilijaangeleski.careem.di.modules.MovieLibraryActivityModule;
import com.example.ilijaangeleski.careem.ui.MovieLibraryActivity;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */
@Component(modules = MovieLibraryActivityModule.class ,dependencies = BaseComponent.class)
public interface MovieLibraryActivityComponent {
    void inject(MovieLibraryActivity activity);
}
