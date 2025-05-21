package com.example;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RandomImage {
    private ArrayList<Image> images;
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

    public Image randomImage(){
        if(visitedIndices.size() == images.size()){
            newDay();
        }
        int index = (int)(Math.random() * images.size());
        while(visitedIndices.contains(index)){
            index = (int)(Math.random() * images.size());
        }
        visitedIndices.add(index);
        return images.get(index);
    }

    public static ArrayList<Image> parseJson(String data){
        JSONObject dataJSON = new JSONObject(data);
        JSONArray photosJSON = dataJSON.getJSONArray("photos");
        ArrayList<Image> photosObj = new ArrayList<>();

        for(int i = 0; i < photosJSON.length(); i++){
            JSONObject photo = photosJSON.getJSONObject(i);
            photosObj.add(new Image(
                photo.getString("img_src"),
                photo.getJSONObject("camera").getString("full_name"),
                photo.getString("earth_date"),
                photo.getInt("id")
                ));
        }
        return photosObj;
    }

}
