package game;

public class MnkPosition implements Position {
    final private int n;
    final private int m;
    private final Cell[][] table;

    public MnkPosition(int m, int n, final Cell[][] table) {
        this.n = n;
        this.m = m;
        this.table = table;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public Cell getCell(int r, int c) {
        return table[r][c];
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < n &&
                0 <= move.getColumn() && move.getColumn() < m &&
                table[move.getRow()][move.getColumn()] == Cell.E;
    }
}
