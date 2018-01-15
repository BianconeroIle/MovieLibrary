package com.example.ilijaangeleski.careem.ui;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ilijaangeleski.careem.MyApp;
import com.example.ilijaangeleski.careem.R;
import com.example.ilijaangeleski.careem.adapter.MovieLibraryAdapter;
import com.example.ilijaangeleski.careem.di.components.DaggerMovieLibraryActivityComponent;
import com.example.ilijaangeleski.careem.di.modules.MovieLibraryActivityModule;
import com.example.ilijaangeleski.careem.model.MovieDTO;
import com.example.ilijaangeleski.careem.presenter.MovieLibraryPresenter;
import com.example.ilijaangeleski.careem.util.EndlessRecyclerViewScrollListener;
import com.example.ilijaangeleski.careem.util.SpacesItemDecoration;
import com.example.ilijaangeleski.careem.view.MovieLibraryView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieLibraryActivity extends AppCompatActivity implements MovieLibraryView {
    @BindView(R.id.movieRecyclerView)
    RecyclerView movieRecyclerView;
    @Inject
    MovieLibraryPresenter presenter;
    private MovieLibraryAdapter libraryAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_library);
        ButterKnife.bind(this);
        createDependencies();
        initView();
        initListeners();
        presenter.fetchMovies();
    }

    public void initView() {
        libraryAdapter = new MovieLibraryAdapter(presenter.getMovies());
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        movieRecyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.cardview_default_elevation);
        movieRecyclerView.setAdapter(libraryAdapter);
        movieRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.fetchMovies();
            }
        };
        movieRecyclerView.addOnScrollListener(scrollListener);
    }

    private void initListeners() {
        libraryAdapter.setOnUserItemClick(new MovieLibraryAdapter.OnMovieItemClicked() {
            @Override
            public void onMovieItemClicked(MovieDTO movie, ImageView movieImage) {
                Log.d("", "onMovieClick :" + movie);
                openMovieDetailsActivity(movie, movieImage);
            }
        });
    }

    private void createDependencies() {
        DaggerMovieLibraryActivityComponent
                .builder()
                .movieLibraryActivityModule(new MovieLibraryActivityModule(this))
                .baseComponent(MyApp.getMyApp().getBaseComponent())
                .build()
                .inject(this);
    }

    private void openMovieDetailsActivity(MovieDTO movie, ImageView movieImage) {
        Intent intent = new Intent(MovieLibraryActivity.this, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.MOVIEDB_EXTRA, movie);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MovieLibraryActivity.this, movieImage, "profile");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void notifyChanges() {
        libraryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorGettingMoviesFromServer() {
        Toast.makeText(this, R.string.show_error, Toast.LENGTH_LONG).show();
    }
}
