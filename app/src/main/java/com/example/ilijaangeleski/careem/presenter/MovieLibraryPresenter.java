package com.example.ilijaangeleski.careem.presenter;

import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.callback.MovieLibraryCallback;
import com.example.ilijaangeleski.careem.callback.MovieLibrarySortCallback;
import com.example.ilijaangeleski.careem.manager.MovieLibraryManager;
import com.example.ilijaangeleski.careem.model.MovieDTO;
import com.example.ilijaangeleski.careem.model.ResponseMovieDTO;
import com.example.ilijaangeleski.careem.view.MovieLibraryView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public class MovieLibraryPresenter {
    private MovieLibraryManager libraryManager;
    private WeakReference<MovieLibraryView> movieLibraryViewWeakReference;
    private List<MovieDTO> movies = new ArrayList<>();
    private List<MovieDTO> sortedMovies = new ArrayList<>();
    private int page=1;


    public MovieLibraryPresenter(
            MovieLibraryManager libraryManager,
            WeakReference<MovieLibraryView> view
    ) {
        this.libraryManager = libraryManager;
        this.movieLibraryViewWeakReference = view;
    }

    public void fetchMovies() {
        libraryManager.fetchMovies(page,new MovieLibraryCallback() {
            @Override
            public void onSuccess(ResponseMovieDTO response) {
                MovieLibraryView view = movieLibraryViewWeakReference.get();
                if (view != null) {
                    if (response != null &&
                            response.getMovies() != null &&
                            !response.getMovies().isEmpty()) {
                        movies.addAll(response.getMovies());
                        view.notifyChanges();
                        page++;
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                MovieLibraryView view = movieLibraryViewWeakReference.get();
                if (view != null) {
                    view.showErrorGettingMoviesFromServer();
                }
            }
        });
    }

    public void fetchSortedMovies(boolean clear){
        if (clear) {
            movies.clear();
        }
        libraryManager.fetchSortedMovies(page,new MovieLibrarySortCallback() {
            @Override
            public void onSuccess(ResponseMovieDTO response) {
                MovieLibraryView view = movieLibraryViewWeakReference.get();
                if(view != null){
                    movies.addAll(response.getMovies());
                    view.notifyChanges();
                    page++;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MovieLibraryView view = movieLibraryViewWeakReference.get();
                if (view != null) {
                    view.showErrorGettingMoviesFromServer();
                }
            }
        });
    }
    public List<MovieDTO> getMovies() {
        return movies;
    }
}
