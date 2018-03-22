package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class SocketThread implements Runnable, Observer {
	Socket clientSocket;
	// BufferedReader inFromClient;

	public SocketThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		// this.inFromClient = inFromClient;
	}

	public void run() {
		String fromClient;
		while (!ServerStarter.getInstance().isShutdown()) {
			// TODO...prepare message to broadcast
			// System.out.println("Received " + fromClient);
			// String messageStr = "Hello World";
			// Object message = messageStr;
			// ServerStarter.getInstance().notifyAllClients(message);
		}

		ServerStarter.getInstance().unregisterClientThread(this);
	}

	@Override
	public void update(Observable o, Object message) {
		try {
			OutputStream output = this.clientSocket.getOutputStream();
			String EOFStr = "\n";
			message = message.toString() + EOFStr;
			output.write(message.toString().getBytes());
			output.flush();
			System.out.println("Sent: " + message + " to client ip " + this.clientSocket.getInetAddress());
			System.out.println(" on port " + this.clientSocket.getPort());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
