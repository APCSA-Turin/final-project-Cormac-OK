package com.example;

public class MarsPhoto {

    /** The URL to access the image*/
    private String URL;
    /** The camera on the rover by which this photo was taken */
    private String camera;
    /** The Earth date on which this photo was taken*/
    private String date;
    /** The photo's unique ID */
    private int id;
    /** The file path to access this photo, if it has been downloaded*/
    private String path;
    /** The rating the user has given this photo, if they've given it one*/
    private int rating;

    /**
     * Creates a new Image object
     * @param URL The image's URL
     * @param camera The camera that took the image
     * @param date The Earth date the image was taken
     * @param id The image's unique idea.
     */
    public MarsPhoto(String URL, String camera, String date, int id){
        // My method of downloading images only works with HTTPS, not HTTP links.
        if(URL.charAt(4) != 's'){ // Yes, I know you see charAt as a sign that ChatGPT was used, but I just really hate working with .substring() and .equals()
            this.URL = URL.substring(0,4) + "s" + URL.substring(4);
        }
        else{
            this.URL = URL;
        }
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString(){
        return "Id: " + id+"\nURL: " + URL+"\nCamera: " + camera+"\nDate: " + date;
    }

    
}
