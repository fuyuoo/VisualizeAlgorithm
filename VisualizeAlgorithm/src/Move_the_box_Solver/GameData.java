package Move_the_box_Solver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GameData {

    private int maxTurn;
    private int N, M;
    private Board startBoard;

    private int offset[][] = {{1, 0}, {0, 1}, {0, -1}};
    private Board showBoard;
    public int clickx = -1, clicky = -1;

    public GameData(String filename) {

        if (filename == null)
            throw new IllegalArgumentException("Filename cannot be null!");

        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException("File " + filename + " doesn't exist!");

            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String turnline = scanner.nextLine();

            this.maxTurn = Integer.parseInt(turnline);

            ArrayList<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
            startBoard = new Board(lines.toArray(new String[lines.size()]));
            showBoard = new Board(startBoard,null,"");
            this.N = startBoard.N();
            this.M = startBoard.M();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null)
                scanner.close();
        }
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }

    public Board getShowBoard() {
        return showBoard;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public boolean solve() {
        if (maxTurn < 0)
            return false;
        return solve(maxTurn, startBoard);
    }

    private boolean solve(int turns, Board board) {
        if (turns < 0 || board == null)
            throw new IllegalArgumentException("Illegal argument in solve!");
        if (turns == 0)
            return board.isWin();
        if (board.isWin()){
            return true;

        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board.getData(i, j) != Board.EMPTY)
                    for (int k = 0; k < offset.length; k++) {
                        int newX = i + offset[k][0];
                        int newY = j + offset[k][1];
                        if (board.inArea(newX, newY)) {
                            String swapInf = String.format("( %d , %d ) and ( %d , %d )",i,j,newX,newY);
                            Board nextBoard = new Board(board,board,swapInf);
                            nextBoard.swap(i, j, newX, newY);
                            nextBoard.run();
                            if (solve(turns-1,nextBoard))
                                return true;
                        }
                    }
            }
        }
        return false;

    }


}
