package com.company;

import java.io.PrintStream;
import java.util.Scanner;

import static com.company.Player.Mark.EMPTY;

public class UserInterface {

    private static final String BOARDTEMPLATE = " # | # | # \n"
                                              + "---|---|---\n"
                                              + " # | # | # \n"
                                              + "---|---|---\n"
                                              + " # | # | # \n";
    private final Scanner input;
    private final PrintStream output;

    public UserInterface(Scanner input, PrintStream output) {
        this.input = input;
        this.output = output;
    }

    public void showBoard(Board board) {
        String liveboard = BOARDTEMPLATE;
        for (int i = 0; i < 9; i++) {
            if (board.getCells().get(i) == EMPTY) {
                liveboard = liveboard.replaceFirst("#", Integer.toString(i + 1));
            } else {
                liveboard = liveboard.replaceFirst("#", String.valueOf(board.getCells().get(i)));
            }
        }
        output.println(liveboard);
    }

    public void processMark(Board board, Player.Mark mark) {
        int validatedNumber = getValidPosition(board);
        board.mark(validatedNumber, mark);
    }

    private int getValidPosition(Board board) {
        output.println("Please choose a number between 1-9");
        while (true) {
            int position = getNumber();
            if (outOfBounds(position) || alreadyMarked(board, position))
                continue;
            return position - 1;
        }
    }

    private boolean outOfBounds(int number) {
        if (!isPositionInBounds(number)) {
            output.println("That is not a valid position");
            return true;
        }
        return false;
    }

    private boolean alreadyMarked(Board board, int number) {
        if (!isPositionMarkable(number - 1, board)) {
            output.println("That cell is already marked, try again");
            return true;
        }
        return false;
    }

    private boolean isPositionInBounds(int number) {
        return (number >= 1 && number <= 9);
    }

    private boolean isPositionMarkable(int number, Board board) {
        return (board.isEmptyCell(number));
    }

    private int getNumber() {
        while (!input.hasNextInt()) {
            output.println("That is not a valid input");
            input.next();
        }
        return input.nextInt();
    }

    public void announceWinner(Board board) {
        String mark = String.valueOf(board.getWinningMark());
        output.println(mark + " has won!");
    }

    public void announceDraw() {
        output.println("It's a Draw!");
    }

    public void displayComputerPlayingMessage() {
        output.println("The computer player is thinking...");
    }

    public void showMenu() {
        output.print("Please choose the game type:\n 1) Human vs Human \n");
    }
}
