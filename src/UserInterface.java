import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UserInterface {

    private final int buttonWidth = 50;
    private final JFrame frame = new JFrame("Minesweeper: Afghanistan edition");
    private final JPanel gamePanel = new JPanel();
    private final JPanel infoPanel = new JPanel();
    private final List<ButtonConnection> buttonConnections = new ArrayList<>();
    private boolean gameOver = false;

    public void generate() {
        int width = MinesweeperApplication.game.getWidth();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width * buttonWidth, (width * buttonWidth) + 50);

        JButton resetButton = new JButton("reset");
        resetButton.addActionListener(e -> MinesweeperApplication.game.reset());
        infoPanel.add(resetButton);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split.setTopComponent(infoPanel);
        split.setBottomComponent(gamePanel);
        gamePanel.setLayout(new GridLayout(width, width));
        frame.add(split);
        generateButtons();
        frame.setVisible(true);
    }

    private void generateButtons() {
        int width = MinesweeperApplication.game.getWidth();
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= width; y++) {
                JButton button = new JButton();
                Field field = MinesweeperApplication.game.getFieldByCoordinate(new Coordinate(x, y));
                buttonConnections.add(new ButtonConnection(button, field));
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (button.isEnabled() && !gameOver) {
                            if (e.getButton() == MouseEvent.BUTTON1) {
                                if (!field.isMarkedAsBomb()) {
                                    field.check();
                                }
                            } else if (e.getButton() == MouseEvent.BUTTON3) {
                                field.toogleMarkedAsBomb();
                            }
                            updateAfterAction();
                        }
                    }
                });
                gamePanel.add(button);
            }
        }
    }

    public void exit() {
        frame.setVisible(false);
    }

    private void updateAfterAction() {
        for (ButtonConnection connection : buttonConnections) {
            JButton button = connection.getButton();
            Field field = connection.getField();
            button.setText("");
            if (field.isMarkedAsBomb()) {
                button.setText("P");
                button.setForeground(Color.RED);
            }
            if (field.isVisible()) {
                if (field.getClass() == EmptyField.class) {
                    EmptyField emptyField = (EmptyField) field;
                    int displayNumber = emptyField.getDisplayNumber();
                    button.setEnabled(false);
                    if (displayNumber > 0) {
                        button.setText(displayNumber + "");
                    }
                } else {
                    button.setBackground(Color.RED);
                }
            }
        }
    }

    public void showLostDialog() {
        JOptionPane.showMessageDialog(gamePanel,"Game Over!");
        gameOver = true;
    }

}
