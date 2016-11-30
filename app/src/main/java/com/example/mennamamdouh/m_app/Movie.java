package com.example.mennamamdouh.m_app;

/**
 * Created by Menna Mamdouh on 11/11/2016.
 */
public class Movie {
    String poster_path_data="",overview_data="",original_title_data="",release_date_data="";
    int id_data=0;
    Double vote_average_data=0.0;

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
}
