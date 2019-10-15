package game_2048;

import gameControl.ReadFile;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import screensAndComplementaryFunctions.GameScreen;

/**
 *
 * @author Murilo Araujo
 */
public class Main {

    static JFrame gameScreen = new JFrame();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "error creating the frame! " + exception.getMessage());
            }

            new ReadFile();
            GameScreen mainScreen = new GameScreen();
        });
    }
}
