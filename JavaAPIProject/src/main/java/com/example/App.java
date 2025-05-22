package com.example;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{

    private static ArrayList<MarsPhoto> images;


    public static MarsPhoto getRandomImage(){
        return new MarsPhoto(null, null, null, 0);
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
