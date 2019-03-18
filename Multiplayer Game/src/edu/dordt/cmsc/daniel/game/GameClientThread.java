package edu.dordt.cmsc.daniel.game;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class GameClientThread extends Thread implements Observer {
	
	private Game game = Game.getInstance();
	
	private String serverIP;
	private ArrayList<Player> playerList;
	
	private boolean outputReady = false;
	
	static final long FRAME_TIME_NS = 16666666;
	
	public GameClientThread() {
		game.getPlayer().addObserver(this);
		this.serverIP = game.getServerIP();
		this.playerList = game.getPlayerList();
	}
	
	public void run() {
		startConnection(serverIP, 25565);
	}
	
	public void startConnection(String ip, int port) {
		try(//new socket for the server
				Socket socket = new Socket(ip, port);
				ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
			){
			
			OutputToServerThread outputToServerThread = new OutputToServerThread(os);
			outputToServerThread.start();
			
			Object input;
			try {
				while ((input = is.readObject()) != null) {
					PlayerDataPackage playerData = ((PlayerDataPackage)input);
		        	Player player = playerData.getPlayerData();
		        	boolean found = false;
		        	for (int i=0; i<playerList.size(); i++) {
		        		if (player.getID() == playerList.get(i).getID()) {
		        			playerList.get(i).setLocation(player.getX(), player.getY());
		        			found = true;
		        			//System.out.println("received data");
		        			if (playerData.hasDisconnected()) {
		        				playerList.remove(i);
		        			}
		        		}
		        	}
		        	if (found) {
		        		found = false;
		        	} else {
		        		playerList.add(new Player(player));
		        	}
		        }
			} catch (IOException | ClassNotFoundException e) {
				System.out.println(playerList);
				for (Player player : playerList) {
					System.out.println(player.getID());
				}
				e.printStackTrace();
			}
	        
	        socket.close();
		} catch (Exception e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Observable clientPlayer, Object arg) {
		outputReady = true;
	}
	
	public boolean getOutputReady() {
		return outputReady;
	}
	
	public void setOutputReady(boolean outputReady) {
		this.outputReady = outputReady;
	}
	
	class OutputToServerThread extends Thread {
		
		private Game game = Game.getInstance();
		
		private ObjectOutputStream os;
		private Player player;
		
		public OutputToServerThread(ObjectOutputStream os) {
			this.os = os;
			this.player = game.getPlayer();
		}
		
		public void run() {
			while (true) {
				try {
					os.writeObject(new PlayerDataPackage(player));
					sleep(16);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

}
