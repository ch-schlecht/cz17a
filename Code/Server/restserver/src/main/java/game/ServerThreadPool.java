package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Dummy for Socket Communication -> Lobby ThreadPool
 * 
 * @author Michael
 * @version 1.1
 * @category Socket
 * 
 */
public class ServerThreadPool implements Runnable {
	protected int size = 5; // (Max) Size of ThreadPool
	protected int PORT = 1811; // Standart Port
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;// Pool-Loop control
	protected Thread runningThread = null;// Thread of Pool
	protected ExecutorService threadPool = Executors.newFixedThreadPool(5); // ThreadPool
	protected AtomicReferenceArray<String> message;
	protected boolean readingFlag = false; //we cannot simply let a worker always read, because readline blocks for input
	
	

	/**
	 * Inits ThreadPool on specific Port
	 * 
	 * @param port
	 *            for threadPool
	 * @since 1.0
	 */
	public ServerThreadPool(int port, int size) {
		this.PORT = port;
		this.size = size;
		message = new AtomicReferenceArray<String>(size);
		for(int i = 0; i < size; i++) {
			message.set(i, "");
		}
		
	}

	public int getSize() {
		return size;
	}

	/**
	 * Thread-Loop
	 */
	@Override
	public void run() {
		System.out.println("Starting ThreadPool");
		int ID = 0;
		synchronized (this) { // only one Thread can be runningThread
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		while (!isStopped) { // main loop
			Socket clientSocket = null;
			try {
				clientSocket = this.serverSocket.accept();
				System.out.println("Accepted Socket");
			} catch (IOException e) {
				if (isStopped) {
					System.out.println("Server Stopped");
					break;
				}
				throw new RuntimeException("Error accepting client connection", e);
			}
			this.threadPool.execute(new WorkerRunnable(clientSocket, this, ID));
			ID++;
		}
		this.threadPool.shutdown(); // close ThreadPool (after isStopped = true)
	}

	public synchronized boolean isStopped() {
		return this.isStopped;
	}

	/**
	 * Stopping ThreadPool
	 * 
	 * @since 1.0
	 */
	public synchronized void stop() {
		this.isStopped = true; // stop main loop
		try {
			this.serverSocket.close(); // close ServerSocket
		} catch (IOException e) {
			throw new RuntimeException("Error closing Server", e);
		}
	}

	/**
	 * Create ServerSocket on defined Port
	 * 
	 * @since 1.0
	 */
	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.PORT);
		} catch (IOException e) {
			throw new RuntimeException("Cannot open port " + PORT, e);
		}
	}
	
	public void setReadingFlag(boolean flag) {
		this.readingFlag = flag;
	}
}
