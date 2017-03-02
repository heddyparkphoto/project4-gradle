package com.learn.heddy.jokesmith;

import java.util.ArrayList;
import java.util.Properties;

/*
    Standalone Java library (Gradle Module)
    In this project this library will run at Google Cloud Endpoints as REST service
    The GCE Module should access all public methods defined in this class
 */
public class JokeSmith {
    private static Properties props;
    private static String JOKES_FILE = "jokes.properties";

    public JokeSmith(){
        props = new Properties();
        try {
            props.load(JokeSmith.class.getClassLoader().getResourceAsStream(JOKES_FILE));
        } catch (Exception e){
            System.out.println("Exception loading jokes file! " + e);
        }
    }

    public String tellJokeFromSmith(){
        return props.getProperty("cheesy", "defaultCheesy joke");
    }

    public static String staticTellJokeFromSmith(){
        return "STATIC METHOD: Smith is ONE SINGLE joker.  Funny, funny man!";
    }

    public ArrayList<String> tellManyJokesFromSmith(){
        // tell many jokes
        ArrayList<String> jokes = new ArrayList<String>();
        jokes.add(props.getProperty("1", "defaultJoke 1"));
        jokes.add(props.getProperty("2", "defaultJoke 2"));
        jokes.add(props.getProperty("3", "defaultJoke 3"));
        jokes.add(props.getProperty("4", "defaultJoke 4"));
        jokes.add(props.getProperty("5", "defaultJoke 5"));
        jokes.add(props.getProperty("6", "defaultJoke 6"));
        jokes.add(props.getProperty("7", "defaultJoke 7"));
        jokes.add(props.getProperty("8", "defaultJoke 8"));
        jokes.add(props.getProperty("9", "defaultJoke 9"));
        jokes.add(props.getProperty("10", "defaultJoke 10"));
        jokes.add(props.getProperty("11", "defaultJoke 11"));
        jokes.add(props.getProperty("12", "defaultJoke 12"));

        return jokes;
    }
}
