package Move_the_box_Solver;
import util.AlgoVisuHelper;

import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 80;

    private GameData data;
    private AlgoFrame frame;

    public AlgoVisualizer(String filename){

        data = new GameData(filename);
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box Solver", sceneWidth,sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){
        setData();
        if (data.solve())
            System.out.println("has solution");
        else
            System.out.println("have not solution");
    }

    public void setData(){
        frame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        String filename = "level/boston_16.txt";

        AlgoVisualizer vis = new AlgoVisualizer(filename);
    }
}
