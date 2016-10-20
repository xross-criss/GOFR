package engine;

public class Point {
    public int x;
    public int y;

    Point(int xcoord, int ycoord){
        this.x = xcoord;
        this.y = ycoord;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
}
