package com.example.ilijaangeleski.careem.presenter;

import com.example.ilijaangeleski.careem.callback.MovieVideoCallback;
import com.example.ilijaangeleski.careem.manager.MovieDetailsManager;
import com.example.ilijaangeleski.careem.model.MovieVideoDTO;
import com.example.ilijaangeleski.careem.model.ResponseMovieVideoDTO;
import com.example.ilijaangeleski.careem.view.MovieDetailsView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public class MovieDetailsPresenter {
    private MovieDetailsManager detailsManager;
    private WeakReference<MovieDetailsView> movieDetailsWeakReference;
    private List<MovieVideoDTO> video = new ArrayList<>();
    private ResponseMovieVideoDTO movieVideoDTO;

    public MovieDetailsPresenter(
            MovieDetailsManager detailsManager,
            WeakReference<MovieDetailsView> view
    ) {
        this.detailsManager = detailsManager;
        this.movieDetailsWeakReference = view;
    }

    public void fetchVideos(int movie_id){
        detailsManager.fetchVideo(movie_id, new MovieVideoCallback() {
            @Override
            public void onSuccess(ResponseMovieVideoDTO response) {
                MovieDetailsView view = movieDetailsWeakReference.get();
                if(view != null){
                    if(response != null
                            && response.getVideo() != null
                            && !response.getVideo().isEmpty()){
                        movieVideoDTO=response;
                        video.addAll(response.getVideo());
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                MovieDetailsView view = movieDetailsWeakReference.get();
                if(view != null){
                    view.showErrorGettingVideoFromServer();
                }
            }
        });
    }

    public String getVideoKey() {
        if (movieVideoDTO != null) {
            List<MovieVideoDTO> trailers = movieVideoDTO.getVideo();
            for (MovieVideoDTO video : trailers) {
                if (video.getSite().equals("YouTube")) {
                    return video.getKey();
                }
            }
        }
        return "";
    }

    public List<MovieVideoDTO> getVideo() {
        return video;
    }
}

