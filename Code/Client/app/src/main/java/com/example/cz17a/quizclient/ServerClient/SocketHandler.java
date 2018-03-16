package com.example.cz17a.quizclient.ServerClient;

import com.example.cz17a.quizclient.Activity.GameActivity;

/**
 * Created by Michael Fritz on 15.03.2018.
 */

public class SocketHandler {
    private static SocketCommunication socketCom;

    public static synchronized  SocketCommunication getSocket(){
        return socketCom;
    }

    public static synchronized void setSocket(SocketCommunication socketCom){
        SocketHandler.socketCom = socketCom;
    }

    /**
    public static synchronized  void addGame(GameActivity game){
        socketCom.setGame(game);
    }
**/
}

