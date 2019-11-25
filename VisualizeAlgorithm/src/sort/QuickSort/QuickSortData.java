package sort.QuickSort;

import java.util.Arrays;

public class QuickSortData {

    public int[] numbers;
    public int l = -1;
    public int r = -1;
    public int PivotIndex = -1;
    public int currentIndex = -1;
    public int curL = -1;
    public int curR = -1;
    public boolean[] fixed;
    public enum dataType{
        normal,
        nearlyOrder,
        Identical
    }

    public QuickSortData(int N, int randomBound,boolean def) {

        numbers = new int[N];
        fixed = new boolean[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound);
            fixed[i] = false;
        }
        if (def == false){
            Arrays.sort(numbers);
            int t = (int)(numbers.length * 0.02);
            for (int i = 0; i < t; i++) {
                int x = (int)(Math.random() * numbers.length);
                int y = (int)(Math.random() * numbers.length);
                swap(x,y);
            }
        }
    }

    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length){
            System.out.println(index);
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        }

        return numbers[index];
    }

    public void swap(int i, int j) {

        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
