package com.example.ilijaangeleski.careem.view;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public interface MovieLibraryView {
    void notifyChanges();

    void showErrorGettingMoviesFromServer();

    void sortByMessage(String sortBy);

}
