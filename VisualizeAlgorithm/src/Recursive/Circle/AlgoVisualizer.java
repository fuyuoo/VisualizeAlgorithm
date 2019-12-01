package Recursive.Circle;

import util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 80;

    private CircleData data;
    private AlgoFrame frame;



    public AlgoVisualizer(int sceneWidth, int sceneHeight){

        int r = Math.min(sceneHeight,sceneWidth) / 2 - 2;
        data = new CircleData(sceneWidth/2,sceneHeight/2,r,r/2,4);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver", sceneWidth,sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        setData();
    }


    private void setData(){

        frame.render(data);
        AlgoVisuHelper.pause(DELAY);

    }

    public static void main(String[] args) {


        AlgoVisualizer vis = new AlgoVisualizer(800,600);
    }
}
