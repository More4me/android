package com.example.mennamamdouh.m_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    Movie movie;
//    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle b = getIntent().getExtras();

        movie = b.getParcelable("Movie_data");
        TextView textView = (TextView)findViewById(R.id.movie_overview);
        textView.setText(movie.getOverview_data());

        TextView movieTitle = (TextView) findViewById(R.id.movie_title);
        movieTitle.setText(movie.getOriginal_title_data());

        ImageView imageView = (ImageView) findViewById(R.id.moive_picture);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/"+movie.getPoster_path_data()).into(imageView);

        TextView vote_averge=(TextView) findViewById(R.id.vote_average);
        vote_averge.setText(String.valueOf(movie.getVote_average_data()));

        TextView released_date=(TextView) findViewById(R.id.released_date);
        released_date.setText(movie.getRelease_date_data());

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }






}
