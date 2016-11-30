package com.example.mennamamdouh.m_app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements Display {
     GridView gridview;
    String LOG_TAG = "";
    ProgressBar progressBar;
    Communicator comm;


    private ArrayList<Movie> movie;
    public MainActivityFragment() {
    }

    public static boolean activeInternerConnection(Context c)
    {
        ConnectivityManager cm=(ConnectivityManager)c.getSystemService(c.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo()!=null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        movie = new ArrayList<>();
        gridview = (GridView)rootView.findViewById(R.id.grid);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                boolean tablet=isTablet(getActivity());
                if(!tablet) {

                    Intent i = new Intent(getActivity(), DetailActivity.class);
                    i.putExtra("Movie_data", movie.get(position));


                    startActivity(i);
                }
                else{

                    comm.respond(movie.get(position));
                }


            }
        });
        if(savedInstanceState != null)
        {
            movie=savedInstanceState.getParcelableArrayList("MOVIE");
            gridview.setAdapter(new ImageAdapter(movie,getActivity()));
            progressBar.setVisibility(View.GONE);
        }
        else {

            if(activeInternerConnection(getActivity())){
                fetchMovie f = new fetchMovie(this);
                f.execute("top_rated");
            }
            else{
                Toast.makeText(getActivity(),"No Internet connection",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        }

        return rootView;

    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.ByPopularity)
        {

            fetchMovie f=new fetchMovie(this);
            f.execute("popular");
            return true;
        }
        else if(item.getItemId()==R.id.ByTopRated)
        {
            fetchMovie f=new fetchMovie(this);
            f.execute("top_rated");
            return true;
        }

        else if (item.getItemId() == R.id.favorite){
            FavouriteDatabaseHelper databaseHelper = new FavouriteDatabaseHelper(getActivity());
            ArrayList<Movie> list = databaseHelper.getAllMovies();
            gridview.setAdapter(new ImageAdapter(list,getActivity()));
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MOVIE", movie);

        super.onSaveInstanceState(outState);
    }


    @Override
    public void display(ArrayList<Movie> movies) {
        movie=movies;
        gridview.setAdapter(new ImageAdapter(movies, getActivity()));
        progressBar.setVisibility(View.GONE);
    }
    public interface Communicator{
        public void respond(Movie movie);
    }
}
