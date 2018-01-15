package com.example.ilijaangeleski.careem.callback;

import com.example.ilijaangeleski.careem.model.ResponseMovieDTO;
import com.example.ilijaangeleski.careem.model.ResponseMovieVideoDTO;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public interface MovieVideoCallback {
    void onSuccess(ResponseMovieVideoDTO response);

    void onFailure(Throwable t);
}
