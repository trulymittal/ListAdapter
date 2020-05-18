package com.example.listadapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends ViewModel {

    private static final String TAG = "MovieViewModel";
    private MutableLiveData<List<Movie>> mutableLiveData;

    public LiveData<List<Movie>> getMovieList() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            initMovieList();
        }
        return mutableLiveData;
    }

    private void initMovieList() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Captain America", "8"));
        movieList.add(new Movie("Iron Man", "7"));
        movieList.add(new Movie("Thor", "6"));
        mutableLiveData.setValue(movieList);
    }

    public void deleteMovie(int position) {
        if (mutableLiveData.getValue() != null) {
            List<Movie> movieList = new ArrayList<>(mutableLiveData.getValue());
            movieList.remove(position);
            mutableLiveData.setValue(movieList);
        }
    }

    public void addMovie(Movie movie) {
        if (mutableLiveData.getValue() != null) {
            List<Movie> movieList = new ArrayList<>(mutableLiveData.getValue());
            movieList.add(movie);
            mutableLiveData.setValue(movieList);
        }
    }

    public void updateMovie(Movie newMovie, int position) {
        if (mutableLiveData.getValue() != null) {
            List<Movie> movieList = new ArrayList<>(mutableLiveData.getValue());
            movieList.remove(position);
            movieList.add(position, newMovie);
            mutableLiveData.setValue(movieList);
        }
    }

}
