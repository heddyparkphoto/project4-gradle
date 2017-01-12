package com.learn.heddy.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/10/17.
 */
public class JokeActivity extends AppCompatActivity {

    private final static String LOG_TAG = JokeActivity.class.getSimpleName();

    TextView textView;
    RecyclerView recyclerView;
    DisplayAdapter mDisplayAdapter;
    ArrayList<String> mJokesArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        textView = (TextView)findViewById(R.id.jokeTopic);
        recyclerView = (RecyclerView)findViewById(R.id.listview_jokes);

        mDisplayAdapter = new DisplayAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mDisplayAdapter);

        Intent intent = getIntent();
        if (savedInstanceState==null){
            if (intent!=null && intent.getStringArrayListExtra("MANY_JOKES_EXTRA")!=null){
                mJokesArray = new ArrayList<String>();
                mJokesArray = intent.getStringArrayListExtra("MANY_JOKES_EXTRA");
                mDisplayAdapter.completeManyJokes(mJokesArray);
            }
        }
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "onPause called.");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "onPause called.");
        super.onResume();
    }
}
