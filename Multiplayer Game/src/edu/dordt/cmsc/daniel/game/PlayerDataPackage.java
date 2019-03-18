package edu.dordt.cmsc.daniel.game;

import java.io.Serializable;

public class PlayerDataPackage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5462151854841412L;
	
	private Player playerData;
	private boolean playerDisconnected;
	
	public PlayerDataPackage(Player player) {
		playerDisconnected = false;
		this.playerData = new Player(player);
	}
	
	public PlayerDataPackage(Player player, boolean playerDisconnected) {
		this.playerDisconnected = playerDisconnected;
		this.playerData = new Player(player);
	}
	
	public Player getPlayerData() {
		return playerData;
	}
	
	public boolean hasDisconnected() {
		return playerDisconnected;
	}

}
