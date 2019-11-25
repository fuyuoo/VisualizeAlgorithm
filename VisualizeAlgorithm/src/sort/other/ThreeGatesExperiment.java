package sort.other;

public class ThreeGatesExperiment {

    private int N;

    ThreeGatesExperiment(int N) {
        this.N = N;
    }

    public void run(boolean changeDoor) {
        int wins = 0;
        for (int i = 0; i < N; i++) {
            if (play(changeDoor))
                wins++;
        }
        System.out.println((double) wins / N);
    }

    private boolean play(boolean changeDoor) {
        int priceDoor = (int) (Math.random() * 3);
        int chooseDoor = (int) (Math.random() * 3);
        if (priceDoor == chooseDoor)
            return !changeDoor;
        else
            return changeDoor;
    }

    public static void main(String[] args) {
        ThreeGatesExperiment exp = new ThreeGatesExperiment(100000);
        exp.run(true);
        exp.run(false);
    }
}
