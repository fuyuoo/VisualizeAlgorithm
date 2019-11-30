package sort.merge;

import util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {
    private MergeSortData data;
    private AlgoFrame algoFrame;
    private boolean isAnimated = true;
    private int sceneWidth = 800;
    private int sceneHeight = 800;
    private int DELAY = 20;

    public AlgoVisualizer(MergeSortData data) {
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

        sort(0,data.numbers.length-1);
        for (int i = 0; i < data.N(); i++) {
            System.out.print(data.numbers[i] + " ");

        }
        setData(0,data.numbers.length,-1);


    }
    private void sort(int l,int r){
        if (l < r){
            setData(l,r,-1);
            int mid = l + (r-l) / 2;
            sort(l,mid);
            sort(mid+1,r);
            merge(l,mid,mid+1,r);
        }
    }
    private void merge(int l1,int r1,int l2,int r2){
        int k = r2-l1;
        int []tmp = new int[k+1];
        int i = r1;
        int j = r2;
        while (i >=l1 && j >=l2){
            if (this.data.numbers[i] < this.data.numbers[j]){
                tmp[k--] = this.data.numbers[j--];
            }else
                tmp[k--] = this.data.numbers[i--];
            setData(l1,r2,k);

        }
        while (i >=l1){
            tmp[k--] = this.data.numbers[i--];
            setData(l1,r2,k);
        }
        while (j >=r2){
            tmp[k--] = this.data.numbers[j--];
            setData(l1,r2,k);
        }
        int num = 0;

        for (int l = l1; l <= r2; l++) {
            data.numbers[l] = tmp[num++];
        }

    }

    private void setData(int l, int r, int currentIndex) {
        data.l = l;
        data.r = r;
        data.currentIndex = currentIndex;
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
