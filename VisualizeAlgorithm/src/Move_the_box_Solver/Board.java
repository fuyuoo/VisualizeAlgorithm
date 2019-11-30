package Move_the_box_Solver;

public class Board {

    private int N, M;
    private char[][] data;
    public static char EMPTY = '.';
    public Board preBoard;
    public String swapInf;

    public Board(String[] lines) {
        if (lines == null)
            throw new IllegalArgumentException("lines cannot be null in Board constructor.");

        N = lines.length;
        if (N == 0)
            throw new IllegalArgumentException("lines cannot be empty in Board constructor.");

        M = lines[0].length();

        data = new char[N][M];
        for (int i = 0; i < N; i++) {
            if (lines[i].length() != M)
                throw new IllegalArgumentException("All lines' length must be same in Board constructor.");
            for (int j = 0; j < M; j++)
                data[i][j] = lines[i].charAt(j);
        }
    }

    public Board(Board board,Board preBoard,String swapInf) {
        if (board == null)
            throw new IllegalArgumentException("the board is null");
        this.N = board.N;
        this.M = board.M;
        this.data = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.data[i][j] = board.data[i][j];
            }
        }
        this.preBoard = preBoard;
        this.swapInf = swapInf;

    }

    public char getData(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("the x,y out of index !");
        return data[x][y];
    }

    public boolean isWin() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] != Board.EMPTY)
                    return false;
            }
        }
        printWinStep();
        return true;
    }

    private void printWinStep() {
        if (preBoard != null)
            preBoard.printWinStep();
        System.out.println(swapInf);
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print() {
        for (int i = 0; i < N; i++)
            System.out.println(String.valueOf(data[i]));
    }

    public void swap(int i, int j, int newX, int newY) {
        if (!inArea(i,j) || !inArea(newX,newY))
            throw new IllegalArgumentException("x,y are out of index in swap!");

        char tmp = data[i][j];
        data[i][j] = data[newX][newY];
        data[newX][newY] = tmp;

    }

    public void run() {
        do {
            drop();
        } while (match());

    }

    private int matchOffset[][] = {{1, 0}, {0, 1}};

    private boolean match() {
        boolean tag[][] = new boolean[N][M];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] != EMPTY) {
                    for (int k = 0; k < matchOffset.length; k++) {
                        int newX = i + matchOffset[k][0];
                        int newY = j + matchOffset[k][1];
                        int newX1 = newX + matchOffset[k][0];
                        int newY1 = newY + matchOffset[k][1];
                        if (inArea(newX, newY) && inArea(newX1, newY1))
                            if (data[i][j] == data[newX][newY] && data[newX][newY] == data[newX1][newY1]) {
                                tag[i][j] = tag[newX][newY] = tag[newX1][newY1] = true;
                                flag = true;

                            }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tag[i][j])
                    data[i][j] = EMPTY;
            }
        }
        return flag;
    }

    private void drop() {
        for (int j = 0; j < M; j++) {
            int cur = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (data[i][j] != EMPTY) {
                    swap(i, j, cur, j);
                    cur--;
                }


            }
        }
    }
}
