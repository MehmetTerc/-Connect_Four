package PIS_HU1;


import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.Arrays;

public class Draw extends PApplet {

    GameEngine game = new GameEngine();
    private int gameMode;
    boolean menu=true;
    boolean bot = false;
    boolean drawGame=false;
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Draw());
    }

    public void settings() {
        size(650, 680);

    }

    public void setup() {
        background(3, 90, 252);
        game.reset();
        mainMenu();
        surface.setTitle("4 Gewinnt!");
    }

    public void draw() {
        if(drawGame) {
            background(3, 90, 252);
            drawBoard();
        }
    }

    public void drawBoard() {
        for (int i = 1; i < 8; i++) {
            textSize(30);
            fill(255);
            text(i, (width/8) * i-10, 40);
        }
        line(0, 60, 800, 60);
        int x = width/8;
        int y = 120;
        for (int h = 5; h >= 0; h--) {
            int w = h;
            int i = 1;
            while (w < 49) {
                long mask = 1L << w;
                if ((game.playerBoard[0] & mask) != 0L) {
                    fill(252, 215, 3);
                    ellipse(x * i, y, 75, 75);

                } else if ((game.playerBoard[1] & mask) != 0L) {
                    fill(252, 44, 3);
                    ellipse(x * i, y, 75, 75);

                } else {
                    fill(255);
                    ellipse(x * i, y, 75, 75);
                }
                i++;
                w += 7;
            }
            y += 100;
        }
        if (game.isWin(game.playerBoard[0])) {
            background(0);
            textSize(50);
            fill(255);
            text("Player 1 WON", 400, 400);
        } else if (game.isWin(game.playerBoard[1])) {
            background(0);
            textSize(50);
            fill(255);
            text("Player 2 WON", 400, 400);
        }
    }

    public void mainMenu(){
        textSize(30);
        fill(255);
        text("4 WINS",200,200);
        text("Press P to play against a Player",200,230);
        text("Press B to play against a Bot",200,260);
    }

    @Override
    public void keyPressed() {
        if (key == '0') {
            System.out.println(game.toString());
        }
        if (key == '1') {
            game.makeMove(0);
            if(bot) bot();
        }
        if (key == '2') {
            game.makeMove(1);
            if(bot) bot();
        }
        if (key == '3') {
            game.makeMove(2);
            if(bot) bot();
        }
        if (key == '4') {
            game.makeMove(3);
            if(bot) bot();
        }
        if (key == '5') {
            game.makeMove(4);
            if(bot) bot();
        }
        if (key == '6') {
            game.makeMove(5);
            if(bot) bot();
        }
        if (key == '7') {
            game.makeMove(6);
            if(bot) bot();
        }
        if (key == 'r') {
            background(3, 90, 252);
            game.reset();
            mainMenu();
            bot=false;
            drawGame=false;
            mainMenu();
            if(drawGame) drawBoard();
        }
        if (key == 'u') {
            game.undoMove();
        }
        if(key=='p'){
            drawGame=true;
        }
        if(key=='b'){
            bot=true;
            drawGame=true;
        }
    }

    public void bot(){
        GameEngine test = new GameEngine();
        test.playerBoard = Arrays.copyOf(game.playerBoard, game.playerBoard.length);
        test.move = Arrays.copyOf(game.move, game.move.length);
        test.heightCol = Arrays.copyOf(game.heightCol, game.heightCol.length);
        test.count = game.count;
        game.makeMove(test.chooseBestMove(test, 100));
    }
}




