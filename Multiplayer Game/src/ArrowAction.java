import javax.swing.*;
import java.awt.event.ActionEvent;

public class ArrowAction extends AbstractAction {
    Snake snake=Snake.getInstance();
    private String cmd;

    public ArrowAction(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((cmd.equalsIgnoreCase("LeftArrow")) && !snake.getDirection().equals("RIGHT") && !snake.getTmpDirection().equals("RIGHT") && !snake.getPause()) {
            snake.setDirection("LEFT");
        } else if (cmd.equalsIgnoreCase("RightArrow") && !snake.getDirection().equals("LEFT") && !snake.getTmpDirection().equals("LEFT")&& !snake.getPause()) {
            snake.setDirection("RIGHT");
        } else if (cmd.equalsIgnoreCase("UpArrow")&& !snake.getDirection().equals("DOWN") && !snake.getTmpDirection().equals("DOWN")&& !snake.getPause()) {
            snake.setDirection("UP");
        } else if (cmd.equalsIgnoreCase("DownArrow")&& !snake.getDirection().equals("UP") && !snake.getTmpDirection().equals("UP")&& !snake.getPause()) {
            snake.setDirection("DOWN");
        } else if (cmd.equalsIgnoreCase("Space")) {
            snake.spacePressed();
        }
    }

}