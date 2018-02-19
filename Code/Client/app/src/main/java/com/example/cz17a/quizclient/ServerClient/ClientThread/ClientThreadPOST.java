package com.example.cz17a.quizclient.ServerClient.ClientThread;

import android.os.AsyncTask;

import com.example.cz17a.quizclient.Login.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Willy Steinbach, Thomas Gerbert
 * @version 0.2
 */

public class ClientThreadPOST extends AsyncTask<URL, Integer, JSONArray> {

    private JSONObject usr;

    public ClientThreadPOST(User usr){
        this.usr = usr.toJSON();
    }
    /**
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
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                out.write(usr.toString());
                connect.disconnect();
            } catch (IOException e) {
                System.out.println("cant connect to given URL");
                return null;
            }

        }
            return null;
    }
}