package com.medialink.mvvm1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.medialink.mvvm1.AddMovieActivity;
import com.medialink.mvvm1.R;
import com.medialink.mvvm1.constant.Const;
import com.medialink.mvvm1.model.Movie;
import com.medialink.mvvm1.ui.adapter.MovieAdapter;
import com.medialink.mvvm1.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private static final int ADD_MOVIE_ID = 101;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private FloatingActionButton addFab;

    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "onCreate()");

        initViews();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MovieAdapter adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getAllMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieList) {
                adapter.setMovie(movieList);
            }
        });

        handlerListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_delete_all_movie) {
            viewModel.deleteAllMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handlerListener() {
        addFab.setOnClickListener(this);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        addFab = findViewById(R.id.fab);
    }

    @Override
    public void onClick(View v) {
        if (v == addFab) {
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivityForResult(intent, ADD_MOVIE_ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MOVIE_ID && resultCode == RESULT_OK) {
            if (data != null) {
                String title = data.getStringExtra(Const.TITLE);
                String desc = data.getStringExtra(Const.DESC);
                viewModel.insertMovie(new Movie(title, desc, R.drawable.ic_launcher_background));
            }
        }
    }
}
