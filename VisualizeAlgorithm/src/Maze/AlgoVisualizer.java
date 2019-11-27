package Maze;

import sort.heap.HeapSortData;
import util.AlgoVisuHelper;

import java.awt.*;
import java.util.Stack;

public class AlgoVisualizer {
    private MazeData data;
    private AlgoFrame algoFrame;
    private int sceneWidth;
    private int sceneHeight;
    private static int blockSide = 8;
    private int DELAY = 10;

    private static final int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

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

    //非递归
    private boolean run() {
        setData();
        Stack<Position> pos = new Stack<>();
        pos.push(new Position(data.getEntranceX(), data.getEntranceY()));
        data.visited[data.getEntranceX()][data.getEntranceY()] = true;
        while (!pos.empty()) {
            Position tmp = pos.pop();
            data.path[tmp.getX()][tmp.getY()] = true;
            setData();
            if (tmp.getX() == data.getExitX() && tmp.getY() == data.getExitY())
                 break;
            for (int i = 0; i < 4; i++) {
                int newX = tmp.getX() + offset[i][0];
                int newY = tmp.getY() + offset[i][1];

                if (data.inArea(newX,newY) && data.getMaze(newX,newY) == data.ROAD && !data.visited[newX][newY])
                {
                    pos.push(new Position(newX,newY));
                    data.visited[newX][newY] = true;

                }
            }
        }
        setData();
        System.out.println("the maze has no solution");
        return false;
    }
    // 递归
    /*private void run() {

        setData();
        if (!go(data.getEntranceX(),data.getEntranceY()))
        {
            System.out.println("the maze has no solution");
            return;
        }
        setData();

    }


    private boolean  go(int x,int y){
        if (!data.inArea(x,y))
            throw new IllegalArgumentException("x or y are out of index in maze");
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

    }*/

    private void setData() {
        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }


}
