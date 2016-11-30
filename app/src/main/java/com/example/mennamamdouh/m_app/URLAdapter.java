package com.example.mennamamdouh.m_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 25/11/2016.
 */
public class URLAdapter extends BaseAdapter {
    ArrayList<Movie> movies;
    Context context;
    URLAdapter(ArrayList<Movie> movies, Context context)
    {
        this.context=context;
        this.movies=movies;
    }
    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position).getPoster_path_data();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView;
        if (convertView==null)
        {
            textView=new TextView(context);
            View view =View.inflate(context,R.layout.textview_single,null);
            textView=(TextView) view.findViewById(R.id.URL);


        }
        else
        {
            textView=(TextView) convertView;
        }
        textView.setText(movies.get(position).getPoster_path_data());
        return textView;
    }
}
