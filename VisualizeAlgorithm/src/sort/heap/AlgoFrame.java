package sort.heap;

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
    private HeapSortData data;

    public void render(HeapSortData data) {
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
            for (int i = 0; i < data.N(); ++i) {

                if (i >= data.heapIndex) {
                    AlgoVisuHelper.setColor(g2d, AlgoVisuHelper.Green);
                } else
                    AlgoVisuHelper.setColor(g2d, AlgoVisuHelper.Grey);

                AlgoVisuHelper.fillRectangle(g2d, i * w, canvasHeight - data.get(i), w - 1, data.get(i));
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }

    }
}
