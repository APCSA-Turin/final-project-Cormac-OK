package com.example;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class RandomImage {
    private ArrayList<MarsPhoto> images;
    private ArrayList<Integer> visitedIndices;
    private int day;

    public RandomImage(int startDay){
        day = startDay-1;
        newDay();
    }
    
    public void newDay(){
        System.out.println("NEWDAY\n\n\n\n\n\n\n\n\n");
        day += 1;
        visitedIndices = new ArrayList<>();
        try{
            images = parseJson(API.getData(day));
        }
        catch(Exception e){
            
        }
    }

    public MarsPhoto randomImage(){
        if(visitedIndices.size() == images.size()){
            newDay();
        }
        int index = (int)(Math.random() * images.size());
        while(visitedIndices.contains(index)){
            index = (int)(Math.random() * images.size());
        }
        visitedIndices.add(index);

        MarsPhoto toAdd = images.get(index);

        try{
            System.out.println(toAdd.getURL());
            downloadUsingStream(toAdd.getURL(), "JavaAPIProject//src//main//java//com//example//data//" + toAdd.getId() + ".jpg");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        toAdd.setPath("JavaAPIProject//src//main//java//com//example//data//" + toAdd.getId() + ".jpg");

        return images.get(index);
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
