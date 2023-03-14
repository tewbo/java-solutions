package game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player[] players = new Player[]{new HumanPlayer("1"), new SequentialPlayer("2"),
                new RandomPlayer("3"), new HumanPlayer("4")};
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Play game");
            System.out.println("2. Play tournament");
            System.out.println("3. Play game on (11, 11) Board with barriers");
            System.out.println("other: exit");
            if (!in.hasNextInt()) {
                System.out.println("Exiting...");
                return;
            }
            int chose = in.nextInt();
            if (chose == 1 || chose == 2) {
                System.out.println("Enter m, n, k and players count");
                int m, n, k, cnt;
                try {
                    m = in.nextInt();
                    n = in.nextInt();
                    k = in.nextInt();
                    cnt = in.nextInt();
                    if (cnt < 2 || cnt > 4) {
                        System.out.println("Players count is incorrect");
                        System.out.println();
                        continue;
                    }
                    if (chose == 1) {
                        Board board = new MnkBoard(m, n, k, cnt);
                        final Game game = new Game(players);
                        final int result = game.play(board);
                        if (result < 0) {
                            System.out.println("Player " + players[(-result) - 1].getName() + " lose");
                        } else if (result > 0) {
                            System.out.println("Player " + players[result - 1].getName() + " win");
                        } else {
                            System.out.println("Draw");
                        }
                        System.out.println();
                    } else {
                        final Tournament tournament = new Tournament(m, n, k, cnt, players);
                        tournament.play();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input is incorrect. Exiting...");
                    return;
                }
            } else if (chose == 3) {
                System.out.println("Enter k and players count");
                int k, cnt;
                try {
                    k = in.nextInt();
                    cnt = in.nextInt();
                    if (cnt < 2 || cnt > 4) {
                        System.out.println("Players count is incorrect");
                        System.out.println();
                        continue;
                    }
                    Board board = new BoardWithBarriers(11, 11, k, cnt);
                    final Game game = new Game(players);
                    final int result = game.play(board);
                    if (result < 0) {
                        System.out.println("Player " + players[(-result) - 1].getName() + " lose");
                    } else if (result > 0) {
                        System.out.println("Player " + players[result - 1].getName() + " win");
                    } else {
                        System.out.println("Draw");
                    }
                    System.out.println();
                } catch (InputMismatchException e) {
                    System.out.println("Input is incorrect. Exiting...");
                    return;
                }

            } else {
                System.out.println("Exiting...");
                return;
            }
        }
    }
}
