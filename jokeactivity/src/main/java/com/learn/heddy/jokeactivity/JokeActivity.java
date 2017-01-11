package com.learn.heddy.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by hyeryungpark on 1/10/17.
 */
public class JokeActivity extends AppCompatActivity {

    private final static String LOG_TAG = JokeActivity.class.getSimpleName();

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        textView = (TextView)findViewById(R.id.jokeText);

        Log.d(LOG_TAG, "In android module!");

        Intent intent = getIntent();
        if (savedInstanceState==null){
            if (intent!=null && intent.getStringExtra(Intent.EXTRA_TEXT)!=null){
                String jokeOnMe = intent.getStringExtra(Intent.EXTRA_TEXT);
                textView.setText("Laugh! from Android library: " + jokeOnMe);
            }
        }
    }
}
