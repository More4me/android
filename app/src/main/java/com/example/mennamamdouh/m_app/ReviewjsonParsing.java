package com.example.mennamamdouh.m_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 27/11/2016.
 */
public class ReviewjsonParsing implements jsonParsing {
    @Override
    public ArrayList<Movie> jsonParsing(String forecastJsonStr) {
        //title author , overview content , id
        /**
         * "id":"54c82e03c3a36870ba000a3d",
         "author":"MJM",
         "content":
         */
        final String id_key="id";
        final String author_key="author";
        final String content_key="content";
        final String url_key="url";
        ArrayList<Movie> movie=new ArrayList<>();
        try {
            JSONObject DataJson = new JSONObject(forecastJsonStr);
            JSONArray resultArray = DataJson.getJSONArray("results");
            Log.v("Menna", String.valueOf(resultArray.length()));
            for (int i = 0; i < resultArray.length(); i++) {
                Movie temp=new Movie();
                JSONObject movieData = resultArray.getJSONObject(i);

                temp.setOriginal_title_data(movieData.getString(author_key));
                temp.setOverview_data(movieData.getString(content_key));
                temp.setPoster_path_data(movieData.getString(url_key));
                movie.add(temp);

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }
}
