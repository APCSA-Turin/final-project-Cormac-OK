package com.example;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class RandomImage {
    private ArrayList<MarsPhoto> images;
    private int day;
    private ArrayList<MarsPhoto> history = new ArrayList<>();

    public RandomImage(int startDay){
        day = startDay-1;
        newDay();
    }
    
    public void newDay(){
        System.out.println("NEWDAY\n\n\n\n\n\n\n\n\n");
        day += 1;
        try{
            images = parseJson(API.getData(day));
        }
        catch(Exception e){
            
        }
    }

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

    public static ArrayList<MarsPhoto> parseJson(String data){
        JSONObject dataJSON = new JSONObject(data);
        JSONArray photosJSON = dataJSON.getJSONArray("photos");
        ArrayList<MarsPhoto> photosObj = new ArrayList<>();

        for(int i = 0; i < photosJSON.length(); i++){
            JSONObject photo = photosJSON.getJSONObject(i);
            photosObj.add(new MarsPhoto(
                photo.getString("img_src"),
                photo.getJSONObject("camera").getString("full_name"),
                photo.getString("earth_date"),
                photo.getInt("id")
                ));
        }
        return photosObj;
    }

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Set user-agent to mimic a real browser
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }



}
