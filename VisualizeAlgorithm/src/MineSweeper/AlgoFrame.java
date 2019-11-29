package MineSweeper;

import Maze_Generation.MazeData;
import util.AlgoVisuHelper;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasHeight = canvasHeight;
        this.canvasWidth = canvasWidth;

        AlgoCanvans canvans = new AlgoCanvans();
        canvans.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        setContentPane(canvans);

        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame() {
        this("Title", 1024, 768);

    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    //Todo
    private MineSweeperData data;

    public void render(MineSweeperData data) {
        this.data = data;
        repaint();
    }


    private class AlgoCanvans extends JPanel {

        public AlgoCanvans() {
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            int w = canvasWidth / data.getM();
            int h = canvasHeight / data.getN();
            for (int i = 0; i < data.getN(); ++i) {
                for (int j = 0; j < data.getM(); j++) {

                    if (data.open[i][j])
                        if (data.isMine(i,j))
                            AlgoVisuHelper.putImage(g2d,j*w,i*h,MineSweeperData.mineImageURL);
                        else
                            AlgoVisuHelper.putImage(g2d,j*w,i*h,MineSweeperData.numberImageURL(data.numbers[i][j]));
                    else
                        if(data.flag[i][j])
                            AlgoVisuHelper.putImage(g2d,j*w,i*h,MineSweeperData.flagImageURL);
                        else
                            AlgoVisuHelper.putImage(g2d,j*w,i*h,MineSweeperData.blockImageURL);

                }

            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }

    }
}
