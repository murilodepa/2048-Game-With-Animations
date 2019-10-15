/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensAndComplementaryFunctions;

import gameControl.GraphicAndControlScreen;
import static gameControl.MoveBlocks.returnMatrix;
import java.awt.Graphics2D;

/**
 *
 * @author Murilo Araujo
 */
public class ComplementaryFunctions extends GraphicAndControlScreen {

    // Signals in case user decides to return game
    public static void returned() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameBoard[i][j] = returnMatrix[i][j];
                flagReturn = false;
                flagCanBeReturned = false;
                score = oldScore;
            }
        }
    }

    // Function for if the user decides to restart the game
    public static void restart() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameBoard[i][j] = 0;
                flagStart = true;
                flagRestart = false;
                score = 0;
            }
        }
    }

    // See the number of digits to center the punctuation position where it appears
    public static void organizeDigitsForPrinting(Graphics2D g, String value, int points, int y) {
        if (points < 10) {
            g.drawString(value, 170, y);
        } else {
            if (points < 100) {
                g.drawString(value, 161, y);
            } else {
                if (points < 1000) {
                    g.drawString(value, 152, y);
                } else {
                    if (points < 10000) {
                        g.drawString(value, 143, y);
                    } else {
                        if (points < 100000) {
                            g.drawString(value, 134, y);
                        } else {
                            g.drawString(value, 125, y);
                        }
                    }
                }
            }
        }
    }
}
