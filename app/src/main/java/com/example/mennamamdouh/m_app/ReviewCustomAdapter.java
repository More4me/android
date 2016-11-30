package com.example.mennamamdouh.m_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 27/11/2016.
 */
public class ReviewCustomAdapter extends BaseAdapter {
    ArrayList<Movie> movies;
    Context context;

    ReviewCustomAdapter(ArrayList<Movie> movies,Context context)
    {
        this.movies=movies;
        this.context=context;
    }
    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      //  ImageView imageView;
        TextView authorName;
        TextView reviewScript;
        View view=null;
        if (convertView == null) {
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = lInflater.inflate(R.layout.review_row_single, null);
        }
        authorName=(TextView)convertView.findViewById(R.id.authorName);
        authorName.setText(movies.get(position).getOriginal_title_data());
        reviewScript=(TextView)convertView.findViewById(R.id.movie_overview);
        reviewScript.setText(movies.get(position).getOverview_data());
        return convertView;
    }
}
