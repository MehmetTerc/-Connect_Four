package PIS_HU1;


import processing.core.PApplet;

import java.util.Arrays;


public class Draw extends PApplet {
    GameEngine game = new GameEngine();
    public static void main(String[] args) {
        PApplet.runSketch(new String[]{""}, new Draw());
    }

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        background(3, 90, 252);
        game.reset();
        drawBoard();

    }

    public void draw() {
        drawBoard();
    }

    public void drawBoard(){
        for(int i=1;i<8;i++){
            textSize(30);
            fill(255);
            text(i,105*i,40);
        }
        line(0,60,800,60);
        int x = 106;
        int y = 120;
        for(int h = 5; h>= 0 ; h--){
            int w=h;
            int i = 1;
            while(w<49){
                long mask = 1L<<w;
                if((game.playerBoard[0]&mask)!= 0L){
                    fill(252, 215, 3);
                    ellipse(x*i, y,75,75);

                } else if((game.playerBoard[1]&mask)!= 0L){
                    fill(252, 44, 3);
                    ellipse(x*i, y,75,75);

                } else {
                    fill(255);
                    ellipse(x*i, y,75,75);
                }
                i++;
                w += 7;
            }
            y += 100;
        }
        if(game.isWin(game.playerBoard[0])){
            background(0);
            textSize(50);
            fill(255);
            text("Player 1 WON",400,400);
        } else if(game.isWin(game.playerBoard[1])){
            background(0);
            textSize(50);
            fill(255);
            text("Player 2 WON",400,400);
        }

    }

    @Override
    public void keyPressed() {
        if(key=='0'){
            System.out.println(game.toString());
        }
        if(key=='1'){
            game.makeMove(0);

        }
        if(key=='2'){
            game.makeMove(1);
        }
        if(key=='3'){
            game.makeMove(2);
        }
        if(key=='4'){
            game.makeMove(3);
        }
        if(key=='5'){
            game.makeMove(4);
        }
        if(key=='6'){
            game.makeMove(5);
        }
        if(key=='7'){
            game.makeMove(6);
        }
        if(key=='r'){
            background(3, 90, 252);
            game.reset();
            drawBoard();
        }
        if(key=='u'){
            game.undoMove();
        }
        if(key == 't'){
            GameEngine test = new GameEngine();
            test.playerBoard = Arrays.copyOf(game.playerBoard, game.playerBoard.length);
            test.move = Arrays.copyOf(game.move, game.move.length);
            test.heightCol = Arrays.copyOf(game.heightCol, game.heightCol.length);
            test.count = game.count;
            game.makeMove(test.chooseBestMove(test,100));
        }
        if(key == 't'){
            GameEngine test = new GameEngine();
            test.playerBoard = Arrays.copyOf(game.playerBoard, game.playerBoard.length);
            test.move = Arrays.copyOf(game.move, game.move.length);
            test.heightCol = Arrays.copyOf(game.heightCol, game.heightCol.length);
            test.count = game.count;
            game.makeMove(test.chooseBestMove(test,100));
        }
    }
}



