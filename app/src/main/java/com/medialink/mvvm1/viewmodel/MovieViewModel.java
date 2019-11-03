package com.medialink.mvvm1.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.medialink.mvvm1.model.Movie;
import com.medialink.mvvm1.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;
    private LiveData<List<Movie>> allMovie;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        allMovie = movieRepository.getAllMovie();
    }

    public void insertMovie(Movie movie) {
        movieRepository.insertMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieRepository.updateMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.deleteMovie(movie);
    }

    public void deleteAllMovie() {
        movieRepository.deleteAllMovies();
    }

    public LiveData<List<Movie>> getAllMovie() {
        return allMovie;
    }
}
