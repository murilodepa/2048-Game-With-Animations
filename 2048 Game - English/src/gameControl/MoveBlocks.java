/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameControl;

import static gameControl.WriteFile.getRecord;
import static gameControl.WriteFile.setRecord;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Murilo Araujo
 */
public class MoveBlocks extends GraphicAndControlScreen {

    /**
     * @param args the command line arguments
     */
    // Declaration of all variables used in the MoveBlocks class
    private static final Random random = new Random();
    public static boolean flagMove = false;
    public static int[][] returnMatrix = new int[4][4];
    public static int[] shockLine = new int[12];
    static int[] shockColumn = new int[12];
    static int[] sumLine = new int[16];
    static int[] sumColumn = new int[16];
    int cont1 = 0, cont2 = 0, line0a3 = 0, column0a3 = 0;

    // Generate random numbers, 20% probability of generating number 4
    public static void generateRandom() {
        int visibleBlock = 0, line0a3 = 0, column0a3 = 0, value0a4 = 0;

        do {
            visibleBlock = 0;
            line0a3 = random.nextInt(4);
            column0a3 = random.nextInt(4);
            value0a4 = random.nextInt(5);

            if (getGameBoard()[line0a3][column0a3] == 0 && value0a4 == 2 && flagStart != true) {
                getGameBoard()[line0a3][column0a3] = 4;
            } else {
                if (getGameBoard()[line0a3][column0a3] == 0 && value0a4 != 2) {
                    getGameBoard()[line0a3][column0a3] = 2;
                } else {
                    visibleBlock = 1;
                }
            }
        } while (visibleBlock == 1);
    }

    // Generate in a random block the value passed as a parameter
    public static void generateRandom(int n) {
        int line0a3 = 0, column0a3 = 0;
        line0a3 = random.nextInt(4);
        column0a3 = random.nextInt(4);
        gameBoard[line0a3][column0a3] = n;
    }

    // Generate at matrix position 0x0 and 0x1 the two values passed as parameters
    public static void generateRandom(int n, int m) {
        int line0a3 = 0, column0a3 = 0;
        gameBoard[line0a3][column0a3] = n;
        gameBoard[line0a3][column0a3 + 1] = m;
    }

