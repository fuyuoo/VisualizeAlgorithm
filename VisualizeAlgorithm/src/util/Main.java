package util;


import sort.heap.*;

public class Main {

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;
        int R = 50;
        Circle circle = new Circle(sceneHeight/2,sceneWidth/2,sceneHeight/2,0,0);
        HeapSortData data = new HeapSortData(100,sceneHeight,true);
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(data);
    }
}
