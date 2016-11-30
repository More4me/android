package com.example.mennamamdouh.m_app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Menna Mamdouh on 11/11/2016.
 */
public class Movie implements Parcelable {
    String poster_path_data="",overview_data="",original_title_data="",release_date_data="";
    int id_data=0;
    Double vote_average_data=0.0;

    public Movie()
    {

    }

    public Movie(String poster_path_data, String overview_data, String original_title_data, String release_date_data, int id_data, Double vote_average_data) {
        this.poster_path_data = poster_path_data;
        this.overview_data = overview_data;
        this.original_title_data = original_title_data;
        this.release_date_data = release_date_data;
        this.id_data = id_data;
        this.vote_average_data = vote_average_data;
    }

    public Movie(Parcel input)
    {
        poster_path_data=input.readString();
        overview_data=input.readString();
        original_title_data=input.readString();
        release_date_data=input.readString();
        id_data=input.readInt();
        vote_average_data=input.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPoster_path_data() {
        return poster_path_data;
    }

    public void setPoster_path_data(String poster_path_data) {
        this.poster_path_data = poster_path_data;
    }

    public String getOverview_data() {
        return overview_data;
    }

    public void setOverview_data(String overview_data) {
        this.overview_data = overview_data;
    }

    public String getOriginal_title_data() {
        return original_title_data;
    }

    public void setOriginal_title_data(String original_title_data) {
        this.original_title_data = original_title_data;
    }

    public String getRelease_date_data() {
        return release_date_data;
    }

    public void setRelease_date_data(String release_date_data) {
        this.release_date_data = release_date_data;
    }

    public int getId_data() {
        return id_data;
    }

    public void setId_data(int id_data) {
        this.id_data = id_data;
    }

    public Double getVote_average_data() {
        return vote_average_data;
    }

    public void setVote_average_data(Double vote_average_data) {
        this.vote_average_data = vote_average_data;
    }



    public String print()
    {
        return (poster_path_data+" , "+overview_data+" , "+original_title_data+" , "+release_date_data+" , "+ id_data+" , "+vote_average_data);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(poster_path_data);
        dest.writeString(overview_data);
        dest.writeString(original_title_data);
        dest.writeString(release_date_data);
        dest.writeInt(id_data);
        dest.writeDouble(vote_average_data);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
