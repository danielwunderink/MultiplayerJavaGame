package edu.dordt.cmsc.daniel.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;


/**
 * Game class with proposed functionality: 
 *  - initialize GUI window
 *  - display players on the window
 *  - receive input from server
 *  - send output to server from button presses
 *  
 *  
 * @author Daniel.Wunderink
 *
 */
public class Game {
	
	private static final Game game = new Game();
	
	private int sizeWidth;
    private int sizeHeight;
    private int offsetWidth;
    private int offsetHeight;
    private int scale;
    
    private JFrame frame;
    
    private boolean gameRunning;
    
    private GameScreen gameScreen;
    private GameMenu gameMenu;
    private Player player;
    private KeyHandler keyHandler;
    private ArrayList<Player> playerList;
    
    private String playerName;
    private Color playerColor;
    private String serverIP;
    private int serverPort;
	
	/**
	 * Starts GUI window with panels for buttons and the game screen.
	 */
	public void initialize() {
		frame = new JFrame("Multiplayer Game");
        gameScreen = new GameScreen();
        sizeWidth=gameScreen.getSizeWidth();
        sizeHeight=gameScreen.getSizeHeight();
        offsetHeight=gameScreen.getOffsetHeight();
        offsetWidth=gameScreen.getOffsetWidth();
        scale=gameScreen.getScale();
        
        gameMenu = new GameMenu();
        frame.getContentPane().add(BorderLayout.CENTER, gameMenu);
        frame.setPreferredSize(new Dimension(sizeWidth + 2 * offsetWidth, sizeHeight + 2 * offsetHeight + 50));

        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //menu loop
        gameRunning = false;
        while (!gameRunning) {
        	System.out.println(playerName+" "+serverIP+" "+serverPort);
        }
        
        frame.remove(gameMenu);
        
        playerList = new ArrayList<Player>();        
        keyHandler = new KeyHandler();
        player = new Player((int)(Math.random()*(sizeWidth-50)),(int)(Math.random()*(sizeHeight-50)),50,50,playerColor,playerName);
        
        playerList.add(player);
        frame.addKeyListener(keyHandler);

        frame.getContentPane().add(BorderLayout.CENTER, gameScreen);
        
        frame.pack();
        
        (new GameLoop()).start();
        (new GameClientThread()).start();
        gameRunning = true;
	}
	
	private Game() {
		
	}
	
	public static Game getInstance() {
		return game;
	}
	
	public KeyHandler getKeyHandler() {
		return keyHandler;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}
	
	public boolean getGameRunning() {
		return gameRunning;
	}
	
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public String getServerIP() {
		return serverIP;
	}
	
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}

}
