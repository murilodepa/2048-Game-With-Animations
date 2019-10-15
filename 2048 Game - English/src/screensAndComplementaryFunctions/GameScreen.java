/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensAndComplementaryFunctions;

import gameControl.GraphicAndControlScreen;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Murilo Araujo
 */
public class GameScreen {

    // JFrame declaration used
    public static JFrame mainScreen = new JFrame();

    // colorBackground and colorLines declarations used
    private static Color colorBackground, colorLines;

    public GameScreen() {

        colorBackground = new Color(102, 178, 255);
        colorLines = new Color(0, 128, 255);

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, " Error creating the frame!" + exception.getMessage());
            }

            editGameWindow();
        });
    }

    public void editGameWindow() {
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setTitle("JOGO 2048");
        mainScreen.setResizable(false);
        mainScreen.add(new GraphicAndControlScreen(), BorderLayout.CENTER);
        mainScreen.pack();
        mainScreen.setLocationRelativeTo(null);
        mainScreen.setBackground(new Color(102, 178, 255));
        mainScreen.setVisible(true);
    }

    // Draw the GAME logo as it appears on 3 screens
    public static void printHeader(Graphics2D g) {
        // Draw the logo 2048
        g.setColor(Color.red); // Choosing Red Color
        g.fillRoundRect(265, 14, 135, 135, 25, 25); // Draw o logo
        g.setColor(Color.BLACK); // Choosing Black Color 
        g.drawRoundRect(265, 14, 135, 135, 25, 25); // Draw the logo border
        g.setFont(new Font("Arial", Font.BOLD, 50)); // Write 2048
        g.drawString("2048", 275, 98); // Write 2048

        // Draw RECORD
        g.setColor(Color.red);
        g.fillRoundRect(100, 14, 153, 65, 25, 25);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 14, 153, 65, 25, 25);
        g.setColor(colorBackground);
        g.fillRoundRect(120, 40, 113, 35, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 21)); // RECORD
        g.drawString(" RECORD", 124, 35); // RECORD

        // Draw SCORE
        g.setColor(Color.red);
        g.fillRoundRect(100, 84, 153, 65, 25, 25);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 84, 153, 65, 25, 25);
        g.setColor(colorBackground);
        g.fillRoundRect(120, 110, 113, 35, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 21)); // SCORE
        g.drawString("  SCORE  ", 129, 105); // SCORE

        // Draw NEW GAME 
        g.setColor(Color.red);
        g.fillRoundRect(412, 79, 70, 70, 25, 25);
        g.setColor(Color.BLACK);
        g.drawRoundRect(412, 79, 70, 70, 25, 25);
        g.setFont(new Font("Arial", Font.BOLD, 22)); // NEW GAME
        g.drawString("NEW ", 420, 110); // NEW
        g.drawString("GAME", 415, 135); // GAME
    }

    // Draw the home screen before showing the main
    public static void printHomeScreen(Graphics2D g) {
        // Include border thickness
        g.setStroke(new BasicStroke(7));

        // Drawing the background to place the blocks on it
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 160, 460, 475, 25, 25);
        g.setColor(colorLines);
        g.fillRoundRect(100, 160, 460, 475, 25, 25);

        // Drawing the block to write the 2048 game
        g.setStroke(new BasicStroke(7));
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 180, 400, 150, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 180, 400, 150, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 72)); // Write 2048 game
        g.drawString("2048 GAME", 133, 280); // Write 2048 game

        g.setStroke(new BasicStroke(4));

        // Drawing block to write New Game
        g.drawRoundRect(206, 360, 250, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(206, 360, 250, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40)); // Write new game
        g.drawString(" NEW GAME", 207, 408); // Write new game

        // Drawing block to write GitHub
        g.drawRoundRect(250, 455, 160, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(250, 455, 160, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40)); // Write GitHub
        g.drawString("GITHUB", 253, 505); // Write GitHub

        // Drawing block to write SAIR
        g.setStroke(new BasicStroke(4));
        g.drawRoundRect(250, 550, 160, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(250, 550, 160, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40)); // Write SAIR
        g.drawString("LEAVE", 265, 600); // Write SAIR

    }

    // Win screen if the user gets a block with 2048
    public static void printScreenWon(Graphics2D g) {

        g.setStroke(new BasicStroke(7));

        // Drawing the background to place the blocks on it
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 160, 460, 475, 25, 25);
        g.setColor(colorLines);
        g.fillRoundRect(100, 160, 460, 475, 25, 25);

        // Drawing the block to write CONGRATULATIONS
        g.setStroke(new BasicStroke(7));
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 220, 400, 100, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 220, 400, 100, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 38)); // CONGRATULATIONS
        g.drawString("CONGRATULATIONS", 135, 280); // Write CONGRATULATIONS

        // Drawing the block YOU WON
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(130, 380, 400, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 380, 400, 70, 25, 25);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50)); // Write YOU WON
        g.drawString("  YOU WON!!!  ", 154, 433); // Write YOU WON

        // Drawing the block PLAY AGAIN
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 510, 160, 80, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 510, 160, 80, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("PLAY", 164, 545); // Write PLAY 
        g.drawString("AGAIN", 155, 580); // Write AGAIN

        // Drawing the block LEAVE
        g.drawRoundRect(390, 515, 140, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(390, 515, 140, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35)); // Write LEAVE
        g.drawString("LEAVE", 404, 560); // Write LEAVE
    }

    // Win screen if user gets a block with 2048
    public static void printScreenLost(Graphics2D g) {
        // Simulate a screen if the user loses

        // Choose a thickness to draw as borders on blocks
        g.setStroke(new BasicStroke(7));

        // Drawing the background frame background to put options on it
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 160, 460, 475, 25, 25);
        g.setColor(colorLines);
        g.fillRoundRect(100, 160, 460, 475, 25, 25);

        // Drawing the block WHAT A PITY
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 220, 400, 110, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 220, 400, 110, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 58)); // Write WHAT A PITY
        g.drawString("WHAT A PITY", 140, 296); // Write WHAT A PITY

        // Drawing the block YOU LOST 
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(130, 385, 400, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 385, 400, 70, 25, 25);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50)); // Write YOU LOST
        g.drawString("YOU LOST!!!", 177, 438); // Write YOU LOST

        // Drawing the block PLAY AGAIN
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 510, 160, 80, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 510, 160, 80, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        g.drawString("PLAY", 164, 545); // Write PLAY 
        g.drawString("AGAIN", 155, 580); // Write AGAIN

        // Drawing the block LEAVE
        g.drawRoundRect(390, 515, 140, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(390, 515, 140, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 35)); // Write LEAVE
        g.drawString("LEAVE", 404, 560); // Write LEAVE
    }
}
