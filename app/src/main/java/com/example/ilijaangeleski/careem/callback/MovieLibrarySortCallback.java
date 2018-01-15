package com.example.ilijaangeleski.careem.callback;

import com.example.ilijaangeleski.careem.model.ResponseMovieDTO;

/**
 * Created by Ilija Angeleski on 1/15/2018.
 */

public interface MovieLibrarySortCallback {
    void onSuccess(ResponseMovieDTO response);

    void onFailure(Throwable t);
}
