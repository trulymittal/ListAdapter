package com.example.listadapter;

import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        movieListAdapter = new MovieListAdapter( Movie.itemCallback , this);
        recyclerView.setAdapter(movieListAdapter);

        initMovieList();
    }

    private void initMovieList() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Captain America", "8"));
        movieList.add(new Movie("Iron Man", "7"));
        movieList.add(new Movie("Thor", "6"));

        movieListAdapter.submitList(movieList);
    }

    public void addItem(View view) {
        Movie movie = new Movie("Avenger's", "9");
        List<Movie> movieList = new ArrayList<>(movieListAdapter.getCurrentList());
        movieList.add(movie);
        movieListAdapter.submitList(movieList);
    }

    public void updateItem(View view) {
        int randomPostion = new Random().nextInt(movieListAdapter.getItemCount());
        Movie movie = movieListAdapter.getCurrentList().get(randomPostion);

        Movie updateMovie = new Movie(movie.getName(), movie.getRating());
        updateMovie.setId(movie.getId());
        updateMovie.setName(movie.getName() + " :updated");

        List<Movie> movieList = new ArrayList<>(movieListAdapter.getCurrentList());
        movieList.remove(movie);
        movieList.add(randomPostion, updateMovie);

        movieListAdapter.submitList(movieList);
    }

    @Override
    public void onDelete(int position) {
        List<Movie> movieList = new ArrayList<>(movieListAdapter.getCurrentList());
        movieList.remove(position);
        movieListAdapter.submitList(movieList);
    }
}
