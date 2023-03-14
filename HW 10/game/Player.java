package game;

public interface Player {
    Move move(Position position, Cell turn);

    String getName();
}
