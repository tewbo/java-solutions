package game;

public class Game {
    final Player[] players;
    final static int MAX_PLAYERS_COUNT = 4;

    public Game(final Player... players) {
        this.players = players;
    }

    public int play(Board board) {
        board.printBoard();

        if (board.getPlayersCnt() > players.length) {
            throw new RuntimeException("The players provided are not enough");
        }

        while (true) {
            for (int i = 0; i < board.getPlayersCnt(); i++) {
                final int result = move(board, players[i], i + 1);
                if (result != MAX_PLAYERS_COUNT + 1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getTurn());
        final Result result = board.makeMove(move);
        board.printBoard();
        if (result == Result.WIN) {
            return no;
        } else if (result == Result.LOSE) {
            return -no;
        } else if (result == Result.DRAW) {
            return 0;
        } else {
            return MAX_PLAYERS_COUNT + 1;
        }
    }
}
