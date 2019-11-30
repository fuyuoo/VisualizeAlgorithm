package Move_the_box_Solver;

import util.AlgoVisuHelper;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

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
    private GameData data;

    public void render(GameData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        private HashMap<Character, Color> colorHashMap;
        private ArrayList<Color> colorArrayList;

        public AlgoCanvas() {
            // 双缓存
            super(true);
            colorHashMap = new HashMap<>();
            colorArrayList = new ArrayList<>();
            colorArrayList.add(AlgoVisuHelper.Red);
            colorArrayList.add(AlgoVisuHelper.Purple);
            colorArrayList.add(AlgoVisuHelper.Blue);
            colorArrayList.add(AlgoVisuHelper.Teal);
            colorArrayList.add(AlgoVisuHelper.LightGreen);
            colorArrayList.add(AlgoVisuHelper.Lime);
            colorArrayList.add(AlgoVisuHelper.Amber);
            colorArrayList.add(AlgoVisuHelper.DeepOrange);
            colorArrayList.add(AlgoVisuHelper.Brown);
            colorArrayList.add(AlgoVisuHelper.BlueGrey);
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

            // 具体绘制
            int w = canvasWidth / data.M();
            int h = canvasHeight / data.N();

            Board showBoard = data.getShowBoard();
            for (int i = 0; i < showBoard.N(); i++) {
                for (int j = 0; j < showBoard.M(); j++) {
                    char c = showBoard.getData(i, j);
                    if (c != Board.EMPTY) {
                        if (!colorHashMap.containsKey(c)) {
                            int sz = colorHashMap.size();
                            colorHashMap.put(c, colorArrayList.get(sz));
                        }
                        Color color = colorHashMap.get(c);
                        AlgoVisuHelper.setColor(g2d, color);
                        AlgoVisuHelper.fillRectangle(g2d, j * h + 2, i * w + 2, w - 4, h - 4);
                    }
                }
            }


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}