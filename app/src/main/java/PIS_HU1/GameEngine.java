package PIS_HU1;




public class GameEngine {
    int[][]gameBoard=new int[8][8];


    public GameEngine(){
        gameBoard[0][1] = 1;
        gameBoard[0][3] = 1;
        gameBoard[0][5] = 1;
        gameBoard[0][7] = 1;
        gameBoard[1][0] = 1;
        gameBoard[1][2] = 1;
        gameBoard[1][4] = 1;
        gameBoard[1][6] = 1;
        gameBoard[2][1] = 1;
        gameBoard[2][3] = 1;
        gameBoard[2][5] = 1;
        gameBoard[2][7] = 1;
        gameBoard[5][0] = 2;
        gameBoard[5][2] = 2;
        gameBoard[5][4] = 2;
        gameBoard[5][6] = 2;
        gameBoard[6][1] = 2;
        gameBoard[6][3] = 2;
        gameBoard[6][5] = 2;
        gameBoard[6][7] = 2;
        gameBoard[7][0] = 2;
        gameBoard[7][2] = 2;
        gameBoard[7][4] = 2;
        gameBoard[7][6] = 2;

    }

    public String toString(){
        String s=" ";
        for (int i=0;i<8;i++){
            for(int j=0; j<8;j++){
                s+=""+ gameBoard[i][j];
            }
            s+=" \n";
        }
        return s;
    }

    public void move(int y, int x, int playerTurn){
        gameBoard[y][x]=playerTurn;
    }







}
