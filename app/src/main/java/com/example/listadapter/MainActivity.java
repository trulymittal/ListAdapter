package com.example.listadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.MovieClickInterface {

    private static final String TAG = "MainActivity";
    private MovieListAdapter movieListAdapter;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        movieListAdapter = new MovieListAdapter( Movie.itemCallback , this);
        recyclerView.setAdapter(movieListAdapter);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getMovieList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieListAdapter.submitList(movies);
            }
        });

    }

    public void addItem(View view) {
        Movie movie = new Movie("Avenger's", "9");
        movieViewModel.addMovie(movie);
    }

    public void updateItem(View view) {
        int randomPostion = new Random().nextInt(movieListAdapter.getItemCount());
        Movie movie = movieListAdapter.getCurrentList().get(randomPostion);

        Movie updateMovie = new Movie(movie.getName(), movie.getRating());
        updateMovie.setId(movie.getId());
        updateMovie.setName(movie.getName() + " :updated");

        movieViewModel.updateMovie(updateMovie, randomPostion);
    }

    @Override
    public void onDelete(int position) {
        movieViewModel.deleteMovie(position);
    }
}
