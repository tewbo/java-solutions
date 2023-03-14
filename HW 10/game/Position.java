package game;

public interface Position {
    Cell getCell(int r, int c);

    int getN();

    int getM();

    boolean isValid(Move move);
}
