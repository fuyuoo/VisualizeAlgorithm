package Maze;

import java.awt.*;

public class Position {
    private int x,y;
    private Position pre;

    public Position(int x, int y,Position pre) {
        this.x = x;
        this.y = y;
        this.pre = pre;
    }
    public Position(int x, int y) {
        this(x,y,null);
    }

    public Position getPre(){
        return this.pre;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
