/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameControl;

import static gameControl.MoveBlocks.actuationEasterEgg;
import static gameControl.MoveBlocks.checkIfLost;
import static gameControl.MoveBlocks.checkIfWon;
import static gameControl.MoveBlocks.clearMatrix;
import static gameControl.MoveBlocks.copyMatrixAndSetVariables;
import static gameControl.MoveBlocks.generateRandom;
import static gameControl.MoveBlocks.printMatrix;
import static gameControl.MoveBlocks.returnMatrix;
import static gameControl.MoveBlocks.shockColumn;
import static gameControl.MoveBlocks.shockLine;
import static gameControl.MoveBlocks.sumColumn;
import static gameControl.MoveBlocks.sumLine;
import static gameControl.WriteFile.getRecord;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.Graphics2D;
import screensAndComplementaryFunctions.ComplementaryFunctions;
import static screensAndComplementaryFunctions.ComplementaryFunctions.organizeDigitsForPrinting;
import static screensAndComplementaryFunctions.ComplementaryFunctions.restart;
import static screensAndComplementaryFunctions.GameScreen.mainScreen;
import static screensAndComplementaryFunctions.GameScreen.printHeader;
import static screensAndComplementaryFunctions.GameScreen.printHomeScreen;
import static screensAndComplementaryFunctions.GameScreen.printScreenLost;
import static screensAndComplementaryFunctions.GameScreen.printScreenWon;

/**
 *
 * @author Murilo Araujo
 */
public class GraphicAndControlScreen extends JPanel implements MouseListener {

    // Declaring of all variables used in the project
    protected static int[][] gameBoard = new int[4][4];

    protected static int score = 0, oldScore = 0, k = 0, x = 97, y = 167, flagUpOrDown = 0;

    private final BufferedImage[][] lightningImage = new BufferedImage[4][3];
    private final BufferedImage[][] blockImage_4 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_8 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_16 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_32 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_64 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_128 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_256 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_512 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_1024 = new BufferedImage[4][4];
    private final BufferedImage[][] blockImage_2048 = new BufferedImage[4][4];

    // Declaring the image of returning play, using only one image of this
    private BufferedImage returnImage;

    // Declaring all variables of boolean type used
    protected static boolean flagStart = true, flagLost = false, flagRestart = false, flagReturn = false, flagGameScreen = false;
    protected static boolean flagEasterEgg = false, flagHomeScreen = true, flagWon = false, flagCanBeReturned = false;

    private Integer clickX, clickY, releasedX, releasedY;

    private Color colorBackground, colorLines, colorBlock_2, colorBlock_4, colorBlock_8, colorBlock_16, colorBlock_32, colorBlock_64;
    private Color colorBlock_128, colorBlock_256, colorBlock_512, colorBlock_1024, colorBlock_2048;

    // Declaring all variables of type String
    private String valueScore, valueRecord;

    // Declaring variables of type Playes and Music
    private Player music;
    Music mp3 = new Music();

    // Creating a constructor for the ScreenScreen classControl
    public GraphicAndControlScreen() {
        setPreferredSize(new Dimension(660, 660)); // Size of my JFrame
        setFocusable(true);                        // setting the "focusable" option
        instantiateComponents();                   // Calling my function to instantiate all my components in the builder
        new checkShockOrSum().start();             // Check shocks or sum between blocks
        moveKeyboard();                            // Check keyboard movement
        addMouseListener(this);                    // Check mouse clicks and gestures
    }

