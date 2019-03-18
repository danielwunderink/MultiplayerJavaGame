package edu.dordt.cmsc.daniel.game;

public class GameLoop extends Thread {
	
	Game game = Game.getInstance();
	
	Player player;
	KeyHandler keyHandler;
	GameScreen gameScreen;
	
	static final long FRAME_TIME_NS = 16666666;
	
	public GameLoop() {
		this.player = game.getPlayer();
		this.keyHandler = game.getKeyHandler();
		this.gameScreen = game.getGameScreen();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		int fps;
		while (game.getGameRunning()) {
		  long current = System.nanoTime();
		  long elapsed = current - lastTime;
		  player.processMovement(elapsed, keyHandler);
		  gameScreen.repaint();
		  lastTime = current;
		  try {
			  long waitTime = (current + FRAME_TIME_NS - System.nanoTime())/1000000;
			  if (waitTime > 0) {
				  sleep(waitTime);
			  }
			  fps = getFPS(System.nanoTime()-current);
		  } catch (InterruptedException e) {
			  e.printStackTrace();
		  }
		  //System.out.println(player.getX());
		}
		System.out.println("Game Loop Ended");
	}
	
	public int getFPS(long frameTimeNS) {
		double frameTimeS = (double)frameTimeNS/1000000000;
		int fps = (int) Math.round(1/frameTimeS);
		//System.out.println(fps);
		return fps;
	}
	
}

