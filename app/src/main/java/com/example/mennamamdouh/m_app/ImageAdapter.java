package com.example.mennamamdouh.m_app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 11/11/2016.
 */
public class ImageAdapter extends BaseAdapter {

        ArrayList<Movie> movie;
        Context context;
        public ImageAdapter(ArrayList<Movie> movies,Context context) {
            movie=movies;
            this.context=context;
        }

        public int getCount() {
            return movie.size();
        }

        public Movie getItem(int position) {
            return movie.get(position);
        }

        public long getItemId(int position) {
            return movie.get(position).getId_data();
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
//                imageView = new ImageView(context);
//                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                imageView.setPadding(8, 8, 8, 8);
                View view=View.inflate(context,R.layout.grid_single,null);
                imageView = (ImageView)view.findViewById(R.id.grid_image);

            } else {
                imageView = (ImageView) convertView;
            }
            Picasso.with(context).load("http://image.tmdb.org/t/p/w342/"+movie.get(position).getPoster_path_data()).into(imageView);
            Log.v("fetchMovie","load img "+movie.get(position).getOriginal_title_data());
            return imageView;
        }
}