    // Clear an matrix passed by parameter
    public static void clearMatrix(int[][] matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    // print the game matrix (Main)
    public static void printMatrix() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%6d", getGameBoard()[i][j]);
            }
            System.out.println("");
        }
    }

    // Checking Easter Egg trigger
    public static void actuationEasterEgg() {
        clearMatrix(gameBoard);
        generateRandom(1024, 1024);
        for (int k = 0; k < 16; k++) {
            sumLine[k] = -1;
            sumColumn[k] = -1;
            if (k < 12) {
                shockLine[k] = -1;
                shockLine[k] = -1;
            }
        }

        // I copy the main matrix on returnMatrix
        copyMatrixAndSetVariables();

        // Zero score and flagStart gets false
        score = 0;
        flagStart = false;
    }

    // I copy the main matrix of the game into a helper if the user decides to return the play
    // Start variables as -1 so as not to conflict with position 0
    public static void copyMatrixAndSetVariables() {
        int i = 0, j = 0, k = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                returnMatrix[i][j] = gameBoard[i][j];
                if (k < 12) {
                    shockLine[k] = -1;
                    shockColumn[k] = -1;
                }
                if (k < 16) {
                    sumLine[k] = -1;
                    sumColumn[k] = -1;
                }
                k += 1;
            }
        }
    }

    // I move blocks to the left, checking for joints, empty squares, and shocks between blocks
    public static void left(int[][] matrix) throws IOException {
        int flagSum = 0, aux = 0, cont = 1, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMove = false;

        copyMatrixAndSetVariables();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux > flagSum) {
                    // 
                    if (matrix[i][aux - 1] == 0 && matrix[i][aux] != 0) {
                        matrix[i][aux - 1] = matrix[i][aux];
                        matrix[i][aux] = 0;
                        flagMove = true;
                    }
                    if (matrix[i][aux - 1] == matrix[i][aux] && matrix[i][aux] != 0) {
                        matrix[i][aux - 1] += matrix[i][aux];
                        sumLine[l] = i;
                        sumColumn[l] = (aux - 1);
                        l += 1;
                        setScore(getScore() + matrix[i][aux - 1]);

                        if (getScore() > getRecord()) {
                            setRecord(getScore());
                            new WriteFile();
                        }

                        matrix[i][aux] = 0;
                        flagSum += 1;
                        flagMove = true;
                    } else {
                        if (matrix[i][aux - 1] != 0 && matrix[i][aux] != 0) {
                            shockLine[cont1] = i;
                            shockColumn[cont2] = (aux - 1);
                            cont1 += 1;
                            cont2 += 1;
                            flagSum += 1;
                        }
                    }
                    aux -= 1;
                }
                cont += 1;
            }
            cont = 1;
            flagSum = 0;
        }

        // If movement has occurred and has not triggered Easter Egg, the program generates another random number after movement
        if (flagMove == true && flagEasterEgg != true) {
            generateRandom();
        }
        flagCanBeReturned = true;
    }

    // I move blocks to the right, checking for joints, empty squares, and collision shocks
    public static void right(int[][] matrix) throws IOException {
        int flagSum = 3, aux = 0, cont = 2, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMove = false;

        copyMatrixAndSetVariables();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux < flagSum) {

                    if (matrix[i][aux + 1] == 0 && matrix[i][aux] != 0) {
                        matrix[i][aux + 1] = matrix[i][aux];
                        matrix[i][aux] = 0;
                        flagMove = true;
                    }
                    if (matrix[i][aux + 1] == matrix[i][aux] && matrix[i][aux] != 0) {
                        matrix[i][aux + 1] += matrix[i][aux];
                        sumLine[l] = i;
                        sumColumn[l] = (aux + 1);
                        l += 1;
                        score += matrix[i][aux + 1];

                        if (getScore() > getRecord()) {
                            setRecord(getScore());
                            new WriteFile();
                        }

                        matrix[i][aux] = 0;
                        flagSum -= 1;
                        flagMove = true;
                    } else {
                        if (matrix[i][aux + 1] != 0 && matrix[i][aux] != 0) {
                            shockLine[cont1] = i;
                            shockColumn[cont2] = (aux);
                            cont1 += 1;
                            cont2 += 1;
                            flagSum -= 1;
                        }
                    }
                    aux += 1;
                }
                cont -= 1;
            }
            cont = 2;
            flagSum = 3;
        }

        // If a move occurs and does not activate an Easter Egg, the program generates another random number after a move
        if (flagMove == true && flagEasterEgg != true) {
            generateRandom();
        }
        flagCanBeReturned = true;
    }

    // I move blocks up, checking junctions, empty squares, and shocks between blocks
    public static void up(int[][] matrix) throws IOException {
        int flagSum = 0, aux = 0, cont = 1, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMove = false;

        copyMatrixAndSetVariables();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux > flagSum) {
                    if (matrix[aux - 1][i] == 0 && matrix[aux][i] != 0) {
                        matrix[aux - 1][i] = matrix[aux][i];
                        matrix[aux][i] = 0;
                        flagMove = true;
                    }
                    if (matrix[aux - 1][i] == matrix[aux][i] && matrix[aux][i] != 0) {
                        matrix[aux - 1][i] += matrix[aux][i];
                        sumLine[l] = (aux - 1);
                        sumColumn[l] = i;
                        l += 1;
                        score += matrix[aux - 1][i];

                        if (getScore() > getRecord()) {
                            setRecord(getScore());
                            new WriteFile();
                        }

                        matrix[aux][i] = 0;
                        flagSum += 1;
                        flagMove = true;
                    } else {
                        if (matrix[aux - 1][i] != 0 && matrix[aux][i] != 0) {
                            shockLine[cont1] = i;
                            shockColumn[cont2] = (aux - 1);
                            cont1 += 1;
                            cont2 += 1;
                            flagSum += 1;
                        }
                    }
                    aux -= 1;
                }
                cont += 1;
            }
            cont = 1;
            flagSum = 0;
        }

        // If movement has occurred and has not triggered Easter Egg, the program generates another random number after movement
        if (flagMove == true && flagEasterEgg != true) {
            generateRandom();
        }
        flagCanBeReturned = true;
    }

    // I move blocks down, checking for joints, empty squares, and shocks between blocks
    public static void down(int[][] matrix) throws IOException {
        int flagSum = 3, aux = 0, cont = 2, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMove = false;

        copyMatrixAndSetVariables();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux < flagSum) {

                    if (matrix[aux + 1][i] == 0 && matrix[aux][i] != 0) {
                        matrix[aux + 1][i] = matrix[aux][i];
                        matrix[aux][i] = 0;
                        flagMove = true;
                    }
                    if (matrix[aux + 1][i] == matrix[aux][i] && matrix[aux][i] != 0) {
                        matrix[aux + 1][i] += matrix[aux][i];
                        sumLine[l] = (aux + 1);
                        sumColumn[l] = i;
                        l += 1;
                        score += matrix[aux + 1][i];

                        if (getScore() > getRecord()) {
                            setRecord(getScore());
                            new WriteFile();
                        }

                        matrix[aux][i] = 0;
                        flagSum -= 1;
                        flagMove = true;
                    } else {
                        if (matrix[aux + 1][i] != 0 && matrix[aux][i] != 0) {
                            shockLine[cont1] = i;
                            shockColumn[cont2] = aux;
                            cont1 += 1;
                            cont2 += 1;
                            flagSum -= 1;
                        }
                    }
                    aux += 1;
                }
                cont -= 1;
            }
            cont = 2;
            flagSum = 3;
        }

        // If movement has occurred and has not triggered Easter Egg, the program generates another random number after movement
        if (flagMove == true && flagEasterEgg != true) {
            generateRandom();
        }
        flagCanBeReturned = true;
    }

    // Function that checks if the user has lost by traversing the matrix and checking for matching blocks in neighboring positions
    public static boolean checkIfLost() {
        boolean flagPerder = false;
        int aux = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == gameBoard[i][j + 1] || gameBoard[i][j] == 0 || gameBoard[i][j + 1] == 0) {
                    return flagPerder;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[j][i] == gameBoard[j + 1][i]) {
                    return flagPerder;
                }
            }
        }
        flagPerder = true;
        return flagPerder;
    }

    // Check if the user has won, ie has a block with the value 2048
    public static boolean checkIfWon() {
        boolean flagGanhou = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] == 2048) {
                    flagGanhou = true;
                    return flagGanhou;
                }
            }
        }
        return flagGanhou;
    }
}
