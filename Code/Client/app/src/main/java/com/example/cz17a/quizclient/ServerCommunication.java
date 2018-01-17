package com.example.cz17a.quizclient;

/**
 * Created by stein on 17.01.2018.
 */

public class ServerCommunication {

    public String[] getTopicTitles(){
        String[] titles = new String[3];
        titles[0] = "Topic 0";
        titles[1] = "Topic 1";
        titles[2] = "Topic 2";
        return titles;

    }
    public int[] getTopicIDs(){
        int[] IDs = new int[3];
        for (int i = 0; i<3;i++){
            IDs[i] = i;
        }
        return IDs;
    }
}
