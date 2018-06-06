package com.snehpandya.popularmoviesasync;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Movies>> {

    private static final String TMDB_URL = "http://api.themoviedb.org/3/movie/popular?api_key=";

    private static final int MOVIE_LOADER_ID = 1;

    MoviesAdapter mMovieAdapter;
    TextView mTextView;
    Movies currentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView moviesListView = (ListView) findViewById(R.id.list);
        mTextView = (TextView) findViewById(R.id.empty_view);
        moviesListView.setEmptyView(mTextView);

        mMovieAdapter = new MoviesAdapter(this, new ArrayList<Movies>());
        moviesListView.setAdapter(mMovieAdapter);

        moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentMovie = mMovieAdapter.getItem(position);


                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("title", currentMovie.getMovieTitle());
                intent.putExtra("image", currentMovie.getMovieImage());
                intent.putExtra("votes", currentMovie.getMovieRating());
                intent.putExtra("release", currentMovie.getMovieReleaseDate());
                intent.putExtra("description", currentMovie.getMovieDescription());
                startActivity(intent);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            android.app.LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(MOVIE_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(GONE);
            mTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Movies>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(TMDB_URL);
        Uri.Builder builder = baseUri.buildUpon();

        return new MoviesLoader(this, builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Movies>> loader, List<Movies> movies) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(GONE);

        mTextView.setText("No Movies Found");
        mMovieAdapter.clear();

        if (movies != null && !movies.isEmpty()) {
            mMovieAdapter.addAll(movies);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Movies>> loader) {
        mMovieAdapter.clear();
    }

}
