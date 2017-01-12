package com.learn.heddy.biggerbetter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.learn.heddy.biggerbetter.task.BiggerEndpointTask;
import com.learn.heddy.jokeactivity.JokeActivity;

import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/10/17.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private Button jokeB;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        jokeB = (Button)root.findViewById(R.id.getJokeButton);

        jokeB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                ArrayList<String> jokesToSend = handleGetJokes();
                if (jokesToSend!=null && !jokesToSend.isEmpty()) {
                Intent intent = new Intent(getActivity(), JokeActivity.class);
                intent.putStringArrayListExtra("MANY_JOKES_EXTRA", jokesToSend);
                startActivity(intent);
                } else {
                    Log.e(LOG_TAG, "Paid version... Problem: No jokes!!");
                }
            }
        });
        return root;
    }


    private ArrayList<String> handleGetJokes(){

        ArrayList<String> jokesList = new ArrayList<String>();
        BiggerEndpointTask myservice;
        String flavor = "paid";

        try {
            myservice = (BiggerEndpointTask) new BiggerEndpointTask().execute(
                    new Pair<Context, String>(getActivity(), flavor));
            if (myservice==null) {
                Log.d(LOG_TAG, "PAID: Service is still null");
            } else {
                jokesList = myservice.get();
            }
        } catch(Exception e){
            Log.e(LOG_TAG, "PAID: Problem from backend service "+ e);
        } finally {
            return jokesList;
        }
    }
}
