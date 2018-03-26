package client.communication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class SocketThread implements Runnable, Observer {
	Socket clientSocket;

	public SocketThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		System.out.println("initiated SocketThread");
	}

	public void run() {
		System.out.println("in run @ SocketThread");
		while (!ServerStarter.getInstance().isShutdown()) {	
		}
		System.out.println("left run @ SocketThread");
		ServerStarter.getInstance().unregisterClientThread(this);
	}

	@Override
	public void update(Observable o, Object message) {
		String response = (String)message;
		try {
			System.out.println("in update @ SocketThread");
			OutputStream output = this.clientSocket.getOutputStream();
			String EOFStr = "\n";
			response += EOFStr;
			output.write(response.getBytes());
			output.flush();
			System.out.println("Sent: " + response + " to client ip " + this.clientSocket.getInetAddress());
			System.out.println(" on port " + this.clientSocket.getPort());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
