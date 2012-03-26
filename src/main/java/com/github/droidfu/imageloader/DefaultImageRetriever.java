package com.github.droidfu.imageloader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class DefaultImageRetriever implements ImageRetriever {

    private static final String LOG_TAG = "Droid-Fu/DefaultImageRetriever";

    @Override
    public byte[] retrieveImageData(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // determine the image size and allocate a buffer
        int fileSize = connection.getContentLength();
        if (fileSize < 0) {
            return null;
        }
        byte[] imageData = new byte[fileSize];

        // download the file
        Log.d(LOG_TAG, "fetching image " + imageUrl + " (" + fileSize + ")");
        BufferedInputStream istream = new BufferedInputStream(connection.getInputStream());
        int bytesRead = 0;
        int offset = 0;
        while (bytesRead != -1 && offset < fileSize) {
            bytesRead = istream.read(imageData, offset, fileSize - offset);
            offset += bytesRead;
        }

        // clean up
        istream.close();
        connection.disconnect();

        return imageData;
    }

    @Override
    public void notifyImageRequestStarted() {
        //nothing
    }
}
