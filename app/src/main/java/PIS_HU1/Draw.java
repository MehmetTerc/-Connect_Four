package PIS_HU1;

import processing.core.PApplet;


import java.util.ArrayList;


public class Draw extends PApplet {

    boolean white = true;
    boolean black = false;
    int width = 800;
    int height = 800;
    int xVar = width / 8; // Hilfsvariable zum erstellen des Feldes
    int yVar = height / 8; // Hilfsvariable zum erstellen des Feldes
    ArrayList<int[]> tmp = new ArrayList<>();
    GameEngine ge = new GameEngine();


    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Draw());
    }

    public void settings() {
        size(width, height);
    }

    public void setup() {
        noStroke();
        drawBoard();
        System.out.println(ge.toString());


    }

    public void draw() {
        noStroke();
        printPawn();

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

    /*

    public void showBoard() {
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

     */


    public void mousePressed() {

        int y = mouseY / 100;
        int x = mouseX / 100;
        if (!tmp.isEmpty()) {
            int x1 = tmp.get(tmp.size() - 1)[0];
            int y1 = tmp.get(tmp.size() - 1)[1];
            System.out.println(y1 + " " + x1);
            ge.gameBoard[y1][x1] = 0;
            System.out.println("test");
            fill(255, 206, 158);
            ellipse(x1* 100 + 50, y1 * 100 + 50, 80, 80);
            ge.move(y, x, 1);
            tmp.clear();
            System.out.println(ge.toString());
        } else {
            System.out.println(y + "   " + x);
            if (ge.gameBoard[y][x] != 0) {
                int[] xy = {x, y};
                tmp.add(xy);

            }
        }
    }

    Pawn[][] move(Pawn[][] Board, int mouseY, int mouseX) {
        Board[mouseY][mouseX] = new Pawn(this, true, mouseX * (height / 8) - (xVar / 2), mouseY * (height / 8) - (xVar / 2));
        return Board;

    }

    public void printPawn() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (ge.gameBoard[i][j] == 1) {
                    fill(0, 0, 0);
                    ellipse(j * 100 + 50, i * 100 + 50, 60, 60);

                } else if (ge.gameBoard[i][j] == 2) {
                    fill(255, 255, 255);
                    ellipse(j * 100 + 50, i * 100 + 50, 60, 60);
                }
            }
        }
    }


}

