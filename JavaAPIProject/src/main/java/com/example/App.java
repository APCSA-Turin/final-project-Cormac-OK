package com.example;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Hello world!
 *
 */
public class App 
{

    private static ArrayList<Image> images;


    public static Image getRandomImage(){
        return new Image(null, null, null, 0);
    }
    public static void main( String[] args )
    {

        RandomImage rand = new RandomImage(2);
        for(int i = 0; i < 17; i ++){
            System.out.println(rand.randomImage());
            System.out.println();
        }
    }

    
}
