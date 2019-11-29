package MineSweeper;


import Maze.Position;
import Maze_Generation.MazeData;
import Maze_Generation.RandomList2;
import util.AlgoVisuHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgoVisualizer {
    private MineSweeperData data;
    private AlgoFrame algoFrame;
    private int sceneWidth;
    private int sceneHeight;
    private static int blockSide = 32;
    private int DELAY = 2;

    private static final int[][] offset = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgoVisualizer(int N, int M, int mineNumber) {
        data = new MineSweeperData(N, M, mineNumber);
        sceneHeight = data.getN() * blockSide;
        sceneWidth = data.getM() * blockSide;

        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame("Title", sceneWidth, sceneHeight);
            algoFrame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        setData(false,-1,-1);

    }





    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            e.translatePoint(-(int) (algoFrame.getBounds().width - algoFrame.getCanvasWidth()),
                             -(int) (algoFrame.getBounds().height - algoFrame.getCanvasHeight()));
            Point pos = e.getPoint();
            int w = algoFrame.getCanvasWidth() / data.getM();
            int h = algoFrame.getCanvasHeight() / data.getN();
            int x = pos.y / h;
            int y = pos.x / w;
            setData(SwingUtilities.isLeftMouseButton(e),x,y);
        }
    }
    private void setData(boolean isLeftClick,int x,int y) {

        if (data.inArea(x,y))
            if (isLeftClick)
                if (data.isMine(x,y)){
                    // todo GameOver!
                    data.open[x][y] = true;
                }else
                    data.open(x,y);
            else
                data.flag[x][y] = !data.flag[x][y];

        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        AlgoVisualizer algoVisualizer = new AlgoVisualizer(20,20,30);

    }

}


