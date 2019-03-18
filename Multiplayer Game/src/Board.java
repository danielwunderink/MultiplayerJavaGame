import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Board extends JPanel {

    private int sizeWidth = 300;
    private int sizeHeight = 300;
    private int offsetWidth = 30;
    private int offsetHeight = 30;
    private int scale = 10;
    Snake snake=Snake.getInstance();

    public void paintComponent(Graphics g) {


            super.paintComponent(g);

            //super.update(g);
            ArrayList<Point> points = Snake.getInstance().getSnakeLocation();
            g.setColor(Color.BLACK);
            g.fillRect(offsetWidth - scale, offsetHeight - scale, sizeWidth + 2 * scale, sizeHeight + 2 * scale);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(offsetWidth, offsetHeight, sizeWidth, sizeHeight);

            g.setColor(Color.BLACK);
            for (int i = 1; i < points.size(); i++) {
                g.fillRect(snake.getSnakeLocation().get(i).x, snake.getSnakeLocation().get(i).y, scale, scale);
            }
            g.setColor(Color.RED);
            g.fillRect(snake.getSnakeLocation().get(0).x, snake.getSnakeLocation().get(0).y, scale, scale);

            g.setColor(Color.BLUE);
            g.fillRect(snake.getFoodLocation().x, snake.getFoodLocation().y, scale, scale);

            g.setColor(Color.RED);
            Font font = new Font("Verdana", Font.BOLD, 12);
            g.setFont(font);
            FontMetrics fm = g.getFontMetrics();
            String score = "Score: " + snake.getScore() + "           Speed: " + snake.getSpeed();
            g.drawString(score, (offsetWidth * 2 + sizeWidth) / 2 - fm.stringWidth(score) / 2, offsetHeight / 2);


            if (!snake.getIsAlive()) {


                font = new Font("Verdana", Font.BOLD, 12);
                g.setFont(font);
                String gameOver1 = "CHOOSE THE SPEED";
                String gameOver2 = "BEFORE STARTING NEW GAME";
                fm = g.getFontMetrics();

                g.setColor(Color.red);
                g.drawString(gameOver1, (offsetWidth * 2 + sizeWidth) / 2 - fm.stringWidth(gameOver1) / 2, (offsetHeight + sizeHeight) / 2);
                g.drawString(gameOver2, (offsetWidth * 2 + sizeWidth) / 2 - fm.stringWidth(gameOver2) / 2, (offsetHeight + sizeHeight + 40) / 2);
                //String speed = "Game speed: " + snake.getSpeed();
                //g.drawString(speed,(offsetWidth*2+sizeWidth)/2-fm.stringWidth(speed)/2,(offsetHeight+sizeHeight)/2+70);
            }
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