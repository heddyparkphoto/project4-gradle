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

/**
 * Created by hyeryungpark on 1/10/17.
 */
public class BiggerEndpointTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private static MyApi serviceApi = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... pairs) {

        Pair inParam = pairs[0];
        this.context = (Context)inParam.first;
        String flavor = (String)inParam.second;

        // Do this just once
        if (serviceApi == null){
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

                return serviceApi.fetchJokes(flavor).execute().getData();

        } catch (IOException e) {
            Log.v("EndPoints", ""+e);
            return null;
        }
    }

}
