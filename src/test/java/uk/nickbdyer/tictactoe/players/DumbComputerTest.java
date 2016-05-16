package uk.nickbdyer.tictactoe.players;

import org.junit.Before;
import org.junit.Test;
import uk.nickbdyer.tictactoe.Board;
import uk.nickbdyer.tictactoe.UserInterface;
import uk.nickbdyer.tictactoe.exceptions.BoardUnplayableException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static uk.nickbdyer.tictactoe.Mark.*;
import static uk.nickbdyer.tictactoe.helpers.BoardHelper.createDrawnBoard;
import static uk.nickbdyer.tictactoe.helpers.BoardHelper.setUpBoard;

public class DumbComputerTest {

    private DumbComputer hal9000;
    private Board board;
    private UserInterface ui;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        ui = new UserInterface(new Scanner(""), new PrintStream(outContent));
        hal9000 = new DumbComputer(O, ui);
        board = new Board();
    }

    @Test
    public void hasMark() {
        assertEquals(O, hal9000.getMark());
    }

    @Test
    public void returnsMoveChoice() {
        setUpBoard(Arrays.asList(X, X, O, X, X, O, O, O, EMPTY), board);
        assertEquals(8, hal9000.choosePosition(board));
    }

    @Test(expected=BoardUnplayableException.class)
    public void throwsExceptionIfBoardIsFull() {
        createDrawnBoard(board);
        hal9000.choosePosition(board);
    }

}