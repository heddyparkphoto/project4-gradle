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

                Log.d(LOG_TAG, "Button clicked from Paid!");

                String tempPaidJoke = handleGetJokes();
                if (tempPaidJoke!=null && tempPaidJoke.length() > 0) {
                    Intent intent = new Intent(getActivity(), JokeActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, tempPaidJoke);
                    startActivity(intent);
                } else {
                    Log.e(LOG_TAG, "Problem: No jokes!!");
                }
            }
        });
        return root;
    }


    private String handleGetJokes(){

        String returnJoke = null;
        BiggerEndpointTask myservice;
        String flavor = "paid";

        try {
            myservice = (BiggerEndpointTask) new BiggerEndpointTask().execute(
                    new Pair<Context, String>(getActivity(), flavor));
            if (myservice==null) {
                Log.d(LOG_TAG, "Service is still null");
            } else {
                returnJoke = myservice.get();
                Log.d(LOG_TAG, "Service OK ");
            }
        } catch(Exception e){
            Log.e(LOG_TAG, "E from backend service "+ e);
        } finally {
            return returnJoke;
        }
    }
}
