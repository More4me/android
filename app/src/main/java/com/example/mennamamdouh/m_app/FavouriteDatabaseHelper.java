package com.example.mennamamdouh.m_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Menna Mamdouh on 25/11/2016.
 */
public class FavouriteDatabaseHelper extends SQLiteOpenHelper{
    final static String DB_NAME="f_movie";
    final static int DB_VERSION=3;
    final static String TABLE_NAME="movies_details";
    final static  String MOVIE_ID="id_data";
    final static  String FAV_MOVIE_ID="fav_id_data";
    final static String TITLE="original_title_data";
    final static String POSTER="poster_path_data";
    final static String OVERVIEW="overview_data";
    final static String RELEASE_DATE="release_date_data";
    final static String VOTES="vote_average_data";
    Context context;
    SQLiteDatabase db;

    public FavouriteDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        try {
            String query = "CREATE TABLE " + TABLE_NAME
                    + " (" + FAV_MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MOVIE_ID +" INTEGER NOT NULL, "
                    + TITLE + " TEXT NOT NULL, "
                    + OVERVIEW + " TEXT NOT NULL, "
                    + RELEASE_DATE + " TEXT NOT NULL, "
                    + VOTES + " REAL NOT NULL, "
                    + POSTER + " TEXT NOT NULL "
                    + ");";
            db.execSQL(query.trim());

        }
        catch (SQLException e)
        {
            e.getMessage();
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
            onCreate(db);
            Toast.makeText(context,"Drop the table !",Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e)
        {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public boolean insertMovie(SQLiteDatabase db,Movie movie) {

        boolean createSuccessful=false;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MOVIE_ID, movie.getId_data());
            contentValues.put(TITLE, movie.getOriginal_title_data());
            contentValues.put(OVERVIEW, movie.getOverview_data());
            contentValues.put(RELEASE_DATE, movie.getRelease_date_data());
            contentValues.put(VOTES, movie.getVote_average_data());
            contentValues.put(POSTER, movie.getPoster_path_data());

            createSuccessful = db.insert(TABLE_NAME, null, contentValues) != -1; // bydrb hena

        }
        catch(SQLException e)
        {

            e.printStackTrace();
        }




        return createSuccessful;


    }

   public boolean check_database_found()
   {
       File database=context.getDatabasePath(DB_NAME);

       if (!database.exists()) {


           return false;
       } else {

           return true;
       }
   }

    public void setOurDatabase(SQLiteDatabase db)
    {
        this.db=db;
    }

    public boolean selectFromDatabase(Movie movie) {
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+MOVIE_ID+" = ?",new String [] {String.valueOf(movie.getId_data())});

        if (c.getCount() ==0 )
            return false;
        else
            return true;
    }

    public ArrayList<Movie> getAllMovies(){

        int i=0;
        String res_id,res_url,res_date,res_rate,res_description,res_title;
        Movie movieInfo[] = new Movie[getMoviesCount()];
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from movies_details",null);
        res.moveToFirst();


        while (!res.isAfterLast()){
            res_id =res.getString(res.getColumnIndex(MOVIE_ID));
            res_url = res.getString(res.getColumnIndex(POSTER));
            res_date = res.getString(res.getColumnIndex(RELEASE_DATE));
            res_rate  = res.getString(res.getColumnIndex(VOTES));
            res_description = res.getString(res.getColumnIndex(OVERVIEW));
            res_title = res.getString(res.getColumnIndex(TITLE));
            if(i==movieInfo.length)
                break;

            movieInfo[i] = new Movie(res_url,res_description,res_title,res_date,Integer.parseInt(res_id),Double.parseDouble(res_rate));
            res.moveToNext();
            i++;


        }

        return new ArrayList<Movie>(Arrays.asList(movieInfo));
    }

    public int getMoviesCount() {
        String countQuery = "SELECT * FROM movies_details" ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
}
