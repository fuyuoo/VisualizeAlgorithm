package MineSweeper;

public class MineSweeperData {
    public static final String blockImageURL = "Resources/block.png";
    public static final String flagImageURL = "Resources/flag.png";
    public static final String mineImageURL = "Resources/mine.png";
    private int N;
    private int M;
    public boolean[][] mines;
    public boolean[][] open;
    public boolean[][] flag;
    public int[][] numbers;

    public static String numberImageURL(int num) {
        if (num < 0 || num > 8)
            throw new IllegalArgumentException("No such a number image!");
        return "Resources/" + num + ".png";
    }

    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0)
            throw new IllegalArgumentException("Mine sweeper size if invalid!");
        if (mineNumber < 0 || mineNumber > N * M) {
            throw new IllegalArgumentException("Mine number is large than the size ! ");
        }
        this.N = N;
        this.M = M;
        mines = new boolean[N][M]; // default all false
        open = new boolean[N][M];
        flag = new boolean[N][M];
        numbers = new int[N][M];

        generateMines(mineNumber);
        calNumbers();

    }

    private void calNumbers() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mines[i][j])
                    numbers[i][j] = -1;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (inArea(k, l) && mines[k][l])
                            numbers[i][j]++;
                    }
                }
            }
        }
    }

    private void generateMines(int mineNumber) {
        for (int i = 0; i < mineNumber; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }
        for (int i = N * M - 1; i >= 0; i--) {
            int x2 = i / M;
            int y2 = i % M;
            int ran = (int) (Math.random() * (i + 1));
            int x1 = ran / M;
            int y1 = ran % M;

            swap(x1, y1, x2, y2);
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean tmp = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = tmp;

    }

    public void open(int x, int y) {
        if (!inArea(x, y))
            return;
        if (isMine(x, y))
            return;
        open[x][y] = true;
        if (numbers[x][y] > 0)
            return;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inArea(i,j) && !open[i][j] && !mines[i][j])
                    open(i,j);
            }
        }
    }

    public boolean isMine(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        return mines[x][y];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }
}
