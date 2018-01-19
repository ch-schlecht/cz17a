package cz17a.gamification.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import data.model.Player;
import data.model.Quiz;

public class GameService {
	private static final String urlHead = "pcai042.informatik.uni-leipzig.de:1810/restTest/webapi/";
	
	public void create_game() {
		//TODO communicate to REST to get quiz_id, player_id
		Game game = new Game(new Quiz(), new ArrayList<Player>());
		game.start();
	}
	
	private void getPlayerFromREST(int player_id) {
		try {
			URL url = new URL(urlHead + "user/{" + player_id + "}");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
			
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed to get User: " + player_id + " from " + url + ". Error code: " + connection.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String output;
			while((output = br.readLine()) != null) {}
			
			//TODO parse JSON STRING to generate Player
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
