package com.learn.heddy.biggerbetter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.AndroidTestCase;
import android.util.Log;

import com.learn.heddy.biggerbetter.task.BiggerEndpointTask;

import java.util.ArrayList;

/**
 * Created by hyeryungpark on 1/12/17.
 *
 * Junit 3 style test
 */
/*
    Start the local dev server before test runs.
 */
public class EndpointPaidTest extends AndroidTestCase {
    private final String LOG_TAG = EndpointPaidTest.class.getSimpleName();

    public void testListOfJokesTeller() {
        ArrayList<String> jokesList;

        Context context = mContext;
        Pair pair = new Pair(context, "paid");
        try {
            BiggerEndpointTask task = (BiggerEndpointTask) new BiggerEndpointTask().execute(pair);
            jokesList = task.get();

            assertNotNull(task);
            assertNotNull(jokesList);
            assertTrue(jokesList.size() > 1);
            assertTrue(jokesList.get(0).length() > 0);
        } catch (Exception e) {
           // assertTrue(e instanceof ExecutionException || e instanceof InterruptedException);
            Log.e(LOG_TAG, "" + e);
        }
    }

}
