package com.example.ilijaangeleski.careem.manager;

import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.callback.MovieLibraryCallback;
import com.example.ilijaangeleski.careem.model.ResponseMovieDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public class MovieLibraryManager {
    private NetworkApi networkApi;

    public MovieLibraryManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchMovies(int page,final MovieLibraryCallback callback){
        networkApi.fetchMovies(NetworkApi.API_KEY ,page).enqueue(new Callback<ResponseMovieDTO>() {
            @Override
            public void onResponse(Call<ResponseMovieDTO> call, Response<ResponseMovieDTO> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseMovieDTO> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
