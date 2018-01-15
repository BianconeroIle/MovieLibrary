package com.example.ilijaangeleski.careem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.careem.R;
import com.example.ilijaangeleski.careem.api.NetworkApi;
import com.example.ilijaangeleski.careem.model.MovieDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ilija Angeleski on 1/14/2018.
 */

public class MovieLibraryAdapter extends RecyclerView.Adapter<MovieLibraryAdapter.MyViewHolder> {
    private List<MovieDTO> movie;
    private OnMovieItemClicked listener;

    public MovieLibraryAdapter(List<MovieDTO> movie) {
        this.movie = movie;
    }

    public interface OnMovieItemClicked {
        void onMovieItemClicked(MovieDTO movie, ImageView profileImage);
    }

    @Override
    public MovieLibraryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_movielibray, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MovieLibraryAdapter.MyViewHolder holder, int position) {
        final MovieDTO current = movie.get(position);
        holder.movieTitle.setText(current.getTitle());
        Picasso.with(holder.movieImage.getContext()).
                load(NetworkApi.IMAGE_BASE_URL +
                current.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.movieImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onMovieItemClicked(current, holder.movieImage);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieImage)
        ImageView movieImage;
        @BindView(R.id.movieTitle)
        TextView movieTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnUserItemClick(OnMovieItemClicked listener) {
        this.listener = listener;
    }
}
