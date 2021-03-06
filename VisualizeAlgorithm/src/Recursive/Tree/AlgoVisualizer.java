package Recursive.Tree;

import Recursive.Fractal.FractalData;
import util.AlgoVisuHelper;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlgoVisualizer {

    private static int DELAY = 5;
    private static int blockSide = 80;

    private TreeData data;
    private AlgoFrame frame;



    public AlgoVisualizer(int depth,double splitAngle){

        data = new TreeData(depth,splitAngle);
        int sceneWidth = 1024;
        int sceneHeight = 768 ;

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("title", sceneWidth,sceneHeight);
            frame.addKeyListener(new keyboardEvent() );

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){

        setData(data.depth);
    }

    private class   keyboardEvent extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() >= '0' && e.getKeyChar() <='9'){
                setData(e.getKeyChar() - '0');
            }

        }
    }

    private void setData(int depth){
        if (depth > 0)
            data.depth = depth;
        frame.render(data);
        AlgoVisuHelper.pause(DELAY);

    }

    public static void main(String[] args) {


        AlgoVisualizer vis = new AlgoVisualizer(10,60);
    }
}
