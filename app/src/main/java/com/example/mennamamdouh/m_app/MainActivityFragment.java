package com.example.mennamamdouh.m_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    GridView gridview;
    String LOG_TAG = "";
    ProgressBar progressBar;

    private ArrayList<Movie> movie;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        movie = new ArrayList<>();
        fetchMovie f=new fetchMovie();
        f.execute();
      //  View view=View.inflate(getActivity(),R.layout.fragment_main);
        gridview = (GridView)rootView.findViewById(R.id.grid);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(), "" + position,
                        Toast.LENGTH_SHORT).show();
                // Explicit Intent by specifying its class name
                Intent i = new Intent(getActivity(), DisplayMovieDetails.class);
                i.putExtra("Movie_data", movie.get(position).getOverview_data());

                // Starts TargetActivity
                startActivity(i);
            }
        });
        return rootView;

    }

    private ArrayList<Movie> jsonParsingData(String forecastJsonStr)
    {
        final String poster_path="poster_path";
        final String overview="overview";
        final String original_title="original_title";
        final String release_date="release_date";
        final String id="id";
        final String vote_average="vote_average";


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
//        Log.v(LOG_TAG, "Movie data after json parcsing");
//        for(int i=0;i<movie.size();i++) {
//            Log.v(LOG_TAG, movie.get(i).print());
//        }

        return movie;

//            gridview.setAdapter(new ImageAdapter(movie,getActivity()));
    }

    class fetchMovie extends AsyncTask<Void, Void, ArrayList<Movie>> {
        private final String LOG_TAG = fetchMovie.class.getSimpleName();

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String forecastJsonStr = null;
            try {

                URL url = new URL("http://api.themoviedb.org/3/movie/popular?api_key=31db33183c10fe474589c4dd6b600027");

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
            return jsonParsingData(forecastJsonStr);
        }


        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            gridview.setAdapter(new ImageAdapter(movies,getActivity()));
            progressBar.setVisibility(View.GONE);
        }
    }


}
