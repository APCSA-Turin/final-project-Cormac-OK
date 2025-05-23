import java.io.*;
import java.net.*;

public class ImageDownloader {
    public static void downloadUsingStream(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Set user-agent to mimic a real browser
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        // Check for successful response
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code: " + responseCode);
        }
        try (
        InputStream in = new BufferedInputStream(connection.getInputStream());
        FileOutputStream out = new FileOutputStream(file)
        ) {
            byte[] buffer = new byte[1024];
            int count;
            while ((count = in.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        } finally {
            connection.disconnect();
        }
    }
    public static void main(String[] args) {
        try {
            downloadUsingStream(
            "https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/00002/opgs/edr/ncam/NRA_397681339EDR_F0020000AUT_04096M_.JPG",
            "mars_image.jpg"
            );
            System.out.println("Download successful!");
        } catch (IOException e) {
            e.printStackTrace();   
        }
    }
}