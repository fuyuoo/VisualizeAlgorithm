package Move_the_box_Solver;
import util.AlgoVisuHelper;

import javax.swing.*;
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
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){
        setData(-1,-1);

//        SwingUtilities.isLeftMouseButton()
        if (data.solve())
            System.out.println("has solution");
        else
            System.out.println("have not solution");
    }

    private Position clickPos1 = null;
    private Position clickPos2 = null;
    private class AlgoMouseListener extends MouseAdapter{

        @Override
        public void mouseReleased(MouseEvent event){
            event.translatePoint(
                    -(int)(frame.getBounds().width - frame.getCanvasWidth()),
                    -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );

            Point pos = event.getPoint();
            //System.out.println(pos.x + " , " + pos.y );

            int w = frame.getCanvasWidth() / data.M();
            int h = frame.getCanvasHeight() / data.N();

            int x = pos.y / h;
            int y = pos.x / w;

            if(SwingUtilities.isLeftMouseButton(event)){
                if(data.inArea(x, y)){
                    setData(x, y);
                    if(clickPos1 == null){
                        clickPos1 = new Position(x, y);
                    }
                    else{
                        clickPos2 = new Position(x, y);
                        if(clickPos2.nextTo(clickPos1)){
                            data.getShowBoard().swap(clickPos1.getX(), clickPos1.getY(), clickPos2.getX(), clickPos2.getY());
                            data.getShowBoard().run();
                        }
                        clickPos1 = null;
                        clickPos2 = null;
                        setData(-1, -1);
                    }
                }
                else{
                    setData(-1, -1);
                    clickPos1 = null;
                    clickPos2 = null;
                }
            }
        }


}
    private void setData(int clickx, int clicky){
        data.clickx = clickx;
        data.clicky = clicky;

        frame.render(data);
        AlgoVisuHelper.pause(DELAY);

    }

    public static void main(String[] args) {

        String filename = "level/boston_16.txt";

        AlgoVisualizer vis = new AlgoVisualizer(filename);
    }
}
