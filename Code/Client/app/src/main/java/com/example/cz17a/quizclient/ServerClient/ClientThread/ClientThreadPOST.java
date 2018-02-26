package com.example.cz17a.quizclient.ServerClient.ClientThread;

import android.os.AsyncTask;

import com.example.cz17a.quizclient.Login.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author Willy Steinbach, Thomas Gerbert
 * @version 0.2
 */

public class ClientThreadPOST extends AsyncTask<URL, Integer, Boolean> {

    private JSONObject usr = null;

    public ClientThreadPOST(){
        this.usr = null;
    }

    public ClientThreadPOST(JSONObject usr){
        this.usr = usr;
    }



    /**
     * @params url
     * @return JSONArray
     */
    @Override
    protected Boolean doInBackground(URL... urls) {
        if (urls.length == 0) {
            return false;
        }
        for (URL url : urls) {
            try {
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("POST"); //throws ProtocolException
                connect.connect();
                /*if (connect.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code: " + connect.getResponseCode());
                }*/
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                out.write(usr.toString());
                BufferedReader cIn = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String line = "";
                String in = "";
                while ((line = cIn.readLine()) != null) {
                    System.out.println(line);
                    in += line;
                }
                System.out.println(in);
                connect.disconnect();
            } catch (ProtocolException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                System.out.println("cant connect to given URL");
                return false;
            }

        }
        return true;
    }
}