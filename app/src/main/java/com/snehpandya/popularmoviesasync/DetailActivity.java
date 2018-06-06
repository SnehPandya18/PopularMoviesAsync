package com.snehpandya.popularmoviesasync;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sneh.pandya on 23/06/17.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        TextView titleTextView = (TextView) findViewById(R.id.textdetails1);
        titleTextView.setText(title);

        String votes = intent.getStringExtra("votes");
        TextView votesTextView = (TextView) findViewById(R.id.textdetails2);
        votesTextView.setText(votes);

        String release = intent.getStringExtra("release");
        TextView releaseTextView = (TextView) findViewById(R.id.textdetails3);
        releaseTextView.setText(release);

        String description = intent.getStringExtra("description");
        TextView descriptionTextView = (TextView) findViewById(R.id.textdetails4);
        descriptionTextView.setText(description);

        String image = intent.getStringExtra("image");

        ImageView imageView = (ImageView) findViewById(R.id.imageviewDetail);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w780" + image).resize(400, 600).into(imageView);
    }
}