    // Instantiating all components and elements used in the game 2048
    public void instantiateComponents() {
        colorBackground = new Color(102, 178, 255);
        colorLines = new Color(0, 128, 255);
        colorBlock_2 = new Color(220, 220, 220);
        colorBlock_4 = new Color(192, 192, 192);
        colorBlock_8 = new Color(255, 180, 68);
        colorBlock_16 = new Color(255, 64, 64);
        colorBlock_32 = new Color(255, 29, 11);
        colorBlock_64 = new Color(255, 0, 0);
        colorBlock_128 = new Color(251, 236, 93);
        colorBlock_256 = new Color(253, 233, 16);
        colorBlock_512 = new Color(255, 215, 0);
        colorBlock_1024 = new Color(255, 255, 64);
        colorBlock_2048 = new Color(255, 255, 0);

        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    lightningImage[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\lightning.gif"));
                }
            }
        } catch (IOException e) {
        }

        // Instantiating all images used in the game for each matrix position and setting each image for each block join
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    blockImage_4[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\stars_4.gif"));
                    blockImage_8[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\sparkles_8.gif"));
                    blockImage_16[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\balloons_16.gif"));
                    blockImage_32[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\Sponge_Bob_32.gif"));
                    blockImage_64[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\avocados_64.gif"));
                    blockImage_128[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\tenor_128.gif"));
                    blockImage_256[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\giphy_256.gif"));
                    blockImage_512[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\tenor_512.gif"));
                    blockImage_1024[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\tenor_1024.gif"));
                    blockImage_2048[i][j] = ImageIO.read(getClass().getResourceAsStream("Images\\fireworks_2048.gif"));
                }
            }
        } catch (IOException e) {
        }

        // Instantiating and setting return image
        try {
            returnImage = ImageIO.read(getClass().getResourceAsStream("Images\\return.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Instantiating variables as -1, as I use positions 0 of the matrix, conflicts occur
        shockLine[k] = -1;
        shockColumn[k] = -1;
        sumLine[k] = -1;
        sumColumn[k] = -1;
    }

    // Drawing to game screens
    public void gameScreen(Graphics2D g) {
        // FlagHomeScreen to display the home screen before showing the main
        if (flagHomeScreen == true) {
            printHomeScreen(g);
        } else {

            // If the user decides to play, has not won or lost, the game is printed and they have new play possibilities.
            if (flagGameScreen == true) {
                // I choose the thickness to be drawing the edges on the blocks
                g.setStroke(new BasicStroke(3));

                // Make the image visible Return
                printHeader(g);

                // Make the image visible Returned
                g.setColor(Color.red);
                g.drawImage(returnImage, 494, 79, 70, 70, this);
                g.setColor(Color.BLACK);
                g.drawRoundRect(494, 79, 70, 70, 1, 1);

                // Flag for if the user decides to restart the game
                if (flagRestart == true) {
                    restart();
                }

                // Convert integer value from punctuation to string
                if (flagReturn == true && flagCanBeReturned == true) {
                    ComplementaryFunctions.returned();
                }

                // Convert integer value from punctuation to string
                valueScore = String.valueOf(score);

                // Convert integer value from record to string
                valueRecord = String.valueOf(getRecord());

                // Sitting in arial font, bold and size 30
                g.setFont(new Font("Arial", Font.BOLD, 30)); // SCORE

                // Function that centers the desired digits, such as punctuation and record
                organizeDigitsForPrinting(g, valueRecord, getRecord(), 68);
                organizeDigitsForPrinting(g, valueScore, score, 139);

                // Drawing the matrix background
                g.setStroke(new BasicStroke(7));
                g.setColor(Color.BLACK);
                g.drawRoundRect(100, 160, 460, 475, 25, 25);
                g.setColor(colorLines);
                g.fillRoundRect(100, 160, 460, 475, 25, 25);

                // Check if user selected (Easter Egg) from game, if not, start game normally
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(3));

                // Verifico se o usuário selecionou a Easter Egg do jogo, se não, inicio normalmente o jogo
                if (flagStart == true && flagEasterEgg != true) {
                    generateRandom(2);
                    generateRandom();

                    // Copy to mainMatrix in returnMatrix
                    copyMatrixAndSetVariables();

                    flagStart = false;
                } else { // If selected, add 2 blocks of value 1024 to win the game
                    if (flagEasterEgg == true && flagStart == true) {
                        actuationEasterEgg();
                    }
                }

                // Print matrix for checks and tests
                printMatrix();

                // Check which blocks will print against my printed matrix
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (gameBoard[i][j] == 0) {
                            g.setColor(colorBackground);
                            g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                        } else {
                            if (gameBoard[i][j] == 2) {
                                g.setColor(colorBlock_2);
                                g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                g.setColor(Color.BLACK);
                                g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                g.setFont(new Font("Arial", Font.BOLD, 40)); //2
                                g.drawString("2", 153 + j * 111, 240 + i * 116); //2
                            } else {
                                if (gameBoard[i][j] == 4) {
                                    g.setColor(colorBlock_4);
                                    g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                    g.setColor(Color.BLACK);
                                    g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                    g.setFont(new Font("Arial", Font.BOLD, 40)); //4
                                    g.drawString("4", 153 + j * 111, 240 + i * 116); //4
                                } else {
                                    if (gameBoard[i][j] == 8) {
                                        g.setColor(colorBlock_8);
                                        g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                        g.setColor(Color.BLACK);
                                        g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                        g.setColor(Color.WHITE);
                                        g.setFont(new Font("Arial", Font.BOLD, 40)); //8
                                        g.drawString("8", 153 + j * 111, 240 + i * 116); //8
                                    } else {
                                        if (gameBoard[i][j] == 16) {
                                            g.setColor(colorBlock_16);
                                            g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                            g.setColor(Color.BLACK);
                                            g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                            g.setColor(Color.WHITE);
                                            g.setFont(new Font("Arial", Font.BOLD, 40)); //16
                                            g.drawString("16", 140 + j * 111, 240 + i * 116); //16
                                        } else {
                                            if (gameBoard[i][j] == 32) {
                                                g.setColor(colorBlock_32);
                                                g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                g.setColor(Color.BLACK);
                                                g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                g.setColor(Color.WHITE);
                                                g.setFont(new Font("Arial", Font.BOLD, 40)); //32
                                                g.drawString("32", 140 + j * 110, 240 + i * 115); //32                                  
                                            } else {
                                                if (gameBoard[i][j] == 64) {
                                                    g.setColor(colorBlock_64);
                                                    g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                    g.setColor(Color.BLACK);
                                                    g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                    g.setColor(Color.WHITE);
                                                    g.setFont(new Font("Arial", Font.BOLD, 40)); //64
                                                    g.drawString("64", 140 + j * 110, 240 + i * 115); //64
                                                } else {
                                                    if (gameBoard[i][j] == 128) {
                                                        g.setColor(colorBlock_128);
                                                        g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                        g.setColor(Color.BLACK);
                                                        g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                        g.setColor(Color.WHITE);
                                                        g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                        g.drawString("128", 130 + j * 110, 240 + i * 115); //128
                                                    } else {
                                                        if (gameBoard[i][j] == 256) {
                                                            g.setColor(colorBlock_256);
                                                            g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                            g.setColor(Color.BLACK);
                                                            g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                            g.setColor(Color.WHITE);
                                                            g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                            g.drawString("256", 130 + j * 110, 240 + i * 115); //128
                                                        } else {
                                                            if (gameBoard[i][j] == 512) {
                                                                g.setColor(colorBlock_512);
                                                                g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                g.setColor(Color.BLACK);
                                                                g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                g.setColor(Color.WHITE);
                                                                g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                                g.drawString("512", 130 + j * 110, 240 + i * 115); //128
                                                            } else {
                                                                if (gameBoard[i][j] == 1024) {
                                                                    g.setColor(colorBlock_1024);
                                                                    g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                    g.setColor(Color.BLACK);
                                                                    g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                    g.setColor(Color.WHITE);
                                                                    g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                                    g.drawString("1024", 120 + j * 110, 240 + i * 115); //128
                                                                } else {
                                                                    if (gameBoard[i][j] == 2048) {
                                                                        g.setColor(colorBlock_2048);
                                                                        g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                        g.setColor(Color.BLACK);
                                                                        g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                        g.setColor(Color.WHITE);
                                                                        g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                                        g.drawString("2048", 120 + j * 110, 240 + i * 115); //128
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }

                // Loop to check for possible horizontal shock animations
                int l = 0;
                try {
                    while (shockLine[l] != -1 && flagUpOrDown == 1) {
                        for (int i = 0; i < 4 && shockLine[l] >= i; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (shockLine[l] == i && shockColumn[l] == j) {
                                    g.drawImage(lightningImage[i][j], 176 + j * 111, 178 + i * 116, 90, 90, this);
                                }
                            }
                        }
                        l += 1;
                    }
                } catch (Exception e) {
                }

                // Loop to check for possible vertical shock animations
                l = 0;
                try {
                    while (shockLine[l] != -1 && flagUpOrDown == 2) {
                        if (shockLine[l] < 3 && shockColumn[l] < 3) {
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 3; j++) {
                                    if (shockLine[l] == i && shockColumn[l] == j) {
                                        g.drawImage(lightningImage[i][j], 121 + i * 111, 237 + j * 116, 90, 90, this);
                                    }
                                }
                            }
                        }
                        l += 1;
                    }
                } catch (Exception e) {
                }

                // Loop to check which animations appear on screen relative to block joining
                l = 0;
                int i = 0, j = 0;
                while (sumLine[l] != -1) {
                    if (gameBoard[(sumLine[l])][(sumColumn[l])] == 4) {
                        g.drawImage(blockImage_4[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                        j += 1;
                        l += 1;
                        if (j == 4) {
                            i += 1;
                        }
                        j = 0;
                    } else {
                        if (gameBoard[(sumLine[l])][(sumColumn[l])] == 8) {
                            g.drawImage(blockImage_8[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                            j += 1;
                            l += 1;
                            if (j == 4) {
                                i += 1;
                            }
                            j = 0;
                        } else {
                            if (gameBoard[(sumLine[l])][(sumColumn[l])] == 16) {
                                g.drawImage(blockImage_16[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                j += 1;
                                l += 1;
                                if (j == 4) {
                                    i += 1;
                                }
                                j = 0;
                            } else {
                                if (gameBoard[(sumLine[l])][(sumColumn[l])] == 32) {
                                    g.drawImage(blockImage_32[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                    j += 1;
                                    l += 1;
                                    if (j == 4) {
                                        i += 1;
                                    }
                                    j = 0;
                                } else {
                                    if (gameBoard[(sumLine[l])][(sumColumn[l])] == 64) {
                                        g.drawImage(blockImage_64[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                        j += 1;
                                        l += 1;
                                        if (j == 4) {
                                            i += 1;
                                        }
                                        j = 0;
                                    } else {
                                        if (gameBoard[(sumLine[l])][(sumColumn[l])] == 128) {
                                            g.drawImage(blockImage_128[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                            j += 1;
                                            l += 1;
                                            if (j == 4) {
                                                i += 1;
                                            }
                                            j = 0;
                                        } else {
                                            if (gameBoard[(sumLine[l])][(sumColumn[l])] == 256) {
                                                g.drawImage(blockImage_256[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                                j += 1;
                                                l += 1;
                                                if (j == 4) {
                                                    i += 1;
                                                }
                                                j = 0;
                                            } else {
                                                if (gameBoard[(sumLine[l])][(sumColumn[l])] == 512) {
                                                    g.drawImage(blockImage_512[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                                    j += 1;
                                                    l += 1;
                                                    if (j == 4) {
                                                        i += 1;
                                                    }
                                                    j = 0;
                                                } else {
                                                    if (gameBoard[(sumLine[l])][(sumColumn[l])] == 1024) {
                                                        g.drawImage(blockImage_1024[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                                        j += 1;
                                                        l += 1;
                                                        if (j == 4) {
                                                            i += 1;
                                                        }
                                                        j = 0;
                                                    } else {
                                                        if (gameBoard[(sumLine[l])][(sumColumn[l])] == 2048) {
                                                            g.drawImage(blockImage_2048[i][j], 115 + sumColumn[l] * 110, 175 + sumLine[l] * 115, 100, 100, this);
                                                            j += 1;
                                                            l += 1;
                                                            if (j == 4) {
                                                                i += 1;
                                                            }
                                                            j = 0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Check if the user won the game, if yes, print the screen indicating victory
        if (flagWon == true) {
            printScreenWon(g);
            flagGameScreen = false;
        } else {
            // Check if the user has lost the game, yes, prints a screen displayed defeat
            if (flagLost == true) {
                printScreenLost(g);
                flagGameScreen = false;
            }
        }
    }

    // Check keyboard movements
    public void moveKeyboard() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                // I always keep the previous score if the player wants to return the play
                oldScore = score;

                // Condition to check if you have selected the left arrow or keyboard "a" key
                if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
                    try {
                        MoveBlocks.left(gameBoard); // Move blocks left
                    } catch (IOException ex) {
                        Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagUpOrDown = 1; // 1 means you moved left or right
                }

                // Condition to check if you have selected the up arrow or "w" key on your keyboard
                if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
                    try {
                        MoveBlocks.up(gameBoard); // Move blocks up
                    } catch (IOException ex) {
                        Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagUpOrDown = 2; // 2 means you moved up or down
                }

                // Condition to check if you have selected the right arrow or the keyboard "d" key
                if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
                    try {
                        MoveBlocks.right(gameBoard); // Move blocks to the right
                    } catch (IOException ex) {
                        Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagUpOrDown = 1; // 1 means you moved left or right
                }

                // Condition to check if you have selected the down arrow or the keyboard "s" key
                if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
                    try {
                        MoveBlocks.down(gameBoard); // Movimento os blocos para down
                    } catch (IOException ex) {
                        Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagUpOrDown = 2; // 2 means you moved up or down
                }

                // I update the impression
                repaint();

                // Print punctuation to make sure it is printing correctly against JFrame
                System.out.println("\n Score: " + getScore());
            }
        });
    }

    // Creating the graphic part of the game
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponents(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gameScreen(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Method triggered when left mouse button is pressed
    @Override
    public void mousePressed(MouseEvent e) {
        // I get the cartesian position relative to JFrame, x and y of the mouse
        try {
            clickX = e.getX();
            clickY = e.getY();
        } catch (java.lang.NullPointerException e1) {
        }
    }

    // Method triggered when the left mouse button is released
    @Override
    public void mouseReleased(MouseEvent e) {
        int deltaX = 0, deltaY = 0;
        try {
            // I get the cartesian position relative to JFrame, x and y of the mouse
            releasedX = e.getX();
            releasedY = e.getY();
        } catch (java.lang.NullPointerException e1) {
        }

        // Check if the user exclusively pressed the mouse on the restart option
        if (clickX > 412 && clickX < 483 && clickY > 79 && clickY < 146) {
            flagRestart = true;
            flagGameScreen = true;
            flagEasterEgg = false;

            clearMatrix(gameBoard);
            clearMatrix(returnMatrix);

            if (flagLost == true || flagWon == true) {
                flagLost = false;
                flagWon = false;
            }
        } else { // Check if the user exclusively pressed the mouse on the return option
            if (clickX > 492 && clickX < 563 && clickY > 79 && clickY < 146 && flagCanBeReturned == true) {
                flagReturn = true;
            } else { // Check if the user has exclusively pressed the mouse on the (Easter Egg) logo
                if (clickX > 267 && clickX < 400 && clickY > 15 && clickY < 141 && flagLost == false && flagEasterEgg != true) {
                    flagEasterEgg = true;
                    score = 0;
                    flagStart = true;
                } else { // Check if the user exclusively pressed the mouse on the NEW GAME option on the Home Screen
                    if (clickX > 210 && clickX < 455 && clickY > 362 && clickY < 431 && flagHomeScreen == true) {
                        flagHomeScreen = false;
                        flagGameScreen = true;
                        mp3.start();
                    } else { // Check if the user exclusively pressed with the mouse on the GitHub option on the Home Screen
                        if (clickX > 249 && clickX < 410 && clickY > 450 && clickY < 525 && flagHomeScreen == true) {
                            try {
                                URI link = new URI("https://github.com/murilodepa/Jogo-2048-com-Animacao");
                                Desktop.getDesktop().browse(link);
                            } catch (Exception erro) {
                            }
                        } else {
                            if (clickX > 249 && clickX < 410 && clickY > 550 && clickY < 615 && flagHomeScreen == true) {
                                flagHomeScreen = false;
                                mainScreen.dispose();
                                System.exit(0);
                            } else { // Verify that the user exclusively pressed the EXIT option on the screen when Losing or Winning
                                if (clickX > 353 && clickX < 507 && clickY > 509 && clickY < 576 && (flagWon == true || flagLost == true)) {
                                    flagWon = false;
                                    flagLost = false;
                                    mainScreen.dispose();
                                    System.exit(0);
                                } else { // Verify that the user exclusively clicked on the NEW GAME option on the screen when Losing or Winning
                                    if (clickX > 133 && clickX < 274 && clickY > 449 && clickY < 592 && (flagWon == true || flagLost == true)) {
                                        flagRestart = true;
                                        flagGameScreen = true;
                                        flagEasterEgg = false;
                                        clearMatrix(gameBoard);
                                        clearMatrix(returnMatrix);
                                        if (flagLost == true || flagWon == true) {
                                            flagLost = false;
                                            flagWon = false;
                                        }
                                    } else { // Check if the user clicked, dragged and released the mouse, wanting to make a movement in the game
                                        if (flagLost != true && flagWon != true && flagHomeScreen != true) {
                                            try {
                                                deltaX = (releasedX - clickX); // Calculate delta X of mouse (when I press and when I release the mouse)
                                                deltaY = (releasedY - clickY); // Calculo o delta Y do mouse (quando pressiono e quando solto o mouse)
                                                //System.out.println("click x: " + clickX + " clickY: " + clickY + "\nreleasedX: " + releasedX + " releasedY: " + releasedY); 

                                                // I always keep the previous score if the player wants to return the play
                                                oldScore = score;

                                                // If deltaX negative and DeltaY negative
                                                if (deltaX < 0 && deltaY < 0) {
                                                    deltaX = (deltaX * (-1));
                                                    deltaY = (deltaY * (-1));
                                                    if (deltaX > deltaY) { // I check if X is bigger, if it is the movement is for left
                                                        try {
                                                            MoveBlocks.left(gameBoard);
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                        flagUpOrDown = 1;
                                                        deltaX = (deltaX * (-1));
                                                        deltaY = (deltaY * (-1));
                                                    } else {
                                                        try {
                                                            // Otherwise the movement is up
                                                            MoveBlocks.up(gameBoard);
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                        flagUpOrDown = 2;
                                                        deltaX = (deltaX * (-1));
                                                        deltaY = (deltaY * (-1));
                                                    }
                                                } else { // DeltaX positive and DeltaY negative
                                                    if (deltaX > 0 && deltaY < 0) {
                                                        deltaY = (deltaY * (-1));

                                                        if (deltaX > deltaY) {
                                                            try {
                                                                // If deltaX is greater than DeltaY, move is to right
                                                                MoveBlocks.right(gameBoard);
                                                            } catch (IOException ex) {
                                                                Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            flagUpOrDown = 1;
                                                            deltaY = (deltaY * (-1));
                                                        } else {
                                                            try {
                                                                // Otherwise the movement is up
                                                                MoveBlocks.up(gameBoard);
                                                            } catch (IOException ex) {
                                                                Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            flagUpOrDown = 2;
                                                            deltaY = (deltaY * (-1));
                                                        }
                                                    } else { // DeltaX positive and DeltaY positive
                                                        if (deltaX > 0 && deltaY > 0) {

                                                            if (deltaX > deltaY) {
                                                                try {
                                                                    // If deltaX is greater than DeltaY, move is to right
                                                                    MoveBlocks.right(gameBoard);
                                                                } catch (IOException ex) {
                                                                    Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                                flagUpOrDown = 1;
                                                            } else {
                                                                try {
                                                                    // Otherwise the movement is down
                                                                    MoveBlocks.down(gameBoard);
                                                                } catch (IOException ex) {
                                                                    Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                                flagUpOrDown = 2;
                                                            }
                                                        } else { // DeltaX negativo e DeltaY positivo
                                                            if (deltaX < 0 && deltaY > 0) {
                                                                deltaX = (deltaX * (-1));

                                                                if (deltaX > deltaY) { // Se deltaX for maior que DeltaY, movimento é para left
                                                                    try {
                                                                        MoveBlocks.left(gameBoard);
                                                                    } catch (IOException ex) {
                                                                        Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                                    flagUpOrDown = 1;
                                                                    deltaX = (deltaX * (-1));
                                                                } else {
                                                                    try {
                                                                        // Otherwise the movement is down
                                                                        MoveBlocks.down(gameBoard);
                                                                    } catch (IOException ex) {
                                                                        Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                                    flagUpOrDown = 2;
                                                                    deltaX = (deltaX * (-1));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (java.lang.NullPointerException e1) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        repaint();

        System.out.println("\n Score: " + getScore());
    }

    @Override
    public void mouseEntered(MouseEvent arg0
    ) {
    }

    // Thread to check for shock or sum between blocks, and update animations
    public class checkShockOrSum extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(400);
                } catch (InterruptedException erro) {
                }
                if (shockLine[0] != -1 || sumLine[0] != -1) {
                    for (int i = 0; i < 16; i++) {
                        if (i < 12) {
                            shockLine[i] = -1;
                            shockColumn[i] = -1;
                        }
                        sumLine[i] = -1;
                        sumColumn[i] = -1;
                    }

                    try {
                        sleep(300);
                    } catch (InterruptedException erro) {
                    }
                    repaint();

                    // Check after last move if user lost
                    flagLost = checkIfLost();

                    // Check after last move if user won
                    flagWon = checkIfWon();

                    // If in case of loss I give a time of 1 second, print lost and flag Up indicates no movement
                    if (flagLost == true) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException erro) {
                        }
                        System.out.println("\n Lost!");
                        flagUpOrDown = 0; // 0 means no change and user lost or won
                    } else { // If case won give 1 second time, print lost and flag Up indicates no movement
                        if (flagWon == true) {
                            try {
                                sleep(1000);
                            } catch (InterruptedException erro) {
                            }
                            System.out.println("Win!");
                            flagUpOrDown = 0; // 0 means no change and user lost or won
                        }
                    }
                }
            }
        }
    }

    // Thread to play music while user is playing
    class Music extends Thread {

        @Override
        public void run() {
            //                       
            try {
                while (true) {
                    // InputStream variable declaration, which already contains the path of the song that is stored in a package
                    InputStream input = this.getClass().getResourceAsStream("/music/Beethoven_Fur_Elise.mp3");

                    // Instantiating the music variable of type Player
                    music = new Player(input);

                    // ".Play to start playing the song
                    music.play();
                }
            } catch (JavaLayerException ex) {
                Logger.getLogger(GraphicAndControlScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // getters and setters methods
    public static int getScore() {
        return score;
    }

    public static void setScore(int aScore) {
        score = aScore;
    }

    public static int[][] getGameBoard() {
        return gameBoard;
    }

    public Integer getReleasedX() {
        return releasedX;
    }

    public void setReleasedX(Integer releasedX) {
        this.releasedX = releasedX;
    }

    public Integer getClickX() {
        return clickX;
    }

    public void setClickX(Integer clickX) {
        this.clickX = clickX;
    }

    public Integer getReleasedY() {
        return releasedY;
    }

    public void setReleasedY(Integer releasedY) {
        this.releasedY = releasedY;
    }

    public Integer getClickY() {
        return clickY;
    }

    public void setClickY(Integer clickY) {
        this.clickY = clickY;

    }

    public static void setGameBoard(int[][] aGameBoard) {
        gameBoard = aGameBoard;
    }
}
