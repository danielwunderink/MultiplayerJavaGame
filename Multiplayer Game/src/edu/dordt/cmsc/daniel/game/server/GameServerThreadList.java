package edu.dordt.cmsc.daniel.game.server;

import java.util.ArrayList;
import java.util.Observable;
import edu.dordt.cmsc.daniel.game.Player;

public class GameServerThreadList extends Observable {
	
	ArrayList<GameServerThread> threadList = new ArrayList<GameServerThread>();

	public void addThread(GameServerThread serverThread) {
		threadList.add(serverThread);
	}
	
	public void removeThread(GameServerThread serverThread) {
		threadList.remove(serverThread);
	}

}
