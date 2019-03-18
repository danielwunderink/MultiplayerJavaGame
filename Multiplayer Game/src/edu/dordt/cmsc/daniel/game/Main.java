package edu.dordt.cmsc.daniel.game;

/**
 * Boots up the game. Calls the initialize method on the instance of a Game class.
 * @author Daniel.Wunderink
 *
 */
public class Main {

	public static void main(String[] args) {
		Game.getInstance().initialize();
	}

}
