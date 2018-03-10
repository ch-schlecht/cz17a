package com.example.cz17a.quizclient.ServerClient.ClientThread;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Michael Fritz on 09.03.2018.
 */

public class ClientThreadGETString extends AsyncTask<URL, Integer, String> {

    private JSONObject usr;


    /**
     * @params url
     * @return JSONArray
     * @version 0.3
     */
    @Override
    protected String doInBackground(URL... urls) {

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

            return quizType;
        }
        return null;
    }
}
