package com.snehpandya.popularmoviesasync;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends ArrayAdapter<Movies> {

    public MoviesAdapter(Context context, List<Movies> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Movies currentMovie = getItem(position);

        ImageView movieImage = (ImageView) listItemView.findViewById(R.id.imageview);
        movieImage.setAdjustViewBounds(true);

        Log.d("ImageData:", currentMovie.getMovieImage());
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w780" + currentMovie.getMovieImage()).into(movieImage);

        return listItemView;
    }
}
