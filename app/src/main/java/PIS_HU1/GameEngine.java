package PIS_HU1;


public class GameEngine implements GameInterface{
    final private int height = 6;
    final private int width = 7;
    final private int heightBottom = height+1;
    final private int size1 = heightBottom*width;
    final private long all = (1L<<size1)-1L;
    final private int column = (1<<heightBottom)-1;
    final private long bottom = all/column;
    final private long top = bottom<<height;
    long[] playerBoard = new long[]{0L,0L};
    int[] heightCol = new int[7]; // Zählt wieviele Steine in dem Column sind
    int[] move = new int[43]; // Moves werden gezählt, wenn das Array voll ist, ist das Spiel vorbei
    int count = 0;

    //Montecarlo Bot

    @Override
    public boolean isWin(long board) {
        int[] directions = {1, 7, 6, 8};
        long bb;
        for(int direction : directions) {
            bb = board& (board >> direction);
            if ((bb & (bb >> (2 * direction))) != 0) return true;
        }
        return false;
    }

    @Override
    public void makeMove(int col) {
        if(!isPlayable(col)){
            System.out.println("Geht nicht");
            return;
        }
        long moving = 1L << heightCol[col]++;
        System.out.println(heightCol[col]);
        playerBoard[count & 1] ^= moving;
        move[count++] = col;
    }

    @Override
    public void undoMove() {
        int col = move[--count];
        long move = 1L << --heightCol[col];
        playerBoard[count & 1] ^= move;
    }

    public void reset(){
        count = 0;
        playerBoard[0]=0L;
        playerBoard[1]=0L;
        for(int i=0; i<width;i++){
            heightCol[i]=heightBottom*i;

        }
    }

    public boolean isLegal(long board){
        return ((board&top)==0L); // ist die oberste Stelle frei und ist der Zug gültig
    }

    public boolean isPlayable(int col){
        return isLegal(playerBoard[count&1]|(1L<<heightCol[col])); // Prüft, ob der move spielbar ist.
    }

    @Override
    public String toString() {
        String s = "";
        for(int i=0;i<width;i++){
            s+=" "+i+" ";
        }
        s+= "\n";
        for(int h=height-1;h>=0;h--){
            int w=h;
            while(w<size1){
                long mask = 1L<<w;
                if ((playerBoard[0]&mask)!=0L) s+=" X "; else if ((playerBoard[1]&mask)!=0L) s+=" 0 "; else s+=" . ";
                w+=heightBottom;
            }
            s+="\n";
        }
        return s;
    }


}








