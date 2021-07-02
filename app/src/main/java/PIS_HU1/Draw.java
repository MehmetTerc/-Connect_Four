package PIS_HU1;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Arrays;

public class Draw extends PApplet {
  GameEngine game = new GameEngine();
  boolean bot = false;
  boolean drawGame = false;

  public static void main(String[] args) {
    PApplet.runSketch(new String[] {""}, new Draw());
  }

  public void settings() {
    size(650, 680);
  }

  public void setup() {
    PImage icon = loadImage("app/src/main/resources/connect-four.png");
    game.reset();
    mainMenu();
    surface.setTitle("Connect Four !");
    surface.setIcon(icon);
  }

  public void draw() {
    if (drawGame) {
      background(50, 96, 114);
      drawBoard();
    }
  }

  public void drawBoard() {
    for (int i = 1; i < 8; i++) {
      textSize(30);
      fill(255);
      text(i, (width / 8) * i - 10, 40);
    }
    line(0, 60, 800, 60);
    int x = width / 8;
    int y = 120;
    for (int h = 5; h >= 0; h--) {
      int w = h;
      int i = 1;
      while (w < 49) {
        long mask = 1L << w;
        if ((game.getPlayerBoard()[0] & mask) != 0L) {
          fill(252, 215, 3);
          ellipse(x * i, y, 75, 75);

        } else if ((game.getPlayerBoard()[1] & mask) != 0L) {
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
    PImage winner = loadImage("app/src/main/resources/winner.png");
    winner.resize(350, 350);
    if (game.isWin(game.getPlayerBoard()[0])) {
      background(50, 96, 114);
      textSize(60);
      fill(255);
      text("Player 1 WON", 100, 100);
      textSize(30);
      text("Press R for restart", 100, 230);
      image(winner, 150, 250);
    } else if (game.isWin(game.getPlayerBoard()[1])) {
      background(50, 96, 114);
      textSize(60);
      fill(255);
      if (bot) {
        PImage botPic = loadImage("app/src/main/resources/robot.png");
        text("Bot WON", 100, 100);
        botPic.resize(350, 350);
        image(botPic, 150, 250);
      } else {
        text("Player 2 WON", 100, 100);
        image(winner, 150, 250);
      }
      textSize(30);
      text("Press R for restart", 100, 200);
    }
  }

  public void mainMenu() {
    PImage start = loadImage("app/src/main/resources/TitleScreen.png");
    background(50, 96, 114);
    textSize(60);
    fill(255);
    text("Connect Four", 100, 100);
    textSize(30);
    text("Press P to play against a Player", 100, 230);
    text("Press B to play against a Bot", 100, 280);
    start.resize(275, 275);
    image(start, 175, 350);
  }

  @Override
  public void keyPressed() {
    switch (key) {
      case '1', '2', '3', '4', '5', '6', '7' -> {
        game.makeMove(Integer.parseInt(String.valueOf(key))-1);
        if (bot) bot();
      }
    }
    if (key == 'r') {
      background(3, 90, 252);
      game.reset();
      mainMenu();
      bot = false;
      drawGame = false;
      mainMenu();
      if (drawGame) drawBoard();
    }
    if (key == 'u') {
      game.undoMove();
    }
    if (key == 'p') {
      drawGame = true;
    }
    if (key == 'b') {
      bot = true;
      drawGame = true;
    }
  }

  public void bot() {
    GameEngine test = new GameEngine();
    test.setGetPlayerBoard(Arrays.copyOf(game.getPlayerBoard(), game.getPlayerBoard().length));
    test.setMove(Arrays.copyOf(game.getMove(), game.getMove().length));
    test.setHeightCol(Arrays.copyOf(game.getHeightCol(), game.getHeightCol().length));
    test.setCount(game.getCount());
    game.makeMove(test.chooseBestMove(test, 100));
  }
}
