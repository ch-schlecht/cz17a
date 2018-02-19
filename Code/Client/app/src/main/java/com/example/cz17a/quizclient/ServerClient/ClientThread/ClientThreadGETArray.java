package com.example.cz17a.quizclient.ServerClient.ClientThread;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Willy Steinbach, Thomas Gerbert
 * @version 0.2
 */

public class ClientThreadGETArray extends AsyncTask<URL, Integer, JSONArray>{

    private JSONObject usr;


    /**
     * @params url
     * @return JSONArray
     * @version 0.3
     */
    @Override
    protected JSONArray doInBackground(URL... urls) {

           if (urls.length == 0) {
                return null;
           }
         for (URL url : urls) {

             String quizType = "";
                //try to connect to given url
                //set connectiontype to get
                try {
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                        connect.setRequestMethod("GET");//throws ProtocolException
                    connect.connect();
                    if (connect.getResponseCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code: " + connect.getResponseCode());
                 }
                 BufferedReader cIn = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                 String line = "";
                    while ((line = cIn.readLine()) != null) {
                        System.out.println(line);
                        quizType += line;
                   }
                    connect.disconnect();
                } catch (IOException e) {
                    System.out.println("cant connect to given URL");
                 return null;
                }
                JSONArray jType = null;

             //try to parse input into JSONArray
                try {
                    System.out.println("QUIZTYPE: "+quizType);
                    jType = new JSONArray(quizType);
                    System.out.println("JSON: "+jType.toString());
             } catch (JSONException j) {
                    System.out.println("invalid Data");
                }
                return jType;
         }
            return null;
    }
}
