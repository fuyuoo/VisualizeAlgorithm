package util;


public class ShuffleExp {
    private int N;
    private int n;
    private int m;
    // N 次  n 个可能的雷区 共有 m 个雷
    public ShuffleExp(int N, int n, int m) {

        if (N <= 0)
            throw new IllegalArgumentException("N must be larger than 0!");
        if (n < m )
            throw new IllegalArgumentException("n must be larger than or equal to m !");
        this.N = N;
        this.n = n;
        this.m = m;
    }
    public void run(){
        int arr[] = new int[n];
        int freq[] = new int[n];
        for (int i = 0; i < N; i++) {
            reset(arr);
            shuffle(arr);
            for (int j = 0; j < n; j++) {
                freq[j]+=arr[j];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + " : " + (double)freq[i]/N);
        }
    }

    private void shuffle(int[] arr) {
        for (int i = n-1; i >=0; i--) {
            int x = (int)(Math.random() * (i+1));
            swap(arr,i,x);
        }
    }
    private void swap(int []arr,int i,int x){
        int t = arr[i];
        arr[i] = arr[x];
        arr[x] = t;
    }

    private void reset(int[] arr) {
        for (int i = 0; i < m; i++) {
            arr[i] = 1;
        }
        for (int i = m; i < n; i++) {
            arr[i] = 0;
        }
    }

    public static void main(String[] args) {
        ShuffleExp shuffleExp = new ShuffleExp(10000000,10,5);
        shuffleExp.run();
    }
}
