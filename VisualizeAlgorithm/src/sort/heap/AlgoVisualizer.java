package sort.heap;

import sort.util.AlgoVisuHelper;

import java.awt.*;

public class AlgoVisualizer {
    private HeapSortData data;
    private AlgoFrame algoFrame;
    private boolean isAnimated = true;
    private int sceneWidth = 800;
    private int sceneHeight = 800;
    private int DELAY = 50;

    public AlgoVisualizer(HeapSortData data) {
        this.data = data;
        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame("title", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {

        setData(data.N());
        sort();
        setData(0);


    }

    private void sort() {
        for (int i = (data.N()-2)/2; i >=0; i--) {
            shiftDown(data.N(),i);
        }
        for (int i = data.N()- 1; i >= 0 ; i--) {
            data.swap(i,0);
            shiftDown(i,0);
            setData(i);
        }

    }
    private void shiftDown(int n,int k ){

        while (k*2+1 < n){
            int chi = k*2+1;
            if (chi+1 < n && data.get(chi+1) > data.get(chi)){
                chi+=1;
            }
            if (data.get(k) >= data.get(chi))
                break;
            data.swap(k,chi);
            setData(data.heapIndex);

            k = chi;
        }
    }


    private void setData(int heapIndex) {
        data.heapIndex = heapIndex;
        algoFrame.render(data);
        AlgoVisuHelper.pause(DELAY);
    }


}
