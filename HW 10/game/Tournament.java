package game;

public class Tournament {
    private final Player[] players;
    private final int[][] sheet;
    private final int playersCnt;
    private final int m;
    private final int n;
    private final int k;

    public Tournament(final int m, final int n, final int k, final int playersCnt, final Player... players) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.players = players;
        sheet = new int[players.length][players.length];
        this.playersCnt = playersCnt;
    }

    public void play() {
        for (int i = 0; i < playersCnt; i++) {
            for (int j = 0; j < playersCnt; j++) {
                if (i == j) {
                    continue;
                }
                System.out.println("Player " + players[i].getName() + " and Player " + players[j].getName() + " are playing.");
                Game game = new Game(players[i], players[j]);
                int result = game.play(new MnkBoard(m, n, k, 2));
                if (result == 0) {
                    System.out.println("Draw");
                    sheet[i][j] += 1;
                    sheet[j][i] += 1;
                    continue;
                }
                if (result == 1 || result == -2) { //first won or second lose
                    sheet[i][j] += 3;
                    System.out.println("Player " + players[i].getName() + " win");
                } else {
                    sheet[j][i] += 3;
                    System.out.println("Player " + players[j].getName() + " win");
                }
                System.out.println();

            }
        }
        printResult();
    }

    private void printResult() {
        System.out.println("Results:");
        for (int i = 0; i < playersCnt; i++) {
            for (int j = 0; j < playersCnt; j++) {
                System.out.print(sheet[i][j] + " ");
            }
            System.out.println();
        }
    }

}
