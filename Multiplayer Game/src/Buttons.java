import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Buttons extends JPanel {

    Snake snake=Snake.getInstance();
    Board board=new Board();
    JButton startGame;
    JButton speedDown;
    JButton speedUp;

    public Buttons(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(board.getSizeWidth()+board.getOffsetWidth(), 20));

        startGame = new JButton("START!");
        startGame.setBackground(Color.red);

        speedDown = new JButton("Speed Down");
        speedUp = new JButton("Speed Up");
        startGame.addActionListener(new startGame());
        speedUp.addActionListener(new SpeedUp());
        speedDown.addActionListener(new SpeedDown());

        startGame.setFocusPainted(false);
        startGame.setFocusable(false);
        speedDown.setFocusPainted(false);
        speedDown.setFocusable(false);
        speedUp.setFocusPainted(false);
        speedUp.setFocusable(false);
        buttonsPanel.setLayout(new GridLayout());
        buttonsPanel.add(startGame);
        buttonsPanel.add(speedDown);
        buttonsPanel.add(speedUp);

        InputMap im = buttonsPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = buttonsPanel.getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "RightArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "LeftArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UpArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DownArrow");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Space");

        am.put("RightArrow", new ArrowAction("RightArrow"));
        am.put("LeftArrow", new ArrowAction("LeftArrow"));
        am.put("UpArrow", new ArrowAction("UpArrow"));
        am.put("DownArrow", new ArrowAction("DownArrow"));
        am.put("Space", new ArrowAction("Space"));
        add(buttonsPanel);

    }

    public void blockButtons() {
        startGame.setText("PAUSE");
        speedUp.setEnabled(false);
        speedDown.setEnabled(false);

    }

    public void enableButtons() {
        startGame.setText("START!");
        speedUp.setEnabled(true);
        speedDown.setEnabled(true);

    }

    private class startGame implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            snake.spacePressed();
        }
    }

    private class SpeedUp implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            snake.increaseSpeed();
            snake.refresh();
        }
    }
    private class SpeedDown implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            snake.decreaseSpeed();
            snake.refresh();
        }
    }
}