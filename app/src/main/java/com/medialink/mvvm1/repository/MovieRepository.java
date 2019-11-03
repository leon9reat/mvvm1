package com.medialink.mvvm1.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.medialink.mvvm1.db.MovieDB;
import com.medialink.mvvm1.db.MovieDao;
import com.medialink.mvvm1.model.Movie;

import java.util.List;

public class MovieRepository {
    private MovieDao movieDao;
    private LiveData<List<Movie>> allMovie;

    public MovieRepository(Application application) {
        MovieDB db = MovieDB.getInstance(application);
        movieDao = db.movieDao();
        allMovie = movieDao.getAllMovie();
    }

    public void insertMovie(Movie movie) {
        new InsertMovieAsyncTask(movieDao).execute(movie);
    }

    public void updateMovie(Movie movie) {
        new UpdateMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteMovie(Movie movie) {
        new DeleteAllMovieAsyncTask(movieDao).execute(movie);
    }

    public void deleteAllMovies() {
        new DeleteAllMovieAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getAllMovie() {
        return allMovie;
    }

    private static class InsertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        public InsertMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insertMovie(movies[0]);
            return null;
        }
    }

    private static class UpdateMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        public UpdateMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.updateMovie(movies[0]);
            return null;
        }
    }

    private static class DeleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        public DeleteMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.deleteMovie(movies[0]);
            return null;
        }
    }

    private static class DeleteAllMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        public DeleteAllMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.deleteAllMovie();
            return null;
        }
    }


}
