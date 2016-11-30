package com.example.mennamamdouh.m_app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 25/11/2016.
 */
public class PopularjsonParsing implements jsonParsing {
    public String LOG_TAG=PopularjsonParsing.class.getSimpleName();
    @Override
    public ArrayList<Movie> jsonParsing(String forecastJsonStr)
    {
        final String poster_path="poster_path";
        final String overview="overview";
        final String original_title="original_title";
        final String release_date="release_date";
        final String id="id";
        final String vote_average="vote_average";
        ArrayList<Movie> movie=new ArrayList<>();

        try {
            JSONObject DataJson = new JSONObject(forecastJsonStr);
            JSONArray resultArray = DataJson.getJSONArray("results");
            Log.v(LOG_TAG, String.valueOf(resultArray.length()));
            for (int i = 0; i < resultArray.length(); i++) {
                Movie temp=new Movie();
                JSONObject movieData = resultArray.getJSONObject(i);
                temp.setPoster_path_data(movieData.getString(poster_path));
                temp.setOverview_data(movieData.getString(overview));
                temp.setOriginal_title_data(movieData.getString(original_title));
                temp.setRelease_date_data(movieData.getString(release_date));
                temp.setId_data(movieData.getInt(id));
                temp.setVote_average_data(movieData.getDouble(vote_average));
                Log.v(LOG_TAG,temp.getPoster_path_data()+" , "+temp.getOverview_data()+" , "
                        +temp.getOriginal_title_data()+" , "+temp.getRelease_date_data()+" , "+
                        temp.getId_data()+" , "+temp.getVote_average_data()+" . ");
                movie.add(temp);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return movie;

    }




}
