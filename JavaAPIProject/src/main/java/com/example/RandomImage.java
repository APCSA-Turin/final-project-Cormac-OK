package com.example;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class RandomImage {
    /** A list of all photos gotten from the current day*/
    private ArrayList<MarsPhoto> images;
    /** The day currently being accessed*/
    private int day;
    /** The photos which have already been accessed*/
    private ArrayList<MarsPhoto> history = new ArrayList<>();

    /**
     * Creates a new image generator
     * @param startDay The first day to get photos from
     */
    public RandomImage(int startDay){
        day = startDay-1;
        newDay();
    }

    /**
     * Moves to a new day of photos
     */
    public void newDay(){
        day += 1;
        try{
            images = parseJson(API.getData(day));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets a new random image from the list of available new photos
     * @return A random image
     */
    public MarsPhoto randomImage(){
        if(images.size()==0){
            newDay();
        }
        int index = (int)(Math.random() * images.size());
        
        MarsPhoto image = images.remove(index);
        history.add(image);

        try{
            downloadUsingStream(image.getURL(), "JavaAPIProject//src//main//java//com//example//data//" + image.getId() + ".jpg");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        image.setPath("JavaAPIProject//src//main//java//com//example//data//" + image.getId() + ".jpg");

        return image;
    }

    /**
     * Converts a String containing JSON data into a list of MarsPhotos
     * @param data A String containing JSON data
     * @return An Arraylist of every photo in data
     */
    public static ArrayList<MarsPhoto> parseJson(String data){
        JSONObject dataJSON = new JSONObject(data);
        JSONArray photosJSON = dataJSON.getJSONArray("photos");
        ArrayList<MarsPhoto> photosList = new ArrayList<>();

        // Iterates over every JSON object in the array of photos
        for(int i = 0; i < photosJSON.length(); i++){
            // Gets the object to parse
            JSONObject photo = photosJSON.getJSONObject(i);
            // Converts the JSON object into a MarsPhoto object
            photosList.add(new MarsPhoto(
                photo.getString("img_src"),
                photo.getJSONObject("camera").getString("full_name"),
                photo.getString("earth_date"),
                photo.getInt("id")
                ));
        }
        return photosList;
    }

    /**
     * Downloads an image
     * @param urlString The URL from which to get the image
     * @param file The destination to which to send the image
     * @throws IOException
     */
    public static void downloadUsingStream(String urlString, String file) throws IOException{
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Impersonates a browser to access images
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        // A stream of bytes containing the data of the image
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        // Goes through the bis, reading it and writing it to target address
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fos.write(buffer, 0, count);
        }
        // Closes streams to prevent memory leak
        fos.close();
        bis.close();
    }



}
