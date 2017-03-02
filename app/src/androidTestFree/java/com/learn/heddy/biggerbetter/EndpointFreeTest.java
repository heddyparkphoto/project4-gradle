package com.learn.heddy.biggerbetter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.util.Pair;
import android.util.Log;

import com.learn.heddy.biggerbetter.task.BiggerEndpointTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by hyeryungpark on 3/2/17.
 */

/*
    Start the local dev server before test runs.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointFreeTest {
    private final String LOG_TAG = EndpointFreeTest.class.getSimpleName();

    @Test
    public void testOneJokeTeller() {

        ArrayList<String> jokesList;

        Context context = InstrumentationRegistry.getContext();
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
}
