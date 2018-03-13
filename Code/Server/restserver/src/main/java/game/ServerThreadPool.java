package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Dummy for Socket Communication -> Lobby ThreadPool
 * @author Michael
 * @version 1.1
 * @category Socket
 * 
 */
public class ServerThreadPool implements Runnable{

	protected int size = 5; //(Max) Size of ThreadPool
	
	protected int PORT = 1811; //Standart Port
	protected ServerSocket serverSocket = null;//ServerSocket 
	protected boolean isStopped = false;//Pool-Loop control
	protected Thread runningThread = null;//Thread of Pool
	protected ExecutorService threadPool = Executors.newFixedThreadPool(5); //ThreadPool

	
	protected AtomicReferenceArray<String> message;

	/**
	 * Inits ThreadPool on specific Port
	 * @param port for threadPool
	 * @since 1.0
	 */
	public ServerThreadPool(int port) {
		this.PORT = port;
		message = new AtomicReferenceArray<String>(size);
	}


	/**
	 * Thread-Loop
	 */
	@Override
	public void run() {
	System.out.println("Starting ThreadPool");
	int ID = 0;
		synchronized(this) { //only one Thread can be runningThread
			this.runningThread = Thread.currentThread();
		}
		openServerSocket(); //open ServerSocket
		while(!isStopped) { //main loop
			Socket clientSocket = null; //clientSocket
			
			try {
				clientSocket = this.serverSocket.accept(); //accept connection from client
			} catch (IOException e) {
				if(isStopped) {
					System.out.println("Server Stopped");
					break;
				}
				throw new RuntimeException("Error accepting client connection",e);
			}
			this.threadPool.execute(new WorkerRunnable(clientSocket,this,ID)); //execute WorkerRunnable with clientSocket
			ID++;
			
		}
		this.threadPool.shutdown(); //close ThreadPool (after isStopped = true)
		
		
	}
	
	
	public synchronized boolean isStopped() {
		return this.isStopped;
	}
	
	/**
	 * Stopping ThreadPool
	 * @since 1.0
	 */
	public synchronized void stop() {
		this.isStopped = true; //stop main loop
		try {
			this.serverSocket.close(); //close ServerSocket
		} catch (IOException e) {
			throw new RuntimeException("Error closing Server",e);
		}
	}
	
	/**
	 * Create ServerSocket on defined Port
	 * @since 1.0
	 */
	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.PORT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port "+PORT,e);
		}
		
	}
	
	
}
