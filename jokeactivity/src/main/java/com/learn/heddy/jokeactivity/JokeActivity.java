package com.learn.heddy.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/10/17.
 */
public class JokeActivity extends AppCompatActivity {

    private final static String LOG_TAG = JokeActivity.class.getSimpleName();

    // Constant to pass in the jokes as Intent StringArrayListExtra
    public static final String MANY_JOKES_EXTRA = "MANY_JOKES_EXTRA";

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(mDisplayAdapter);

        Intent intent = getIntent();
        if (savedInstanceState==null){
            if (intent!=null && intent.getStringArrayListExtra(this.MANY_JOKES_EXTRA)!=null){
                mJokesArray = new ArrayList<String>();
                mJokesArray = intent.getStringArrayListExtra(this.MANY_JOKES_EXTRA);
                mDisplayAdapter.completeManyJokes(mJokesArray);
            }
        }
    }
}
