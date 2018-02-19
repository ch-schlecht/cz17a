package game;

import java.net.Socket;

public class WorkerRunnable implements Runnable{

	protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
     //TODO Communication
    }
	
}
