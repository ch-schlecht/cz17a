package server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LogManager {

	public static Logger logger;
	public static Handler handler;
	
	public final static String path = "C:\\Users\\Michael\\Desktop\\logger"; //TODO
	
	
	public static boolean init() {
		logger = Logger.getLogger(LogManager.class.getName());
		try {
			handler = new FileHandler(path);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
			return false;
		}
		logger.addHandler(handler);
		return true;
	}
	
	public static Logger getLogger() {
		return logger;
	}
}
