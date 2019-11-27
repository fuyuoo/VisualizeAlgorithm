package sort.selection;

import util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {
    private SelectionSortData data;
    private AlgoFrame algoFrame;
    private boolean isAnimated = true;
    private int sceneWidth = 800;
    private int sceneHeight = 800;
    private int DELAY = 10;

    public AlgoVisualizer(SelectionSortData data) {
        this.data = data;
        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame("title", sceneWidth, sceneHeight);
//            algoFrame.addKeyListener(new AlgoKeyListener());
//            algoFrame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        setData(0, -1, -1);
        for (int i = 0; i < data.N(); i++) {
            int minIndex = i;
            setData(i, -1, minIndex);

            for (int j = i + 1; j < data.N(); j++) {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(minIndex, i);
            setData(i + 1, -1, -1);
        }
        setData(data.N(), -1, -1);

    }

    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }


//    private class AlgoKeyListener extends KeyAdapter{
//        @Override
//        public void keyReleased(KeyEvent key){
//            if (key.getKeyChar() == ' '){
//                isAnimated = !isAnimated;
//            }
//        }
//    }
//
//    private class AlgoMouseListener extends MouseAdapter{
//        @Override
//        public void mousePressed(MouseEvent event){
//            event.translatePoint(0,-(algoFrame.getBounds().height - algoFrame.getCanvasHeight()));
//            System.out.println(event.getPoint());
//            for (Circle circle:circles){
//
//                if (circle.contain(event.getPoint())){
//                    circle.isFilled = !circle.isFilled;
//                }
//            }
//        }
//    }


}
