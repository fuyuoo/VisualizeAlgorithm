package Recursive.Tree;

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
    private TreeData data;

    public void render(TreeData data) {
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
            AlgoVisuHelper.setColor(g2d, AlgoVisuHelper.Teal);
            drawFractal(g2d, canvasWidth / 2, canvasHeight, canvasHeight, 0, 0);

        }

        private void drawFractal(Graphics2D g2d, double x1, double y1, double side, double angle, int depth) {

            double side_2 = side / 2;
            if (side_2 <= 0 || depth == data.depth)
                return;

            double x2 = x1 - side_2 * Math.sin(angle * Math.PI / 180.0);
            double y2 = y1 - side_2 * Math.cos(angle * Math.PI / 180.0);

            AlgoVisuHelper.setStrokeWidth(g2d, data.depth - depth);
            AlgoVisuHelper.drawLine(g2d, x1, y1, x2, y2);
            double ran = Math.random();

            drawFractal(g2d, x2, y2, side_2, angle + data.splitAngle * ran, depth + 1);
            drawFractal(g2d, x2, y2, side_2, angle - data.splitAngle * (1 - ran), depth + 1);


        }


        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}