package com.snehpandya.popularmoviesasync;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by sneh.pandya on 23/06/17.
 */

public class MoviesLoader extends AsyncTaskLoader<List<Movies>> {

    private String mUrl;

    public MoviesLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movies> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<Movies> movies = Utils.fetchMoviesdata(mUrl);
        return movies;
    }
}
