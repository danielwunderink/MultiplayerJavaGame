package edu.dordt.cmsc.daniel.game.server;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

import edu.dordt.cmsc.daniel.game.Player;

public class GameServerThreadList extends Observable {
	
	CopyOnWriteArrayList<GameServerThread> threadList = new CopyOnWriteArrayList<GameServerThread>();

	public void addThread(GameServerThread serverThread) {
		threadList.add(serverThread);
	}
	
	public void removeThread(GameServerThread serverThread) {
		threadList.remove(serverThread);
	}

}
