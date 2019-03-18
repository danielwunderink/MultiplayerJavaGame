import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Snake implements ActionListener {


    private int sizeWidth;
    private int sizeHeight;
    private int offsetWidth;
    private int offsetHeight;
    private int scale;
    private ArrayList<Point> snakeLocation;
    private static Point food;
    private String direction = "RIGHT";
    private String tmpDirection = "RIGHT";

    private static final Snake snake = new Snake();

    private Integer delay;
    private Boolean isPaused = false;
    private Boolean isAlive = false;
    private Timer timer;
    private Board board;
    private Buttons buttons;
    private JFrame frame;

    private Integer score=0;
    private int speed=5;

    private Snake() {

    }

    public static Snake getInstance() {
        return snake;
    }

    public void createBoard() {
        frame = new JFrame("Typical Snake Game");
        snakeLocation = new ArrayList<>();
        snakeLocation.add(new Point(-100,-100));
        food = new Point(-100,-100);
        board = new Board();
        sizeWidth=board.getSizeWidth();
        sizeHeight=board.getSizeHeight();
        offsetHeight=board.getOffsetHeight();
        offsetWidth=board.getOffsetWidth();
        scale=board.getScale();

        buttons = new Buttons();
        frame.getContentPane().add(BorderLayout.CENTER, board);
        frame.getContentPane().add(BorderLayout.SOUTH, buttons);
        frame.setPreferredSize(new Dimension(sizeWidth + 2 * offsetWidth, sizeHeight + 2 * offsetHeight + 50));

        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void startGame() {
        delay=100+(5-speed)*15;
        System.out.println(delay);
        timer = new Timer(delay, this);
        System.out.println(delay);
        if(frame==null){
            snake.createBoard();
        }
        score=0;
        direction="RIGHT";
        snakeLocation.clear();

        for(int i=0;i<6;i++){
            snakeLocation.add(new Point(Math.round((sizeWidth+offsetWidth)/(2*10))*10-i*10, Math.round((sizeHeight+offsetHeight)/(2*10))*10));
        }

        newFood();

        buttons.blockButtons();
        isAlive = true;
        isPaused = false;
        timer.start();
    }



    public ArrayList<Point> getSnakeLocation() {
        return snakeLocation;
    }

    public Point getFoodLocation() {
        return food;
    }
    public Boolean getIsAlive() { return isAlive;}

    public void setDirection(String dir) {
        snake.direction = dir;
    }
    public String getDirection() {
        return snake.direction;
    }

    public String getTmpDirection(){
        return snake.tmpDirection;
    }

    public void spacePressed(){

        if(!isAlive) {
            snake.startGame();
        }
        else {
            isPaused^=true;
        }
    }

    public Boolean getPause(){
        return isPaused;
    }

    public void move() {
        if (direction.equals("RIGHT")) {
            snakeLocation.add(0, new Point(snakeLocation.get(0).x + 10, snakeLocation.get(0).y + 0));
        } else if (direction.equals("LEFT")) {
            snakeLocation.add(0, new Point(snakeLocation.get(0).x - 10, snakeLocation.get(0).y + 0));
        } else if (direction.equals("UP")) {
            snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y - 10));
        } else if (direction.equals("DOWN")) {
            snakeLocation.add(0, new Point(snakeLocation.get(0).x, snakeLocation.get(0).y + 10));
        }
    }


    public void actionPerformed(ActionEvent arg0) {
        if(!isPaused && isAlive) {
            tmpDirection = direction;
            snake.move();

            snake.checkPosition();

            //refresh();
            board.repaint();

        }  else if(!isAlive) {
            timer.stop();
            buttons.enableButtons();
        }


    }

    public void newFood() {
        Random random = new Random();
        Point point;
        point = new Point(random.nextInt(sizeWidth / scale) * scale + offsetWidth, random.nextInt(sizeHeight / scale) * scale + offsetHeight);

        while (Arrays.asList(getSnakeLocation()).contains(point)) {
            point = new Point(random.nextInt(sizeWidth / scale) * scale + offsetWidth, random.nextInt(sizeHeight / scale) * scale + offsetHeight);
        }

        food = point;
    }

    public void increaseScore() {
       score=score+speed;
    }
    public int getScore(){
        return score;
    }

    public void increaseSpeed(){
        if(speed<10) {
            speed += 1;
        }
    }

    public void decreaseSpeed(){
        if(speed>1) {
            speed -= 1;
        }
    }

    public int getSpeed(){
        return speed;
    }

    public void refresh(){

        board.repaint();
    }


    public void checkPosition(){
        for (int j = 1; j < snakeLocation.size()-1; j++) {
            if (snakeLocation.get(0).equals(snakeLocation.get(j))) {
                isAlive = false;
            }
        }


            if (snakeLocation.get(0).x==offsetWidth-scale || snakeLocation.get(0).x==sizeWidth+offsetWidth ||snakeLocation.get(0).y==offsetHeight-scale || snakeLocation.get(0).y==sizeHeight+offsetHeight) {
                isAlive = false;
            }


        if (snakeLocation.get(0).equals(food)) {
            newFood();
            increaseScore();
        }
        else {
            snakeLocation.remove(snakeLocation.size() - 1);
        }

    }
}
