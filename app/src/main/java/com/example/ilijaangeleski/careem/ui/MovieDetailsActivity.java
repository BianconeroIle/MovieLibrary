package com.example.ilijaangeleski.careem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilijaangeleski.careem.R;
import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.model.GenreDTO;
import com.example.ilijaangeleski.careem.model.MovieDTO;
import com.example.ilijaangeleski.careem.presenter.MovieDetailsPresenter;
import com.example.ilijaangeleski.careem.util.AppPreference;
import com.example.ilijaangeleski.careem.view.MovieDetailsView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener, MovieDetailsView {
    public static final String MOVIEDB_EXTRA = "movie";
    @BindView(R.id.tv_titleMovie)
    TextView tv_titleMovie;
    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_overview_description)
    TextView tv_overview_description;
    @BindView(R.id.tv_rating)
    TextView tv_rating;
    @BindView(R.id.tv_release_date)
    TextView tv_release_date;
    @BindView(R.id.tv_genres)
    TextView tv_genres;
    @BindView(R.id.tv_playtxt)
    TextView tv_playtxt;
    @BindView(R.id.trailerLayout)
    RelativeLayout trailerLayout;
    private MediaController mediaControls;
    private MovieDTO movie;
    private AppPreference preference;

    @Inject
    MovieDetailsPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null
                && getIntent().hasExtra(MOVIEDB_EXTRA)) {
            movie = (MovieDTO) getIntent().getExtras().getSerializable(MOVIEDB_EXTRA);
        }
        initView();
//        presenter.fetchVideos(movie.getId());

    }

    private void initView() {
        preference = new AppPreference(this);
        tv_titleMovie.setText(movie.getTitle());
        tv_overview_description.setText(movie.getOverview());
        tv_rating.setText(movie.getVoteAverage() + "");
        tv_genres.setText(getMovieGenres());
        tv_playtxt.setOnClickListener(this);

        if (mediaControls == null) {
            mediaControls = new MediaController(MovieDetailsActivity.this);
        }
        trailerLayout.setOnClickListener(this);

        try {
            tv_release_date.setText(formatReleaseDate(movie.getReleaseDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            tv_release_date.setText(movie.getReleaseDate());
        }

        Picasso.with(this).load(NetworkApi.IMAGE_BASE_URL + movie.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv_avatar);
    }
    private void openYouTubeVideo(){
        Intent i = new Intent(MovieDetailsActivity.this, YouTubePlayerActivity.class);
        i.putExtra("youtubeVideoKey", presenter.getVideoKey());
        startActivity(i);
    }

    @Override
    public void showErrorGettingVideoFromServer() {
        Toast.makeText(this,R.string.show_error_getting_video,Toast.LENGTH_LONG).show();
    }

    private String formatReleaseDate(String movieReleaseServerDate) throws ParseException {
        Date releaseServerDate;
        releaseServerDate = new SimpleDateFormat("yyyy-MM-dd").parse(movieReleaseServerDate);
        return new SimpleDateFormat("d MMM yyyy").format(releaseServerDate);
    }

    private String getMovieGenres() {
        List<GenreDTO> genres = preference.getMovieGenres();
        String movieGenres = "";
        for (int i = 0; i < movie.getGenreIds().length; i++) {
            int genreId = movie.getGenreIds()[i];
            for (GenreDTO genre : genres) {
                if (genre.getId() == genreId) {
                    movieGenres += genre.getName() + ", ";
                }
            }
        }
        return movieGenres;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_playtxt:
                openYouTubeVideo();
                break;
        }
    }
}
