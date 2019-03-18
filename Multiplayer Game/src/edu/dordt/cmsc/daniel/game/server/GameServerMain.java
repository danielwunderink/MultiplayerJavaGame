package edu.dordt.cmsc.daniel.game.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServerMain {
	
	//static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 25565;
    
    public static void main(String args[]) {
    	GameServerThreadList serverThreads = new GameServerThreadList();
    	try {
    		//create the socket server object
    		server = new ServerSocket(port);
            boolean running = true;
            while(running){
                System.out.println("Waiting for client request");
                //creating socket and waiting for client connection
                Socket clientSocket = server.accept();
                (new GameServerThread(clientSocket, serverThreads)).start();
            }
    	} catch (IOException e){
    		e.printStackTrace();
    	}
        
    }

}
