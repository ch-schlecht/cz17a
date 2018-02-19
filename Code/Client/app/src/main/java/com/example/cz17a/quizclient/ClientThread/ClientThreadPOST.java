package com.example.cz17a.quizclient.ClientThread;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Willy Steinbach, Thomas Gerbert
 * @version 0.2
 */

public class ClientThreadPOST extends AsyncTask<URL, Integer, JSONArray> {

    /**
     * @deprecated clientThread 0.3
     * @params url
     * @return JSONArray
     */
    @Override
    protected JSONArray doInBackground(URL... urls) {

        if (urls.length == 0) {
            return null;
        }
        for (URL url : urls) {
            try {
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("POST"); //throws ProtocolException
                connect.connect();
                if (connect.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code: " + connect.getResponseCode());
                }
                BufferedWriter Out = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                connect.disconnect();
            } catch (IOException e) {
                System.out.println("cant connect to given URL");
                return null;
            }

        }
            return null;
    }
}