package game;

public interface Board {
    Position getPosition();

    Cell getTurn();

    int getPlayersCnt();

    Result makeMove(Move move);

    void printBoard();

}
