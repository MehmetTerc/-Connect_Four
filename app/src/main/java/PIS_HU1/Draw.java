package PIS_HU1;

import processing.core.PApplet;

public class Draw extends PApplet {

    boolean white = true;
    boolean black = false;
    int width = 600;
    int height = 600;
    int xVar = width / 8;
    int yVar = height / 8;
    Pawn[][] gameBoard = new Pawn[8][8];


    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Draw());
    }

    public void settings() {
        size(width, height);
    }

    public void setup() {
        noStroke();
        drawBoard();
        startBoard();

    }

    public void draw() {
        noStroke();


    }

    public void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j + 1) % 2 != 0) {
                    fill(209, 139, 71);
                } else {
                    fill(255, 206, 158);
                }
                rect(i * xVar, j * yVar, (i + 1) * xVar, (j + 1) * yVar);
            }
        }
    }

    public void startBoard() {
        gameBoard[0][1] = new Pawn(this, false, (height / 8) - (xVar / 2), 2 *(height / 8) - (yVar / 2));
        gameBoard[0][3] = new Pawn(this, false, (height / 8) - (xVar / 2), 4 * (height / 8) - (yVar / 2));
        gameBoard[0][5] = new Pawn(this, false, (height / 8) - (xVar / 2), 6 * (height / 8) - (yVar / 2));
        gameBoard[0][7] = new Pawn(this, false, (height / 8) - (xVar / 2), 8 * (height / 8) - (yVar / 2));
        gameBoard[1][0] = new Pawn(this, false, 2 * (height / 8) - (xVar / 2),  (height / 8) - (yVar / 2));
        gameBoard[1][2] = new Pawn(this, false, 2 * (height / 8) - (xVar / 2), 3 * (height / 8) - (yVar / 2));
        gameBoard[1][4] = new Pawn(this, false, 2 * (height / 8) - (xVar / 2), 5* (height / 8) - (yVar / 2));
        gameBoard[1][6] = new Pawn(this, false, 2 * (height / 8) - (xVar / 2), 7 * (height / 8) - (yVar / 2));
        gameBoard[2][1] = new Pawn(this, false, 3 * (height / 8) - (xVar / 2), 2*(height / 8) - (yVar / 2));
        gameBoard[2][3] = new Pawn(this, false, 3 * (height / 8) - (xVar / 2), 4* (height / 8) - (yVar / 2));
        gameBoard[2][5] = new Pawn(this, false, 3 * (height / 8) - (xVar / 2), 6* (height / 8) - (yVar / 2));
        gameBoard[2][7] = new Pawn(this, false, 3 * (height / 8) - (xVar / 2), 8 * (height / 8) - (yVar / 2));
        gameBoard[5][0] = new Pawn(this, true, 6 * (height / 8) - (xVar / 2), (height / 8) - (yVar / 2));
        gameBoard[5][2] = new Pawn(this, true, 6 * (height / 8) - (xVar / 2), 3 * (height / 8) - (yVar / 2));
        gameBoard[5][4] = new Pawn(this, true, 6 * (height / 8) - (xVar / 2), 5 * (height / 8) - (yVar / 2));
        gameBoard[5][6] = new Pawn(this, true, 6 * (height / 8) - (xVar / 2), 7 * (height / 8) - (yVar / 2));
        gameBoard[6][1] = new Pawn(this, true, 7 * (height / 8) - (xVar / 2), 2 * (height / 8) - (yVar / 2));
        gameBoard[6][3] = new Pawn(this, true, 7 * (height / 8) - (xVar / 2), 4 * (height / 8) - (yVar / 2));
        gameBoard[6][5] = new Pawn(this, true, 7 * (height / 8) - (xVar / 2), 6 * (height / 8) - (yVar / 2));
        gameBoard[6][7] = new Pawn(this, true, 7 * (height / 8) - (xVar / 2), 8 * (height / 8) - (yVar / 2));
        gameBoard[7][0] = new Pawn(this, true, 8 * (height / 8) - (xVar / 2), (height / 8) - (yVar / 2));
        gameBoard[7][2] = new Pawn(this, true, 8 * (height / 8) - (xVar / 2), 3 * (height / 8) - (yVar / 2));
        gameBoard[7][4] = new Pawn(this, true, 8 * (height / 8) - (xVar / 2), 5 * (height / 8) - (yVar / 2));
        gameBoard[7][6] = new Pawn(this, true, 8 * (height / 8) - (xVar / 2), 7 * (height / 8) - (yVar / 2));
    }


}

