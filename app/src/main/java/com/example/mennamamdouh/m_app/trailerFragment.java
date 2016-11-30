package com.example.mennamamdouh.m_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class trailerFragment extends Fragment  implements Display ,AdapterView.OnItemClickListener {
    ArrayList<Movie> trailerUrl;
    ListView lv;
    public trailerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_trailer, container, false);
        lv = (ListView)view.findViewById(R.id.movietrailer);

        Movie movie=getActivity().
                getIntent().getExtras().getParcelable("movie");

        Toast.makeText(getActivity(),movie.getOriginal_title_data(), Toast.LENGTH_SHORT).show();

        if(MainActivityFragment.activeInternerConnection(getActivity())) {
            fetchMovie f=new fetchMovie(this);
            f.execute(movie.getId_data() + "/videos");
        }
        else{
            Toast.makeText(getActivity(), "There is no internet connection", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @Override
    public void display(ArrayList<Movie> movies) {
        String[] trailerNumers=new String[movies.size()];
        trailerUrl=movies;
        for(int i=0;i<movies.size();i++)
        {
            trailerNumers[i]="trailer"+i;
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(),
                R.layout.list_single,
                trailerNumers);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/watch?v=" + trailerUrl.get(position).getPoster_path_data()));
        startActivity(intent);

    }

}
