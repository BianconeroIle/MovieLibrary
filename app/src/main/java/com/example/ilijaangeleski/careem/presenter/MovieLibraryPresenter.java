package com.example.ilijaangeleski.careem.presenter;

import com.example.ilijaangeleski.careem.callback.MovieLibraryCallback;
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
    private int page=1;
    private String sortBy = MovieLibraryManager.sortASC;
    private String ASC = "ASC";
    private String DESC = "DESC";


    public MovieLibraryPresenter(
            MovieLibraryManager libraryManager,
            WeakReference<MovieLibraryView> view
    ) {
        this.libraryManager = libraryManager;
        this.movieLibraryViewWeakReference = view;
    }

    public void fetchMovies() {
        libraryManager.fetchMovies(page, sortBy, new MovieLibraryCallback() {
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

    public void sortMovies() {
        MovieLibraryView view = movieLibraryViewWeakReference.get();
        movies.clear();
        page = 1;
        if (sortBy.equals(MovieLibraryManager.sortASC)) {
            sortBy = MovieLibraryManager.sortDESC;
            view.sortByMessage(DESC);
        } else {
            sortBy = MovieLibraryManager.sortASC;
            view.sortByMessage(ASC);
        }
        fetchMovies();
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }
}
