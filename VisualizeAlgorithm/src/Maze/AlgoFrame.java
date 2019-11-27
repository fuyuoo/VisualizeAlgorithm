package Maze;

import sort.heap.HeapSortData;
import util.AlgoVisuHelper;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

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
    private MazeData data;

    public void render(MazeData data) {
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

            //Todo
            int w = canvasWidth / data.N();
            int h = canvasWidth /data.M();
            for (int i = 0; i < data.N(); ++i) {
                for (int j = 0; j < data.M(); j++) {
                    if (data.getMaze(i,j) == data.BAFFLE)
                        AlgoVisuHelper.setColor(g2d,AlgoVisuHelper.Teal);
                    else
                        AlgoVisuHelper.setColor(g2d,AlgoVisuHelper.White);
                    if (data.path[i][j])
                        AlgoVisuHelper.setColor(g2d,AlgoVisuHelper.Purple);

                    AlgoVisuHelper.fillRectangle(g2d, j * w, i*h,w,h);
                }

            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }

    }
}
