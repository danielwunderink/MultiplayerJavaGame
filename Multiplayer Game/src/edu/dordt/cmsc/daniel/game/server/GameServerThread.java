package edu.dordt.cmsc.daniel.game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.dordt.cmsc.daniel.game.Player;
import edu.dordt.cmsc.daniel.game.PlayerDataPackage;

public class GameServerThread extends Thread implements Observer {

	ObjectOutputStream os;
	ObjectInputStream is;
    
    Player clientPlayer;
	Socket clientSocket;
	
	GameServerThreadList threadList;
	
	ArrayList<GameServerThread> connections = new ArrayList<GameServerThread>();
	
	/**
	 * Constructor that adds this thread to the observable list.
	 * @param clientSocket - the socket for this Thread's client.
	 */
	public GameServerThread(Socket clientSocket, GameServerThreadList threadList) {
		this.clientSocket = clientSocket;
		this.threadList = threadList;
		threadList.addObserver(this);
		threadList.addThread(this);
		connections = threadList.threadList;
		System.out.println("Client has connected: "+connections);
	}
	
	@Override
	public void update(Observable threadList, Object arg) {
		if (threadList.getClass() == GameServerThreadList.class) {
			connections = ((GameServerThreadList) threadList).threadList;
		}		
	}
	
	/**
	 * Prints a message to the output stream that goes to the client attached to this thread.
	 * @param message - A string message
	 * @throws IOException
	 */
	public synchronized void sendData(PlayerDataPackage playerData) throws IOException {
		os.writeObject(new PlayerDataPackage(playerData.getPlayerData(),playerData.hasDisconnected()));
	}
	
	/**
	 * Accesses all other threads except the current one to print a message to each client.
	 * @param message
	 * @throws IOException
	 */
	public void broadcastData(PlayerDataPackage playerData) throws IOException {
		for (GameServerThread connection : connections) {
			if (connection != this) {
				connection.sendData(playerData);
			}
		}
	}
	
	/**
	 * Listens for client input, and broadcasts it to all other clients.
	 */
	public void run() {
		try {			
			os = new ObjectOutputStream(clientSocket.getOutputStream());
			is = new ObjectInputStream(clientSocket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//sends the client input to every other client
		PlayerDataPackage clientInput;
		try {
			clientPlayer = ((PlayerDataPackage)is.readObject()).getPlayerData();
			while ((clientInput = ((PlayerDataPackage) is.readObject())) != null) {
				//System.out.println(this+": "+clientInput);
	        	broadcastData(clientInput);
	        	//System.out.println("sending data...");
	        }
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(clientPlayer.getID() + " has disconnected");
			try {
				broadcastData(new PlayerDataPackage(new Player(clientPlayer),true));
			} catch (IOException e1) {
				System.out.println("Error sending last package");
				e1.printStackTrace();
			}
			threadList.removeThread(this);
			threadList.deleteObserver(this);
		}
		
	}

}
