package Maze;

import sort.heap.HeapSortData;
import util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {
    private MazeData data;
    private AlgoFrame algoFrame;
    private int sceneWidth;
    private int sceneHeight;
    private static int blockSide = 8;
    private int DELAY = 20;

    private static final int[][] offset= {{-1,0},{0,1},{1,0},{0,-1}};

    public AlgoVisualizer(String mazeFile) {
        data = new MazeData(mazeFile);
        sceneHeight = data.N() * blockSide;
        sceneWidth = data.M() * blockSide;


        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame("Title", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        setData();
        go(data.getEntranceX(),data.getEntranceY());
        setData();

    }


    private boolean  go(int x,int y){
        if (!data.inArea(x,y))
            throw new IllegalArgumentException("x , y are out of index in maze");
        data.visited[x][y] = true;
        data.path[x][y] = true;
        setData();
        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < 4; i++) {
            int newX = x + offset[i][0];
            int newY = y + offset[i][1];
            if (data.inArea(newX,newY) && data.getMaze(newX,newY) == data.ROAD && !data.visited[newX][newY]){
                if (go(newX,newY))
                    return true;
            }
        }
        data.path[x][y] = false;
        setData();
        return false;

    }

    private void setData() {
        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }



}
