package Maze;

public class Main {
    public static void main(String[] args) {
        String mazeFile = "src/Maze/maze_101_101.txt";
        MazeData mazeData = new MazeData(mazeFile);
        mazeData.print();
    }
}
