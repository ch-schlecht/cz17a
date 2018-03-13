package com.example.cz17a.quizclient.ServerClient.ClientThread;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Scanner;
        import org.apache.http.HttpResponse;
        import org.apache.http.client.HttpClient;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.HttpClientBuilder;


/**
 *
 * @author Christian-PC2
 */
public class PostRequest {
    private static final String urlRegister = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/webapi/players/register";
    private static final String urlLogin = "http://pcai042.informatik.uni-leipzig.de:1810/restserver/webapi/players/login";

    /**
     * führt den post request zur url aus.
     * der payload string sind die daten, die geschickt werden (bei euch dann anpassen, evtl. direkt json objekt reingeben.
     * die payload wird in den http body gesetzt (wie von unserer rest schnittstelle erwartet
     * @param url
     * @param payload
     * @return
     * @throws IOException
     */
    public static String doPostRequest(String url, String payload) throws IOException{
        StringEntity entity = new StringEntity(payload); //entity erzeugen

        HttpClient client = HttpClientBuilder.create().build(); //http client erstellen
        HttpPost request = new HttpPost(url); //request an die url setzen
        request.setEntity(entity); //entity (payload) an die request anhaengen (in den http body)
        request.setHeader("Content-type", "application/json");

        HttpResponse response = client.execute(request); //abschicken
        InputStream in = response.getEntity().getContent(); //Antwort vom Server bekommen
        Scanner s = new Scanner(in).useDelimiter("\\A"); //ab hier werden nur noch die daten aus dem input stream in einen string umgewandelt
        String result = s.hasNext() ? s.next() : "";
        return result;
    }

}
