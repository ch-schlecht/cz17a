package com.example.cz17a.quizclient.ServerClient.ClientThread;

import android.os.AsyncTask;

import com.example.cz17a.quizclient.Login.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Willy Steinbach, Thomas Gerbert
 * @version 0.2
 */

public class ClientThreadPOST extends AsyncTask<URL, Integer, Integer> {

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
    protected Integer doInBackground(URL... urls) {

        String result="-1";

        if (urls.length == 0) {
            return Integer.parseInt(result);
        }
        for (URL url : urls) {
       /**
            try {
                HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("POST"); //throws ProtocolException
                connect.setDoOutput(true);
                connect.connect();
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
                System.out.println("SendLoginUser: " + usr.toString());
                out.write(usr.toString());


                out.flush();
                out.close();
                int responseCode = connect.getResponseCode();
                System.out.println("ResponseCode: " + responseCode);


                connect.disconnect();

                if(connect.getResponseMessage() ==
                        "Sie haben sich erfolgreich angemeldet."){
                        return true;
                }

            } catch (ProtocolException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                System.out.println("cant connect to given URL");
                e.printStackTrace();
                return false;
            }

        }**/


try {
    StringEntity entity = new StringEntity(usr.toString()); //entity erzeugen

    HttpClient client = HttpClientBuilder.create().build(); //http client erstellen
    HttpPost request = new HttpPost(url.toString()); //request an die url setzen
    request.setEntity(entity); //entity (payload) an die request anhaengen (in den http body)
    request.setHeader("Content-type", "application/json");

    HttpResponse response = client.execute(request); //abschicken
    InputStream in = response.getEntity().getContent(); //Antwort vom Server bekommen
    Scanner s = new Scanner(in).useDelimiter("\\A"); //ab hier werden nur noch die daten aus dem input stream in einen string umgewandelt
    result = s.hasNext() ? s.next() : "";
}catch(IOException e){
    e.printStackTrace();
}


        }
        return Integer.parseInt(result);


    }
}