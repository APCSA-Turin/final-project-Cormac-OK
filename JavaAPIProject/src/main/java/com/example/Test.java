package com.example;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Test {
    public static void main(String[] args) {
        try{
            saveImage("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00002/opgs/edr/ncam/NRA_397681339EDR_F0020000AUT_04096M_.JPG", "JavaAPIProject//src//main//java//com//example//data//nasa.JPG");
        }
        catch(Exception e){}

        try{
            saveImage("https://images.dog.ceo/breeds/spaniel-japanese/n02085782_172.jpg", "JavaAPIProject//src//main//java//com//example//data//dog.jpg");
        }
        catch (Exception e){}

        try{
            downloadUsingStream("https://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00002/opgs/edr/ncam/NRA_397681339EDR_F0020000AUT_04096M_.JPG", "JavaAPIProject//src//main//java//com//example//data//nasaV2.JPG");
        }
        catch(Exception e){}


    }

    /**
     * From Stack Overflow
     */
    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

    /**
     * Digital Ocean
     */
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
