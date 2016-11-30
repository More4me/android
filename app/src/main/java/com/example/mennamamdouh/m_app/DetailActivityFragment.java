package com.example.mennamamdouh.m_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    Movie movie;
    TextView v1;
    TextView v2;
    TextView v3;
    TextView v4;
    ImageView I1;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail, container, false);
        Button button = (Button) view.findViewById(R.id.saveDatabase);
        Button trailerButton=(Button) view.findViewById(R.id.opentrailer);
        Button reviewsButton=(Button) view.findViewById(R.id.viewreviews);
        v1 = (TextView) view.findViewById(R.id.movie_title);
        v2 = (TextView) view.findViewById(R.id.movie_overview);
        v3 = (TextView) view.findViewById(R.id.vote_average);
        v4 = (TextView) view.findViewById(R.id.released_date);
        I1 = (ImageView) view.findViewById(R.id.moive_picture);

       // v1=view.findViewById(R.id.);
        boolean tablet=MainActivityFragment.isTablet(getActivity());
        if(!tablet) {
            Bundle b = getActivity().getIntent().getExtras();

            movie = b.getParcelable("Movie_data");


        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInDatabase(v);
            }
        });

        trailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_trailer();
            }
        });

        reviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_review();
            }
        });
        return view;
    }

    private void view_review() {
        Intent i = new Intent(getActivity(), ReviewMovie.class);
        i.putExtra("movie", movie);
        startActivity(i);
        // no animation in transaction
        (getActivity()).overridePendingTransition(0, 0);
    }

    public void open_trailer() {

        Intent i = new Intent(getActivity(), trailer.class);
        i.putExtra("movie", movie);
        startActivity(i);
        // no animation in transaction
        (getActivity()).overridePendingTransition(0, 0);
    }

    public void saveInDatabase(View view) {
        FavouriteDatabaseHelper fav=new FavouriteDatabaseHelper(getActivity());
        SQLiteDatabase favdatabase = fav.getWritableDatabase();
        fav.setOurDatabase(favdatabase);

        if(!fav.selectFromDatabase(movie) ) {
            if (fav.insertMovie(favdatabase, movie)) {

                Toast.makeText(getActivity(), "Added successfully", Toast.LENGTH_SHORT);
           }
        }
        else {
            Toast.makeText(getActivity(),"already in your favourite",Toast.LENGTH_SHORT);

        }

    }

    public void changeData(Movie movie) {
        this.movie = movie;

        v1.setText(movie.getOriginal_title_data());
        v2.setText(movie.getOverview_data());
        v3.setText(movie.getRelease_date_data());
        v4.setText(String.valueOf(movie.getVote_average_data()));
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342/"+movie.getPoster_path_data()).into(I1);
    }


}
