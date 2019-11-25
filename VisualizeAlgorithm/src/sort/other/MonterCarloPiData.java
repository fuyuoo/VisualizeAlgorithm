package sort.other;
import sort.util.Circle;

import java.awt.*;
import java.util.LinkedList;
public class MonterCarloPiData {
    public LinkedList<Point> points;
    public Circle circle;
    int inside;

    public MonterCarloPiData(Circle circle){
        this.points = new LinkedList<Point>();
        this.circle = circle;
        inside = 0;
    }
    public void add(Point p){
        this.points.add(p);
        if (circle.contain(p)){
            inside++;
        }
    }
    public void printPI(){
        System.out.println(4 * (double)inside/points.size());
    }
}
