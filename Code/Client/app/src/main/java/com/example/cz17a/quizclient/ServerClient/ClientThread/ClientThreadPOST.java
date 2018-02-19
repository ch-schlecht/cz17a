package com.example.cz17a.quizclient.ServerClient.ClientThread;

import android.os.AsyncTask;

import com.example.cz17a.quizclient.Login.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author Willy Steinbach, Thomas Gerbert
 * @version 0.2
 */

public class ClientThreadPOST extends AsyncTask<URL, Integer, Boolean> {

    private JSONObject usr;

    public ClientThreadPOST(){
        this.usr = null;
    }

    public ClientThreadPOST(User usr){
        this.usr = usr.toJSON();
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
                if (connect.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code: " + connect.getResponseCode());
                }
                //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                //future implement
                //out.write(usr.toString());
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