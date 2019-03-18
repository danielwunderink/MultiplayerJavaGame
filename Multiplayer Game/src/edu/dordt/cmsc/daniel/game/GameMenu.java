package edu.dordt.cmsc.daniel.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameMenu extends JPanel {
	
	Game game = Game.getInstance();
	
	private JButton startGame;
	private JTextField ipField;
	private JTextField portField;
	private JTextField nameField;
	private JComboBox<String> colorBox;
	private String[] colors = {"black","blue","cyan","darkgray","gray","green","yellow","lightgray","magneta","orange","pink","red","white"};
	
	private JTextArea nameText;
	private JTextArea colorText;
	private JTextArea ipText;
	private JTextArea portText;	
	
	private JPanel nameTextPanel;
	private JPanel colorTextPanel;
	private JPanel ipTextPanel;
	private JPanel portTextPanel;
	
	private int width = game.getGameScreen().getWidth();
	private int height = game.getGameScreen().getHeight();
	private int offsetWidth = game.getGameScreen().getOffsetHeight();
	private int offsetHeight = game.getGameScreen().getOffsetWidth();
	
	private String colorString;
	private Color color;
	private String name;
	private String IP;
	private int port;
	
	public GameMenu() {
		this.setPreferredSize(new Dimension(width+offsetWidth, height+offsetHeight));
		
		startGame = new JButton("Start Game");
		nameField = new JTextField();
		colorBox = new JComboBox<String>();
		for (String color : colors) {
			colorBox.addItem(color);
		}
		ipField = new JTextField();
		portField = new JTextField();
		
		nameText = new JTextArea("Name:");
		colorText = new JTextArea("Select Color:");
		ipText = new JTextArea("Server IP:");
		portText = new JTextArea("Server Port (default is 25565):");
		
		nameTextPanel = newTextAreaPanel(nameText);
		colorTextPanel = newTextAreaPanel(colorText);		
		ipTextPanel = newTextAreaPanel(ipText);
		portTextPanel = newTextAreaPanel(portText);
		
		this.setLayout(new GridLayout(9, 1, 70, 2));		
		
		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println("here");
					name = nameField.getText();
					IP = ipField.getText();
					port = Integer.valueOf(portField.getText());
					colorString = (String)colorBox.getSelectedItem();
					color = MyColor.getColor(colorString);
					
					game.setPlayerName(name);
					game.setPlayerColor(color);
					game.setServerIP(IP);
					game.setServerPort(port);
					game.setGameRunning(true);
				} catch (NullPointerException | NumberFormatException exception) {
					//do nothing
				}
			}			
		});
		
		this.add(nameTextPanel);
		this.add(nameField);
		this.add(colorTextPanel);
		this.add(colorBox);
		this.add(ipTextPanel);
		this.add(ipField);
		this.add(portTextPanel);
		this.add(portField);
		this.add(startGame);
		
	}
	
	public JPanel newTextAreaPanel(JTextArea text) {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(text, BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * A class to get the Color value from a string color name
	 */
	public static class MyColor {
	    private static Color color;
	    
		/**
		 * Get the color from a string name
		 * 
		 * @param col name of the color
		 * @return White if no color is given, otherwise the Color object
		 */
		static Color getColor(String col) {
		    switch (col.toLowerCase()) {
		    case "black":
		        color = Color.BLACK;
		        break;
		    case "blue":
		        color = Color.BLUE;
		        break;
		    case "cyan":
		        color = Color.CYAN;
		        break;
		    case "darkgray":
		        color = Color.DARK_GRAY;
		        break;
		    case "gray":
		        color = Color.GRAY;
		        break;
		    case "green":
		        color = Color.GREEN;
		        break;
	
		    case "yellow":
		        color = Color.YELLOW;
		        break;
		    case "lightgray":
		        color = Color.LIGHT_GRAY;
		        break;
		    case "magneta":
		        color = Color.MAGENTA;
		        break;
		    case "orange":
		        color = Color.ORANGE;
		        break;
		    case "pink":
		        color = Color.PINK;
		        break;
		    case "red":
		        color = Color.RED;
		        break;
		    case "white":
		        color = Color.WHITE;
		        break;
		        }
		    return color;
		    }
	}

}
