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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.learn.heddy.biggerbetter.task.BiggerEndpointTask;
import com.learn.heddy.jokeactivity.JokeActivity;

import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/10/17.
 */
public class MainActivityFragment extends Fragment {

    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    Context context;

    // interstitial ad variable
    InterstitialAd mInterstitialAd;

    public MainActivityFragment() {
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Banner style ad
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        // interstitial ad uses the network, so it is typically loaded even before it is
        // needed.  The codes below does that.
        mInterstitialAd = new InterstitialAd(getActivity());
        String adUnitId = getActivity().getString(R.string.banner_ad_unit_id);
        mInterstitialAd.setAdUnitId(adUnitId);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                displayJokes();
            }
        });
        // invoke immediately once.
        requestNewInterstitial();

        // MainActivity
        Button jokeB = (Button)root.findViewById(R.id.getJokeButtonFree);

        jokeB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                } else {
                    displayJokes();
                }
            }
        });

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private ArrayList<String> handleGetJokes(){

        ArrayList<String> jokesList = new ArrayList<String>();
        BiggerEndpointTask myservice;
        String flavor = "free";

        try {
            myservice = (BiggerEndpointTask) new BiggerEndpointTask().execute(new Pair<Context, String>(context, flavor));
            if (myservice==null) {
                Log.d(LOG_TAG, "New Service is still null");
            } else {
                jokesList = myservice.get();
            }
        } catch(Exception e){
            Log.e(LOG_TAG, "Problem from backend service "+ e);
        } finally {
            return jokesList;
        }
    }

    private void displayJokes(){

        ArrayList<String> jokesToSend = handleGetJokes();
        if (jokesToSend!=null && !jokesToSend.isEmpty()) {
            Intent intent = new Intent(getActivity(), JokeActivity.class);
            intent.putStringArrayListExtra("MANY_JOKES_EXTRA", jokesToSend);
            startActivity(intent);
        } else {
            Log.e(LOG_TAG, "Problem: No jokes!!");
        }
    }

}
