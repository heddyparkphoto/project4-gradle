package com.learn.heddy.jokesmith;

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
}
