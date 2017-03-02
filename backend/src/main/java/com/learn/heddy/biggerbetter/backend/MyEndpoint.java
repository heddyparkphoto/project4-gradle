/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.learn.heddy.biggerbetter.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.learn.heddy.jokesmith.JokeSmith;

import java.util.ArrayList;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.biggerbetter.heddy.learn.com",
    ownerName = "backend.biggerbetter.heddy.learn.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    /** Main endpoint method that fetches and sets the jokes on the respond for the app's request */
    @ApiMethod(name="fetchJokes")
    public MyBean fetchJokes(){
        ArrayList<String> jokedJokes;

        MyBean response = new MyBean();
        JokeSmith smith = new JokeSmith();
        jokedJokes = smith.tellManyJokesFromSmith();
        response.setMyListData(jokedJokes);

        return response;
    }
}
