package edu.dordt.cmsc.daniel.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GameScreen extends JPanel {
	
	private int sizeWidth = 300;
    private int sizeHeight = 300;
    private int offsetWidth = 30;
    private int offsetHeight = 30;
    private int scale = 10;
    
    Game game = Game.getInstance();

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
//    	
//    	g.setColor(game.getPlayer().getColor());  
//    	g.fillRect(game.getPlayer().getX(), game.getPlayer().getY(), game.getPlayer().getSizeX(), game.getPlayer().getSizeY());

    	ArrayList<Player> players = game.getPlayerList();
    	for (int i=0; i<players.size(); i++) {
    		g.setColor(players.get(i).getColor());  
        	g.fillRect(players.get(i).getX(), players.get(i).getY(), players.get(i).getSizeX(), players.get(i).getSizeY());
    	}
    	
    	//System.out.println(game.getPlayer().getX());

    }
    
    public int getSizeWidth() {
        return sizeWidth;
    }

    public int getOffsetWidth() {
        return offsetWidth;
    }

    public int getSizeHeight(){
        return sizeHeight;
    }

    public int getOffsetHeight() {
        return offsetHeight;
    }

    public int getScale(){
        return scale;
    }

}
