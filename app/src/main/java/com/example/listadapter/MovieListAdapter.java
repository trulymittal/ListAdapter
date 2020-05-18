package com.example.listadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends ListAdapter<Movie, MovieListAdapter.MovieViewHolder> {

    MovieClickInterface movieClickInterface;

    protected MovieListAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, MovieClickInterface movieClickInterface) {
        super(diffCallback);
        this.movieClickInterface = movieClickInterface;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.bind(movie);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "MovieViewHolder";
        TextView nameTextView, ratingTextView;
        ImageButton deleteButton;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextview);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieClickInterface.onDelete(getAdapterPosition());
                }
            });
        }

        public void bind(Movie movie) {
            nameTextView.setText(movie.getName());
            ratingTextView.setText(movie.getRating());
        }
    }

    interface MovieClickInterface {
        public void onDelete(int position);
    }
}
