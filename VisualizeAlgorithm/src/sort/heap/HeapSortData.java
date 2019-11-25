package sort.heap;

import java.util.Arrays;

public class HeapSortData {

    public int[] numbers;
    public int heapIndex ;
    public enum dataType{
        normal,
        nearlyOrder,
        Identical
    }

    public HeapSortData(int N, int randomBound, boolean def) {

        numbers = new int[N];
        heapIndex = N;
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound);
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
