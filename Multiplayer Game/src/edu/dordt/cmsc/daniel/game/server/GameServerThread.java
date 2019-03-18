package edu.dordt.cmsc.daniel.game.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.dordt.cmsc.daniel.game.Player;

public class GameServerThread extends Thread implements Observer {

	ObjectOutputStream os;
	ObjectInputStream is;
    
    String clientName;
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
	public void sendData(Player player) throws IOException {
		os.writeObject(new Player(player));
	}
	
	/**
	 * Accesses all other threads to print a message to each client.
	 * @param message
	 * @throws IOException
	 */
	public void broadcastData(Player player) throws IOException {
		for (GameServerThread connection : connections) {
			connection.sendData(player);
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
		Player clientInput;
		try {
			while ((clientInput = ((Player) is.readObject())) != null) {
	        	broadcastData(clientInput);
	        	//System.out.println("sending data...");
	        }
			System.out.println(clientName + " has left the chat room");
			threadList.removeThread(this);
			threadList.deleteObserver(this);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(clientName + " has left the chat room");
			e.printStackTrace();
		}
		
	}

}
