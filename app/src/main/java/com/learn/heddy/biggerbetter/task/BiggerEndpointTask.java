package com.learn.heddy.biggerbetter.task;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.learn.heddy.biggerbetter.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/10/17.
 *
 * Adapted from the Lesson link sample code
 * It requests the GCE backend Api called MyApi and its method to deliver the list of jokes
 * to the calling methods.
 *
 * */
public class BiggerEndpointTask extends AsyncTask<Pair<Context, String>, Void, ArrayList<String>> {

    private static MyApi serviceApi = null;
    private Context context;

    /*
        The calling method sends whether this is a "free" or "paid" Build on the 2nd argument.
        ArrayList of one joke is returned if "free" vs. a full list of jokes if not (or "paid").
     */
    @Override
    protected ArrayList<String> doInBackground(Pair<Context, String>... pairs) {

        Pair inParam = pairs[0];
        this.context = (Context)inParam.first;
        String flavor = (String)inParam.second;
        ArrayList<String> jokesList;

        // Do this just once
        if (serviceApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
            });
            // end options for devappserver

            serviceApi = builder.build();
        }

        try {
            jokesList = (ArrayList<String>)serviceApi.fetchJokes().execute().getMyListData();
            if ("free".equalsIgnoreCase(flavor)){
                ArrayList<String> oneVersion = new ArrayList<String>();
                oneVersion.add(jokesList.get(0));
                return oneVersion;
            } else {
                return jokesList;
            }
        } catch (IOException e) {
            Log.e("BiggerEndPointTasks ", "" + e);
            return null;
        }
    }
}
