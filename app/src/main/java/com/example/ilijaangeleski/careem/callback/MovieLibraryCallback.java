package com.example.ilijaangeleski.careem.callback;

import com.example.ilijaangeleski.careem.model.ResponseMovieDTO;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public interface MovieLibraryCallback {
    void onSuccess(ResponseMovieDTO response);

    void onFailure(Throwable t);
}
