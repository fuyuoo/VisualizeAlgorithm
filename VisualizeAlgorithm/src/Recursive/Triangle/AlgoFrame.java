package Recursive.Triangle;

import Recursive.Fractal.FractalData;
import util.AlgoVisuHelper;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public AlgoFrame(String title) {

        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    // data
    private FractalData data;

    public void render(FractalData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 双缓存
            super(true);

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);
            AlgoVisuHelper.setColor(g2d, AlgoVisuHelper.Indigo);
            drawFractal(g2d, 0, 0, canvasWidth, canvasHeight, 0);

        }

        private void drawFractal(Graphics2D g2d, int x1, int y1, int x2, int y2, int depth) {
            int w = x2 / 3;
            if (depth == data.depth) {
                AlgoVisuHelper.fillRectangle(g2d, x1+w, y1+w, w,w);
                return;
            }
            if (x2 <= 1 || y2 <= 1) {
                return;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1)
                        AlgoVisuHelper.fillRectangle(g2d, x1+w, y1+w, w,w);
                    else
                        drawFractal(g2d,x1+ i * w ,y1 + j*w,w,w,depth+1);

                }
            }

        }


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}