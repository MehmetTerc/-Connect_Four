package PIS_HU1;

public interface GameInterface {

    boolean isWin(long board);
    void makeMove(int col);
    void undoMove();
    String toString();


}
