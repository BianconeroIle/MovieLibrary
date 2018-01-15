package com.example.ilijaangeleski.careem.api;

import com.example.ilijaangeleski.careem.model.ResponseMovieDTO;
import com.example.ilijaangeleski.careem.model.ResponseMovieVideoDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public interface NetworkApi {

    String BASE_URL = "http://api.themoviedb.org/3/";
    String IMAGE_BASE_SIZE = "w500";
    String API_KEY = "5dc2dfbabf3e85a3de3790440b219fca";
    String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/" + IMAGE_BASE_SIZE + "";

    @GET("/movie/{movie_id}/videos")
    Call<ResponseMovieVideoDTO> fetchMovieVideo(@Query("api_key") String apiKey, @Path("movie_id") int movie_id);


    @GET("movie/now_playing")
    Call<ResponseMovieDTO> fetchMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy, @Query("page") int page);
}
