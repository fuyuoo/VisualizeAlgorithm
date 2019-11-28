package Maze_Generation;

import java.util.Date;

public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';
    private int N, M;
    public char[][] maze;
    private int entranceX, entranceY;
    private int exitX, exitY;
    public boolean visited[][];
    public boolean outMist[][];
    public boolean path[][];


    public MazeData(int N, int M) {
        if (N % 2 == 0 || M % 2 == 0)
            throw new IllegalArgumentException("Our Maze Generalization need odd number of cols and rows ");
        this.N = N;
        this.M = M;
        maze = new char[N][M];
        outMist = new boolean[N][M];
        visited = new boolean[N][M];
        path = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (j % 2 == 1 && i % 2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
            }
        }
        this.entranceX = 1;
        this.entranceY = 0;
        this.exitX = N - 2;
        this.exitY = M - 1;
        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public boolean inArea(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < M)
            return true;
        return false;
    }

    public void openMist(int x, int y) {
        if (inArea(x, y)) {
            for (int i = x - 1; i < x + 1; i++) {
                for (int j = y - 1; j < y + 1; j++) {
                    if (inArea(i, j))
                        outMist[i][j] = true;
                }
            }
        }

    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }
}
