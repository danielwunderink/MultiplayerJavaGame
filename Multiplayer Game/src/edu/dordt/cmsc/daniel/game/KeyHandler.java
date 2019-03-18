package edu.dordt.cmsc.daniel.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Observable;

public class KeyHandler implements KeyListener {

	int left = KeyEvent.VK_LEFT;
	int right = KeyEvent.VK_RIGHT;
	int up = KeyEvent.VK_UP;
	int down = KeyEvent.VK_DOWN;
	
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean upPressed = false;
	boolean downPressed = false;
	
	public synchronized ArrayList<Boolean> getControlList() {
		ArrayList<Boolean> controlList = new ArrayList<Boolean>();
		controlList.add(leftPressed);
		controlList.add(rightPressed);
		controlList.add(upPressed);
		controlList.add(downPressed);
		return controlList;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == left)
			leftPressed = true;
		if (key == right)
			rightPressed = true;
		if (key == up)
			upPressed = true;
		if (key == down)
			downPressed = true;		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == left)
			leftPressed = false;
		if (key == right)
			rightPressed = false;
		if (key == up)
			upPressed = false;
		if (key == down)
			downPressed = false;		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
