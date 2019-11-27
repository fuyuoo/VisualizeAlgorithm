package sort.QuickSort;

import util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {
    private QuickSortData data;
    private AlgoFrame algoFrame;
    private boolean isAnimated = true;
    private int sceneWidth = 800;
    private int sceneHeight = 800;
    private int DELAY = 50;

    public AlgoVisualizer(QuickSortData data) {
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
        setData3Ways(-1,-1,-1,-1,-1,-1);
        quickSort3Ways(0,data.N()-1);
        setData3Ways(-1,-1,-1,-1,-1,-1);


    }

    private void sort(int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r) {
            setData2Ways(l, r, -1, l, -1, -1);
            return;
        }
        setData2Ways(l, r, -1, -1, -1, -1);

        int pivot = quickSort2Ways(l, r);
        sort(l, pivot);
        sort(pivot + 1, r);
    }

    //  单路
    private int quickSort(int l, int r) {

        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1);

        data.swap(l, p);
        int key = data.get(l);
        setData(l, r, -1, l, -1);

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            setData(l, r, i, l, -1);
            if (data.get(i) < key) {
                j++;
                data.swap(i, j);
                setData(l, r, i, l, -1);

            }
        }
        data.swap(l, j);
        setData(l, r, -1, -1, j);

        return j;
    }

    // 双路 解决相同元素 O^2
    private int quickSort2Ways(int l, int r) {
        int p = (int) (Math.random() * (r - l + 1)) + l;
        setData2Ways(l, r, p, -1, -1, -1);

        data.swap(l, p);
        int key = data.get(l);
        setData2Ways(l, r, l, -1, -1, -1);

        int i = l + 1;
        int j = r;
        setData2Ways(l, r, l, -1, i, j);
        while (true) {
            while (i <= r && data.get(i) < key) {
                setData2Ways(l, r, l, -1, i, j);
                i++;
            }
            while (j >= l + 1 && data.get(j) > key) {
                j--;
                setData2Ways(l, r, l, -1, i, j);

            }
            if (i > j)
                break;
            data.swap(i, j);
            i++;
            j--;
            setData2Ways(l, r, l, -1, i, j);

        }
        data.swap(l, j);
        setData2Ways(l, r, -1, j, -1, -1);

        return j;
    }

    // 三路 可以跳过相同的元素
    private void quickSort3Ways(int l, int r) {
        if (l > r) {
            return;
        }
        if (l == r) {
            setData3Ways(l,r,-1,l,-1,-1);
            return;
        }
        int p = (int) (Math.random() * (r - l + 1) + l);
        data.swap(l, p);
        setData3Ways(l,r,p,-1,-1,-1);

        int v = data.get(l);
        int gl = l;
        int i = l + 1;
        int gr = r + 1;
        setData3Ways(l,r,l,-1,gl,gr);

        while (i < gr) {
            if (data.get(i) < v) {
                data.swap(i, gl + 1);
                gl++;
                i++;

            } else if (data.get(i) > v) {
                data.swap(i, gr - 1);
                gr--;
            } else
                i++;
            setData3Ways(l,r,l,-1,i,gr);

        }
        data.swap(l, gl);
        setData3Ways(l,r,-1,gl,-1,-1);
        quickSort3Ways(l, gl - 1);
        quickSort3Ways(gr, r);
    }


    private void setData(int l, int r, int currentIndex, int curPivotIndex, int fix) {
        data.l = l;
        data.r = r;
        data.currentIndex = currentIndex;
        data.PivotIndex = curPivotIndex;
        if (fix != -1)
            data.fixed[fix] = true;
        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }

    private void setData2Ways(int l, int r, int curPivotIndex, int fix, int curL, int curR) {
        data.l = l;
        data.r = r;

        data.PivotIndex = curPivotIndex;
        data.curR = curR;
        data.curL = curL;
        if (fix != -1)
            data.fixed[fix] = true;
        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }

    private void setData3Ways(int l, int r, int curPivotIndex, int fix, int curL, int curR) {
        data.l = l;
        data.r = r;

        data.PivotIndex = curPivotIndex;
        data.curR = curR;
        data.curL = curL;
        if (fix != -1) {
            for (int i = fix; i < data.N() && data.get(fix) == data.get(i); i++) {
                data.fixed[i] = true;
            }
        }
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
