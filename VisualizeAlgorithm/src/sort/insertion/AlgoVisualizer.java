package sort.insertion;

import util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {
    private InsertionSortData data;
    private AlgoFrame algoFrame;
    private boolean isAnimated = true;
    private int sceneWidth = 800;
    private int sceneHeight = 800;
    private int DELAY = 100;

    public AlgoVisualizer(InsertionSortData data) {
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
        setData(0,-1);
        for (int i = 0; i < data.N(); i++) {
            setData(i,i);

            for (int j = i; j > 0 && data.get(j) < data.get(j-1); j--) {
                data.swap(j,j-1);
                setData(i+1,j);
            }

        }
        setData(data.N(),-1);



    }

    private void setData(int orderedIndex, int currentCompareIndex) {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
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
