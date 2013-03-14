package com.bingoflights.testing;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

public class DataLoader {

    public static String loadTestData(String fileURI) throws IOException {
        URL allFlightsURL = Resources.getResource(fileURI);
        return Resources.toString(allFlightsURL, Charsets.UTF_8);
    }
}
