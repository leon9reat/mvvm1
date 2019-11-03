package com.medialink.mvvm1.db;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.medialink.mvvm1.R;
import com.medialink.mvvm1.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDB extends RoomDatabase {
    public static final String DB_NAME = "MOVIE_DB";
    private static MovieDB instance;
    private static Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    public static synchronized MovieDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDB.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract MovieDao movieDao();

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private MovieDao movieDao;

        public PopulateDBAsyncTask(MovieDB instance) {
            this.movieDao = instance.movieDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            movieDao.insertMovie(new Movie("Avengers: Infinity War","Avengers: Infinity War is a 2018 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures.", R.drawable.ic_banner));
            movieDao.insertMovie(new Movie("Spider-Man","Spider-Man is a fictional superhero created by writer-editor Stan Lee and writer-artist Steve Ditko. He first appeared in the anthology comic book Amazing Fantasy #15 in the Silver Age of Comic Books.", R.drawable.ic_banner));
            movieDao.insertMovie(new Movie("Thor","Thor is exiled by his father Odin, the King of Asgard, to the Earth to live among mortals. When he lands on Earth, his trusted weapon Mjolnir is discovered and captured by S.H.I.E.L.D.", R.drawable.ic_banner));
            movieDao.insertMovie(new Movie("The Hulk","The Hulk is a fictional superhero appearing in publications by the American publisher Marvel Comics. Created by writer Stan Lee and artist Jack Kirby, the character first appeared in the debut issue of The Incredible Hulk.", R.drawable.ic_banner));

            return null;
        }
    }
}
