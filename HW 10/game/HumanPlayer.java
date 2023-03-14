package game;

// :NOTE: пакет должен называться game, а не mnk :)

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements Player{
    private final Scanner in;
    private final PrintStream out;
    private final String name;

    public HumanPlayer(final PrintStream out, final Scanner in, String name) {
        this.out = out;
        this.in = in;
        this.name = name;
    }

    public HumanPlayer(String name) {
        this(System.out, new Scanner(System.in), name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move move(Position position, Cell turn) {
        System.out.println("Player " + name +", enter row and column.");
        int r = -1;
        int c = -1;
        boolean correct = false;
        while (!correct) {
            String line = in.nextLine();
            Scanner lineScanner = new Scanner(line);
            if (!lineScanner.hasNextInt()) {
                System.out.println("Input is not correct. Please enter 2 ints. Try again.");
                continue;
            }
            r = lineScanner.nextInt();
            if (!lineScanner.hasNextInt()) {
                System.out.println("Input is not correct. Please enter 2 ints. Try again.");
                continue;
            }
            c = lineScanner.nextInt();
            r--;
            c--;
            if (!position.isValid(new Move(r, c, turn))) {
                System.out.println("The selected sell is incorrect. Please try again.");
                continue;
            }
            correct = true;
        }

        return new Move(r, c, turn);
    }
}
