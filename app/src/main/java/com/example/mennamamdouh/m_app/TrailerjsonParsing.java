package com.example.mennamamdouh.m_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 25/11/2016.
 */
public class TrailerjsonParsing implements jsonParsing {
    public String LOG_TAG=TrailerjsonParsing.this.toString();
    @Override
    public ArrayList<Movie> jsonParsing(String forecastJsonStr)
    {
        ArrayList<Movie> trailerLinks=new ArrayList<>();

        try {
            JSONObject DataJson = new JSONObject(forecastJsonStr);
            JSONArray resultArray = DataJson.getJSONArray("results");
            Log.v(LOG_TAG, String.valueOf(resultArray.length()));
            for (int i = 0; i < resultArray.length(); i++) {
                Movie movie=new Movie();
                JSONObject movieData = resultArray.getJSONObject(i);
                movie.setPoster_path_data(movieData.getString("key"));
                Log.v(LOG_TAG, movie.getPoster_path_data());
                trailerLinks.add(movie);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return trailerLinks;
    }




}
