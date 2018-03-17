package game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

import data.model.Player;

/**
 * Runnable for ThreadPool
 * 
 * @author Michael
 * @version 1.0
 *
 */
public class WorkerRunnable implements Runnable {

	protected Socket clientSocket = null;
	protected ServerThreadPool parent = null;
	protected int ID = 0;
	protected boolean isStopped = false; //worker loop control

	public WorkerRunnable(Socket clientSocket, ServerThreadPool parent, int ID) {
		this.clientSocket = clientSocket;
		this.parent = parent;
		this.ID = ID;
	}

	public void run() {
		System.out.println("running Pool");
		Scanner input = null;
		try {
			input = new Scanner(clientSocket.getInputStream()); //set this before the main loop, so that the scanner will not be overridden by a previous one
		} catch (IOException e1) {
			System.out.println("Error creating Scanner for Reading from Client");
			e1.printStackTrace();
		}
		run: while (!isStopped) {
			
			//check connection
			
			OutputStream output = null;
			
			String receivedInput = "";
			try {
				String msg = parent.message.get(ID);
				if (msg != "") {
					output = clientSocket.getOutputStream();
					output.write(msg.getBytes());
					output.flush();
					System.out.println("Sending " + msg + " to " + clientSocket.getInetAddress() + " from ID: " + ID);
					parent.message.set(ID, "");
				}
				
				if(parent.readingFlag) { //if a reading flag was set, read message from Client
					System.out.println("Reading from " + clientSocket.getInetAddress() + " initiated on ID: " + ID);
					input.useDelimiter(Pattern.quote("$")); //escape sequence
					while(input.hasNext()) {
						System.out.println("Reading input");
						receivedInput = input.next();
						//TODO pass the received String out (to Lobby, Game, wherever needed), maybe use same pattern as sending (with messages array)
						//TODO maybe set reading flag to false in here, but might cause problems with other workers when this one sets
						//the flag to false, but others have not yet reached the input (--> they would not read a message from client even though they should)
						continue run; //when message was read, go back to main running loop
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**
			 * try {
			 * 
			 * if(parent.message != null) {
			 * 
			 * }
			 * 
			 * 
			 * 
			 * InputStream input = clientSocket.getInputStream(); OutputStream output =
			 * clientSocket.getOutputStream(); long time = System.currentTimeMillis();
			 * output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + this.serverText + " - "
			 * + time + "").getBytes()); output.close(); input.close();
			 * System.out.println("Request processed: " + time); } catch (IOException e) {
			 * //report exception somewhere. e.printStackTrace(); }
			 **/
		}
		
	}
	public synchronized void closeAllConnectionsAndStopWorker() {
		this.isStopped = true;
		try {
			this.clientSocket.close(); //closing socket should be enough, since all Input/Output Streams and associated channels are closed as well
								       //(reference taken from javadoc)
		} catch (IOException e) {
			System.out.println("Error closing socket connection");
			e.printStackTrace();
		}
	}
}
