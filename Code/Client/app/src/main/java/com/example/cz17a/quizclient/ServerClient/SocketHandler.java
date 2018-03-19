package com.example.cz17a.quizclient.ServerClient;

import com.example.cz17a.quizclient.Activity.GameActivity;

/**
 * Static Class for Holding SocketCommunication
 * Also holds Activitys: GameActivity
 * @version 1.1
 * @author Michael
 */

public class SocketHandler {
    private static SocketCommunication socketCom; //var for SocketCommunication
    private static GameActivity game; //var for GameActivity


    /**
     * Get Holdet SocketCommunication
     * @since 1.0
     * @return SocketCommunication
     */
    public static synchronized  SocketCommunication getSocket(){
        return socketCom;
    }

    /**
     * Set SC to Hold
     * @since 1.0
     * @param socketCom
     */
    public static synchronized void setSocket(SocketCommunication socketCom){
        SocketHandler.socketCom = socketCom;
    }

    /**
     * Set GameAvtivity to Hold
     * @since 1.1
     * @param game
     */
    public static synchronized  void setGame(GameActivity game){
        SocketHandler.game = game;
    }

    /**
     * Get Holdet GameActivity
     * @since 1.1
     * @return
     */
    public static synchronized  GameActivity getGame (){
        return game;
    }


}
