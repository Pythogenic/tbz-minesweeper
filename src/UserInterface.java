import javax.swing.*;
import java.awt.*;

public class UserInterface {

    private final int buttonWidth = 30;
    private final JFrame frame = new JFrame("Minesweeper: Afghanistan edition");

    public void generate(int amountOfX, int amountOfY) {
        JFrame jFrame = new JFrame("Minesweeper");
        jFrame.setSize(amountOfX * buttonWidth,amountOfY * buttonWidth);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        generateButton();
    }

    public void generateButton() {
        JButton button = new JButton("Bila isch en lelek");
        button.setPreferredSize(new Dimension(100,30));
        frame.add(button);
    }


}
