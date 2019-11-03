package com.medialink.mvvm1.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.medialink.mvvm1.R;
import com.medialink.mvvm1.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private List<Movie> movieList;

    public MovieAdapter() {
    }

    public void setMovie(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        final Movie movie = movieList.get(position);
        holder.titleTxt.setText(movie.getName());
        holder.descTxt.setText(movie.getDescription());
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, descTxt;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.title_txt);
            descTxt = itemView.findViewById(R.id.desc_txt);
        }
    }
}
