package com.example.mennamamdouh.m_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReviewMovieFragment extends Fragment implements Display {
    ArrayList<Movie> m;
    ListView textView;
    public ReviewMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_review_movie, container, false);
        Movie movie=getActivity().getIntent().getExtras().getParcelable("movie");
        textView=(ListView) view.findViewById(R.id.review_list);
        if(MainActivityFragment.activeInternerConnection(getActivity())) {
            fetchMovie f=new fetchMovie(this);
            f.execute(movie.getId_data() + "/reviews");
        }
        else{
            Toast.makeText(getActivity(), "There is no internet connection", Toast.LENGTH_LONG).show();
        }

        return view;

    }

    @Override
    public void display(ArrayList<Movie> movies) {
        m=movies;
        textView.setAdapter(new ReviewCustomAdapter(m, getActivity())); // shyf el gridview b null
       // progressBar.setVisibility(View.GONE);

    }
}
