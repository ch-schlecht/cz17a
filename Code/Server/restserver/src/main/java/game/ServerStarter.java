package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ServerStarter implements Runnable{

    private static ServerStarter singleton;
    //private static Object mutex = new Object();

    private volatile boolean shutdown;
    // thread pool executor
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    // observable to notify client threads
    private final ModifiedObservable observable = new ModifiedObservable();
    // fair lock (can use unfair lock if message broadcasting order is not important)
    private final Lock fairLock = new ReentrantLock(true);    
    public static final int PORT = 1811; 
    private boolean sendingFlag = false;

    private ServerStarter() {
    	new Thread(this).start();
    }
   
    public static ServerStarter getInstance() {
    	if(singleton == null) {
    		synchronized(ServerStarter.class) {
    			if(singleton == null) {
    				singleton = new ServerStarter();
    			}
    		}
    	}
    	return singleton;
       
    }
    
    public boolean isReadyToSend() {
    	return sendingFlag;
    }
    
    public void setSendingFlag(boolean flag) {
    	this.sendingFlag = flag;
    }
    
    @Override
    public void run() {
    	start();
    }

    public void start() {
        ServerSocket server = null;
        System.out.println("started ServerStarter");
        try {
            //server configs,from left to right is: PORT,BackLog,Address
            server = new ServerSocket(PORT);
            while (!isShutdown()) {
            	System.out.println("waiting for connection");
                Socket sock = server.accept();
                System.out.println("accepted Connection");
                //BufferedReader inFromClient = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                //each clients run on it's own thread!
                SocketThread clientThread = new SocketThread(sock);
                observable.addObserver(clientThread); //move function bodies start and register directly up
                executorService.submit(clientThread);
                
               // ServerStarter.getInstance().registerClientThread(clientThread);
               // ServerStarter.getInstance().startClientThread(clientThread);
            }
        } catch (IOException e) {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    public void shutdown() {
        shutdown = true;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void startClientThread(SocketThread clientThread) {
        executorService.submit(clientThread);
    }

    private void registerClientThread(SocketThread clientThread) {
        observable.addObserver(clientThread);
    }

    public void notifyAllClients(final Object message) {
    	System.out.println("in notifyAllClients @ ServerStarter");
    	System.out.println("going to notify " + observable.countObservers() + " Clients");
        fairLock.lock();
        try {
            executorService.submit(new MessageBroadcaster(message));
        } finally {
            fairLock.unlock();
        }
    }

    public void unregisterClientThread(SocketThread clientThread) {
        fairLock.lock();
        try {
            observable.deleteObserver(clientThread);
        } finally {
            fairLock.unlock();
        }
    }

    private class MessageBroadcaster implements Runnable {
        private final Object message;

        public MessageBroadcaster(Object message) {
            this.message = message;
        }

        @Override
        public void run() {
        	System.out.println("in run @ messageBroadcaster");
            fairLock.lock();
            try {
            	observable.setChanged();
            	observable.notifyObservers(message);
            } finally {
                fairLock.unlock();
            }
        }
    }


}
