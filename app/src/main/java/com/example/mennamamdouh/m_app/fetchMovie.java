package com.example.mennamamdouh.m_app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Menna Mamdouh on 25/11/2016.
 */
public class fetchMovie extends AsyncTask<String, Void, ArrayList<Movie>> {
    private final String LOG_TAG = fetchMovie.class.getSimpleName();
    public String MovieType="popular";
    Display mainActivityFragment;

    public fetchMovie(Display mainActivityFragment) {
        this.mainActivityFragment = mainActivityFragment;
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        MovieType=params[0].trim();
        try {

            URL url = new URL("http://api.themoviedb.org/3/movie/"+MovieType+"?api_key=31db33183c10fe474589c4dd6b600027");

            Log.v(LOG_TAG, "Built URI " + url);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                Log.v(LOG_TAG, "Buufer Length" + buffer.length());
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.v(LOG_TAG, forecastJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        if(MovieType.equals("popular")||MovieType.equals("top_rated"))
        {
            PopularjsonParsing p=new PopularjsonParsing();
            return  p.jsonParsing(forecastJsonStr);
        }
        else if(MovieType.contains("videos")) {
            TrailerjsonParsing t=new TrailerjsonParsing();
            return t.jsonParsing(forecastJsonStr);
        }
        else if(MovieType.contains("reviews"))
        {
            ReviewjsonParsing t=new ReviewjsonParsing();
            return t.jsonParsing(forecastJsonStr);
        }
        return null;
    }


    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        Log.v("Movies" , String.valueOf(movies.size()));
        mainActivityFragment.display(movies);

    }
}
