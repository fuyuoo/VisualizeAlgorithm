package util;


import sort.QuickSort.QuickSortData;
import sort.selection.*;
import sort.insertion.InsertionSortData;
import sort.merge.MergeSortData;
import sort.selection.SelectionSortData;

public class Main {

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;
        int R = 50;
        Circle circle = new Circle(sceneHeight/2,sceneWidth/2,sceneHeight/2,0,0);
        SelectionSortData data = new SelectionSortData(100,sceneHeight);
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(data);
    }
}
