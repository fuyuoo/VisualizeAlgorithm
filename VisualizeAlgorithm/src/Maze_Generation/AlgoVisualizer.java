package Maze_Generation;


import Maze.Position;
import util.AlgoVisuHelper;

import java.awt.*;
import java.beans.Visibility;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer {
    private MazeData data;
    private AlgoFrame algoFrame;
    private int sceneWidth;
    private int sceneHeight;
    private static int blockSide = 8;
    private int DELAY = 2;
    private boolean visited[][];

    private static final int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(int N, int M) {
        data = new MazeData(N, M);
        sceneHeight = data.N() * blockSide;
        sceneWidth = data.M() * blockSide;
        visited = new boolean[N][M];

        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame("Title", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData();
        visited[data.getEntranceX()][data.getEntranceY() + 1] = true;
        Position pos = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        data.openMist(data.getEntranceX(), data.getEntranceY() + 1);
        go(pos);

        setData();


    }
    // dfs递归
    /*private void go(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int newX = x + offset[i][0] * 2;
            int newY = y + offset[i][1] * 2;
            if (data.inArea(newX, newY) && !visited[newX][newY]) {
                visited[newX][newY] = true;
                data.maze[x + offset[i][0]][y + offset[i][1]] =  MazeData.ROAD;
                setData();
                go(newX, newY);
            }
        }
    }*/

    // dfs 非递归
    /*private void go(Position pos){
        Stack<Position> stack = new Stack<>();
        stack.push(pos);
        while (!stack.empty()){
            Position tmp = stack.pop();
            for (int i = 0; i < 4; i++) {
                int newX = tmp.getX()+ offset[3-i][0] * 2;
                int newY = tmp.getY() + offset[3-i][1] * 2;
                if (data.inArea(newX, newY) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    data.maze[tmp.getX() + offset[i][0]][tmp.getY() + offset[i][1]] =  MazeData.ROAD;
                    setData();
                    stack.push(new Position(newX,newY));
                }
            }
        }
    }*/

    // bfs
    /*private void go(Position pos){
        LinkedList<Position> stack = new LinkedList<>();
        stack.push(pos);
        while (!stack.isEmpty()){
            Position tmp = stack.pop();
            for (int i = 0; i < 4; i++) {
                int newX = tmp.getX()+ offset[i][0] * 2;
                int newY = tmp.getY() + offset[i][1] * 2;
                if (data.inArea(newX, newY) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    data.maze[tmp.getX() + offset[i][0]][tmp.getY() + offset[i][1]] =  MazeData.ROAD;
                    setData();
                    stack.addLast(new Position(newX,newY));
                }
            }
        }
    }*/
    // RandomList
    private void go(Position pos) {
        RandomList2<Position> stack = new RandomList2<>();
        stack.add(pos);
        while (stack.size() != 0) {
            Position tmp = stack.remote();
            for (int i = 0; i < 4; i++) {
                int newX = tmp.getX() + offset[i][0] * 2;
                int newY = tmp.getY() + offset[i][1] * 2;
                if (data.inArea(newX, newY) && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    data.maze[tmp.getX() + offset[i][0]][tmp.getY() + offset[i][1]] = MazeData.ROAD;
                    data.openMist(newX, newY);
                    setData();
                    stack.add(new Position(newX, newY));
                }
            }
        }
        FinshMaze();
    }

    // dfs递归
    private void FinshMaze() {
        setData();
        if (!goMaze(data.getEntranceX(), data.getEntranceY())) {
            System.out.println("the maze has no solution");
            return;
        }
        setData();
    }

    private boolean goMaze(int x, int y) {
        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x or y are out of index in maze");
        data.visited[x][y] = true;
        data.path[x][y] = true;
        setData();
        if (x == data.getExitX() && y == data.getExitY())
            return true;

        for (int i = 0; i < 4; i++) {
            int newX = x + offset[i][0];
            int newY = y + offset[i][1];
            if (data.inArea(newX, newY) && data.maze[newX][newY] == data.ROAD && !data.visited[newX][newY]) {
                if (goMaze(newX, newY))
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

    public static void main(String[] args) {
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(101, 101);

    }

}


