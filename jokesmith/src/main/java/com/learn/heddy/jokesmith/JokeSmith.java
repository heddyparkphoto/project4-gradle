package com.learn.heddy.jokesmith;

import java.util.ArrayList;
import java.util.ResourceBundle;

/*
    Standalone Java library (Gradle Module)
    In this project this library will run at Google Cloud Endpoints as REST service
    The GCE Module should access all public methods defined in this class
 */
public class JokeSmith {
    static ResourceBundle bundle;

    public JokeSmith(){
    }

    public String tellJokeFromSmith(){
        return "Haha, Smith is a joker.  Funny, funny man!";
    }

    public static String staticTellJokeFromSmith(){
        return "STATIC METHOD: Smith is ONE SINGLE joker.  Funny, funny man!";
    }

    public ArrayList<String> tellManyJokesFromSmith(){

        // tell many jokes
        ArrayList<String> jokes = new ArrayList<String>();
        jokes.add("Haha, Smith is a joker.  Funny, funny man!");
        jokes.add("Two, Smith is a joker.  Funny, funny man!");
        jokes.add("Three, Smith is a joker.  Funny, funny man!");
        jokes.add("Four Smith is a joker.  Funny, funny man!");
        jokes.add("5, Smith is a joker.  Funny, funny man!");
        jokes.add("Six and, Smith is a joker.  Funny, funny man!");
        jokes.add("7, Smith is a joker.  Funny, funny man!");
        jokes.add("8th joke, Smith is a joker.  Funny, funny man!");
        jokes.add("9, Smith is a joker.  Funny, funny man!");
        jokes.add("10: Smith is a joker.  Funny, funny man!");

        return jokes;
    }
}
