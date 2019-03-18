package edu.dordt.cmsc.daniel.game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Contains location of player. Receives key presses from KeyHandler.
 * @author Daniel.Wunderink
 *
 */
public class Player extends Observable implements Serializable {
	
	private static final long serialVersionUID = 2080754940594674112L;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean downPressed = false;
	
	private int x;
	private int y;
	private int sizeX;
	private int sizeY;
	
	private Color color;
	
	private int speed = 5;
	private String ID;
	
	public Player() {
		this.x = 50;
		this.y = 50;
		this.sizeX = 50;
		this.sizeY = 50;
		this.color = Color.BLUE;
		this.ID = "Player1";
	}
	
	/**
	 * Full Constructor.
	 * @param x
	 * @param y
	 * @param sizeX
	 * @param sizeY
	 * @param color
	 * @param ID
	 */
	public Player(int x, int y, int sizeX, int sizeY, Color color, String ID) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.color = color;
		this.ID = ID;
	}
	
	public Player(Player player) {
		this.x = player.x;
		this.y = player.y;
		this.sizeX = player.sizeX;
		this.sizeY = player.sizeY;
		this.color = player.color;
		this.ID = player.ID;
	}
	
	public void setControlVariables(KeyHandler keys) {
		ArrayList<Boolean> controlList = keys.getControlList();
		leftPressed = controlList.get(0);
		rightPressed = controlList.get(1);
		upPressed = controlList.get(2);
		downPressed = controlList.get(3);
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public void processMovement(long timeElapsed, KeyHandler keyHandler) {
		setControlVariables(keyHandler);
		if (leftPressed) {
			x = x - speed;
		}
		if (rightPressed) {
			x = x + speed;
		}
		if (upPressed) {
			y = y - speed;
		}
		if (downPressed) {
			y = y + speed;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	
	public int getSizeY() {
		return sizeY;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getID() {
		return ID;
	}

}
