package game;

import java.net.Socket;

/**
 * Runnable for ThreadPool
 * @author Michael
 * @version 1.0
 *
 */
public class WorkerRunnable implements Runnable{

	protected Socket clientSocket = null;
    protected String command   = null;

    public WorkerRunnable(Socket clientSocket, String command) {
        this.clientSocket = clientSocket;
        this.command   = command;
    }

    public void run() {
     //TODO Communication
    }
    
    public String sendText() {
    	return "test";
    }
	
}
