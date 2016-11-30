package com.example.mennamamdouh.m_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.Communicator {
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public Bundle my_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        my_bundle = savedInstanceState;
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void respond(Movie movie) {


            DetailActivityFragment detailActivityFragment = (DetailActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
            detailActivityFragment.changeData(movie);

    }
}
