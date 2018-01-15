package com.example.ilijaangeleski.careem.di.components;

import com.example.ilijaangeleski.careem.di.modules.MovieDetailsActivityModule;
import com.example.ilijaangeleski.careem.ui.MovieDetailsActivity;

import dagger.Component;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */
@Component(modules = MovieDetailsActivityModule.class,dependencies = BaseComponent.class)
public interface MovieDetailsActivityComponent {
    void inject(MovieDetailsActivity activity);
}
