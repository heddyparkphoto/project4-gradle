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
 * Junit3 style Testcase
 */

/*
    Start the local dev server before test runs.
 */
public class EndpointAsyncTest extends AndroidTestCase {
    private final String LOG_TAG = EndpointAsyncTest.class.getSimpleName();

    // test "free"
    public void testOneJokeTeller() {

        ArrayList<String> jokesList;

        Context context = mContext;
        Pair pair = new Pair(context, "free");
        try {
            BiggerEndpointTask task = (BiggerEndpointTask) new BiggerEndpointTask().execute(pair);
            jokesList = task.get();

            assertNotNull(task);
            assertNotNull(jokesList);
            assertEquals(1, jokesList.size());
            assertTrue(jokesList.get(0).length() > 0);
        } catch (Exception e){
           // assertTrue(e instanceof ExecutionException || e instanceof InterruptedException);
            Log.e(LOG_TAG, "" + e);
        }
    }

    // test "paid"
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
