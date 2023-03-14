package game;

import java.util.Arrays;
import java.util.Map;

public class MnkBoard implements Board {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.E, '.',
            Cell.O, 'O',
            Cell.T, '-',
            Cell.L, '|',
            Cell.USED, '+'
    );
    private final int n;
    private final int m;
    private final int k;
    protected final Cell[][] table;
    private final int playersCnt;
    private final Cell[] turns;
    private int turnPos;
    private int emptyCnt;


    public MnkBoard(final int m, final int n, final int k, final int playersCnt) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.table = new Cell[n][m];
        this.playersCnt = playersCnt;
        for (Cell[] row : table) {
            Arrays.fill(row, Cell.E);
        }
        turns = new Cell[]{Cell.X, Cell.O, Cell.T, Cell.L};
        turnPos = 0;
        emptyCnt = n * m;
    }

    private int countLine(int row, int column, int rowShift, int columnShift, Cell val) {
        row += rowShift;
        column += columnShift;
        int cnt = 0;
        while (cnt + 1 < k && row >= 0 && row < n && column >= 0 && column < m && table[row][column] == val) {
            cnt++;
            row += rowShift;
            column += columnShift;
        }
        return cnt;
    }

    @Override
    public Position getPosition() {

        return new MnkPosition(m, n, table);
    }

    @Override
    public Cell getTurn() {
        return turns[turnPos];
    }

    @Override
    public int getPlayersCnt() {
        return playersCnt;
    }

    @Override
    public Result makeMove(final Move move) {
        Position mnkPosition = getPosition();
        if (!mnkPosition.isValid(move)) {
            return Result.LOSE;
        }

        table[move.getRow()][move.getColumn()] = move.getValue();
        emptyCnt--;

        final int r = move.getRow();
        final int c = move.getColumn();
        final Cell val = move.getValue();
        int rowSum = 1 + countLine(r, c, 0, 1, val) + countLine(r, c, 0, -1, val);
        int colSum = 1 + countLine(r, c, 1, 0, val) + countLine(r, c, -1, 0, val);
        int diag1 = 1 + countLine(r, c, 1, 1, val) + countLine(r, c, -1, -1, val);
        int diag2 = 1 + countLine(r, c, 1, -1, val) + countLine(r, c, -1, 1, val);

        if (rowSum >= k || colSum >= k || diag1 >= k || diag2 >= k) {
            return Result.WIN;
        }

        if (emptyCnt == 0) {
            return Result.DRAW;
        }

        turnPos = (turnPos + 1) % playersCnt;
        return Result.UNKNOWN;
    }

    @Override
    public void printBoard() {
        System.out.print("  ");
        for (int j = 0; j < m; j++) {
            System.out.print((j + 1) % 10);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) % 10);
            System.out.print(" ");
            for (int j = 0; j < m; j++) {
                System.out.print(SYMBOLS.get(table[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }
}
