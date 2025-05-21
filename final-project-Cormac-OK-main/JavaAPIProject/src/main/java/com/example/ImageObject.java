package com.example;

public class ImageObject {
    
    private String URL;
    private String camera;
    private String date;
    private int id;
    private int rating;

    /**
     * Creates a new Image object
     * @param URL The image's URL
     * @param camera The camera that took the image
     * @param date The Earth date the image was taken
     * @param id The image's unique idea.
     */
    public ImageObject(String URL, String camera, String date, int id){
        this.URL = URL;
        this.camera = camera;
        this.date = date;
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public String getCamera() {
        return camera;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString(){
        return "Id: " + id+"\nURL: " + URL+"\nCamera: " + camera+"\nDate: " + date;
    }

    
}
