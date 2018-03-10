package game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Runnable for ThreadPool
 * @author Michael
 * @version 1.0
 *
 */
public class WorkerRunnable implements Runnable{

	protected Socket clientSocket = null;
    protected ServerThreadPool parent   = null;

    public WorkerRunnable(Socket clientSocket, ServerThreadPool parent) {
        this.clientSocket = clientSocket;
        this.parent = parent;
    }


    public void run() {
       System.out.println("running Pool");
    	while(true){
    		OutputStream output = null;
            try {
            	output = clientSocket.getOutputStream();
				output.write(("First Test").getBytes());
				System.out.println("Sending first test");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					output.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            
    		
    		/**try {
            
        		if(parent.message != null) {
            	   
               }
        		
        		
        		
        		InputStream input  = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();
                long time = System.currentTimeMillis();
                output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                		this.serverText + " - " +
                		time +
                		"").getBytes());
                output.close();
                input.close();
                System.out.println("Request processed: " + time);
            } catch (IOException e) {
                //report exception somewhere.
                e.printStackTrace();
            }**/
        }

        	
    }
    
    }
