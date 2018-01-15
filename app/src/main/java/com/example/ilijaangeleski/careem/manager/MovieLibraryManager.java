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
    public static final String sortDESC = "release_date.desc";
    public static final String sortASC = "release_date.asc";


    public MovieLibraryManager(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    public void fetchMovies(int page, String sortBy, final MovieLibraryCallback callback) {
        networkApi.fetchMovies(NetworkApi.API_KEY, sortBy, page).enqueue(new Callback<ResponseMovieDTO>() {
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
