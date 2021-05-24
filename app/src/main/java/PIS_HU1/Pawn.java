package PIS_HU1;
import processing.core.PApplet;

public class Pawn {
    PApplet a;
    int y, x;
    int playerTurn;

    public Pawn ( PApplet a,boolean black,int x, int y){
        if(black){
            playerTurn=1;
            a.fill(0,0,0);
            a.ellipse(this.y=y,this.x=x,60,60);
        }else{
            playerTurn=2;
            a.fill(255,255,255);
            a.ellipse(this.y=y,this.x=x,60,60);
        }
    }

    void move(int x, int y){
        this.y= y*(a.height/8)-(a.height/8/2);
        this.x= y*(a.width/8)-(a.width/8/2);
    }

    void setY(int y){
        this.y=y*(a.height/8)-(a.height/8/2);
    }

    void setX(int x){
        this.x= y*(a.width/8)-(a.width/8/2);
    }
}
