package game;

public class BoardWithBarriers extends  MnkBoard {
    BoardWithBarriers(final int m, final int n, final int k, final int playersCnt) {
        super(m, n, k, playersCnt);
        for (int i = 0; i < Math.min(m, n); i++) {
            table[i][i] = Cell.USED;
            table[i][m-i-1] = Cell.USED;
        }
    }
}
