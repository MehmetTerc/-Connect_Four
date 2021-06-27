package PIS_HU1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameEngine implements GameInterface {
  private final int height = 6;
  private final int width = 7;
  private final int heightBottom = height + 1;
  private final int size1 = heightBottom * width;
  private final long all = (1L << size1) - 1L;
  private final int column = (1 << heightBottom) - 1;
  private final long bottom = all / column;
  private final long top = bottom << height;
  long[] playerBoard = new long[] {0L, 0L};
  int[] heightCol = new int[7]; // Zählt wieviele Steine in dem Column sind
  int[] move = new int[43]; // Moves werden gezählt, wenn das Array voll ist, ist das Spiel vorbei
  int count = 0;

  @Override
  public boolean isWin(long board) {
    int[] directions = {1, 7, 6, 8};
    long bb;
    for (int direction : directions) {
      bb = board & (board >> direction);
      if ((bb & (bb >> (2 * direction))) != 0) return true;
    }
    return false;
  }

  @Override
  public void makeMove(int col) {
    if (!isPlayable(col)) {
      System.out.println("Not Possible");
      return;
    }
    long moving = 1L << heightCol[col]++;
    playerBoard[count & 1] ^= moving;
    move[count++] = col;
    // System.out.println(Long.toBinaryString(playerBoard[0])+"
    // "+Long.toBinaryString(playerBoard[0]));
  }

  @Override
  public void undoMove() {
    int col = move[--count];
    long move = 1L << --heightCol[col];
    playerBoard[count & 1] ^= move;
  }

  public void reset() {
    count = 0;
    playerBoard[0] = 0L;
    playerBoard[1] = 0L;
    for (int i = 0; i < width; i++) {
      heightCol[i] = heightBottom * i;
    }
    // System.out.println(Long.toBinaryString(playerBoard[0])+"
    // "+Long.toBinaryString(playerBoard[0]));
  }

  public boolean isLegal(long board) {
    return ((board & top) == 0L); // ist die oberste Stelle frei und ist der Zug gültig
  }

  public boolean isPlayable(int col) {
    return isLegal(
        playerBoard[count & 1] | (1L << heightCol[col])); // Prüft, ob der move spielbar ist.
  }

  // Montecarlo Bot

  public ArrayList<Integer> listMoves() {
    ArrayList<Integer> moves = new ArrayList<>();
    long TOP = 0b1000000_1000000_1000000_1000000_1000000_1000000_1000000L;
    for (int col = 0; col <= 6; col++) {
      if ((TOP & (1L << heightCol[col])) == 0) moves.add(col);
    }
    return moves;
  }

  public int playRandomly(GameEngine board) {
    int value = board.isWin(board.playerBoard[0]) ? 1 : board.isWin(board.playerBoard[1]) ? -1 : 0;
    Random rnd = new Random();
    while (value == 0) {
      ArrayList<Integer> moves = board.listMoves();
      if (moves.isEmpty()) return 0;
      int randomMove = moves.get(rnd.nextInt(moves.size()));
      board.makeMove(randomMove);
      value = board.isWin(board.playerBoard[0]) ? 1 : board.isWin(board.playerBoard[1]) ? -1 : 0;
    }
    return value;
  }

  public int[] simulatePlays(GameEngine board, int number) {
    int[] counter = {0, 0, 0};
    int count = board.count;
    while (number > 0) {
      GameEngine test = new GameEngine();
      test.playerBoard = Arrays.copyOf(board.playerBoard, board.playerBoard.length); // NUR CLONE
      test.move = Arrays.copyOf(board.move, board.move.length);
      test.heightCol = Arrays.copyOf(board.heightCol, board.heightCol.length);
      test.count = count;
      counter[playRandomly(test) + 1] += 1;
      number--;
    }
    return counter;
  }

  public ArrayList<int[]> evaluateMoves(GameEngine board, int number) {
    ArrayList<Integer> moves = board.listMoves();
    ArrayList<int[]> values = new ArrayList<>();
    for (int move : moves) {
      board.makeMove(move);
      values.add(simulatePlays(board, number));
      board.undoMove();
    }
    return values;
  }

  public int chooseBestMove(GameEngine board, int number) {
    ArrayList<Integer> moves = board.listMoves();
    ArrayList<int[]> evaluate = board.evaluateMoves(board, number);
    int[] values = new int[evaluate.size()];
    for (int i = 0; i < evaluate.size(); i++) {
      int turn = (board.count & 1) == 0 ? 1 : -1;
      values[i] = evaluate.get(i)[2] * turn;
    }
    int maxValue = Arrays.stream(values).max().getAsInt();
    int bestIndex = -1;
    for (int j = 0; j < values.length; j++) {
      if (values[j] == maxValue) {
        bestIndex = j;
      }
    }
    return moves.get(bestIndex);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < width; i++) {
      s.append(" ").append(i).append(" ");
    }
    s.append("\n");
    for (int h = height - 1; h >= 0; h--) {
      int w = h;
      while (w < size1) {
        long mask = 1L << w;
        if ((playerBoard[0] & mask) != 0L) s.append(" X ");
        else if ((playerBoard[1] & mask) != 0L) s.append(" 0 ");
        else s.append(" . ");
        w += heightBottom;
      }
      s.append("\n");
    }
    return s.toString();
  }
}
