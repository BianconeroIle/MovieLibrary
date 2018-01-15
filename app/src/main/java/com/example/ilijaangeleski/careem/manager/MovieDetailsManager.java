package com.example.ilijaangeleski.careem.manager;

import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.callback.MovieVideoCallback;
import com.example.ilijaangeleski.careem.model.ResponseMovieVideoDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public class MovieDetailsManager {
    private NetworkApi networkApi;

    public MovieDetailsManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchVideo(int movie_id ,final MovieVideoCallback callback){
        networkApi.fetchMovieVideo(NetworkApi.API_KEY,movie_id).enqueue(new Callback<ResponseMovieVideoDTO>() {
            @Override
            public void onResponse(Call<ResponseMovieVideoDTO> call, Response<ResponseMovieVideoDTO> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseMovieVideoDTO> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }


}
