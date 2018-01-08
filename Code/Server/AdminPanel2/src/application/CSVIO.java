package application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import server.LogManager;

/**
 * Class to read Questions/Answers from CSV Files
 * @author Michael
 * @version 1.1
 */
public class CSVIO {

	//Reader
	private BufferedReader br;
	
	/**
	 * Contructor
	 * setting up BufferedReader
	 * @param BufferedReader reader linked to  File
	 * @since 1.0
	 */
	public CSVIO(BufferedReader br){
		this.br = br;
	}
	
	/**
	 * Convert CSV into ArrayList of String[]
	 * ArrayList contains rows
	 * String[]  contains coloumns
	 * @return Data with rows in ArrayList and coulumns in String-Array
	 * @since 1.0
	 */
	public ArrayList<String[]> read(){
		ArrayList<String[]> list = new ArrayList<>(); //list for all rows
		String line = ""; //line for reader
		try {
			while(null!=(line=br.readLine())){ //while not eof , for every row
				list.add(line.split(";")); //every coulumn ends with an ';'
			}
			
		} catch (IOException e) {
			LogManager.getLogger().log(Level.WARNING, "Unable to Convert user CSV", e);
		
		}
		return list;
	}
	
	
}
