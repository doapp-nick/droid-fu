package com.github.droidfu.imageloader;

import java.io.IOException;

public interface ImageRetriever {
    
    public byte[] retrieveImageData(String requestedUrl) throws IOException;
    
    public void notifyImageRequestStarted();

}
