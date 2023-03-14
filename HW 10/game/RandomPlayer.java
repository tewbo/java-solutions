package game;

import java.util.Random;

public class RandomPlayer implements Player{
    private final Random random;
    private final String name;

    public RandomPlayer(final Random random, String name) {
        this.random = random;
        this.name = name;
    }

    public RandomPlayer(String name) {
        this(new Random(), name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move move(Position position, Cell turn) {
        int correct = 0;
        int r = -1;
        int c = -1;
        while (correct == 0) {
            r = random.nextInt(position.getN());
            c = random.nextInt(position.getM());
            if (position.isValid(new Move(r, c, turn))) {
                correct = 1;
            }
        }
        return new Move(r, c, turn);
    }
}
