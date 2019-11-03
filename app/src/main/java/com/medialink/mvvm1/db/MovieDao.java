package com.medialink.mvvm1.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.medialink.mvvm1.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert
    void insertMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("DELETE FROM movie_table")
    void deleteAllMovie();

    @Query("SELECT * FROM movie_table ORDER BY id DESC")
    LiveData<List<Movie>> getAllMovie();
}
