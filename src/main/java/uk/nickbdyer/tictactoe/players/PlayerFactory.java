package uk.nickbdyer.tictactoe.players;

import uk.nickbdyer.tictactoe.GameType;
import uk.nickbdyer.tictactoe.Player;
import uk.nickbdyer.tictactoe.delayers.ThreadDelayer;

import java.util.Arrays;
import java.util.List;

import static uk.nickbdyer.tictactoe.Mark.O;
import static uk.nickbdyer.tictactoe.Mark.X;

public class PlayerFactory {

    public List<Player> create(GameType type) {
        if (type == GameType.HvsH) {
            return Arrays.asList(new Human(X), new Human(O));
        } else if (type == GameType.HvsAi) {
            return Arrays.asList(new Human(X), slow(new DumbComputer(O)));
        } else if (type == GameType.AivsH) {
            return Arrays.asList(slow(new DumbComputer(X)), new Human(O));
        } else if (type == GameType.AivsAi) {
            return Arrays.asList(slow(new DumbComputer(X)), slow(new DumbComputer(O)));
        } else if (type == GameType.HvsPAi) {
            return Arrays.asList(new Human(X), slow(new PerfectComputer(O)));
        } else if (type == GameType.PAivsH) {
            return Arrays.asList(slow(new PerfectComputer(X)), new Human(O));
        } else if (type == GameType.PAivsPAi) {
            return Arrays.asList(slow(new PerfectComputer(X)), slow(new PerfectComputer(O)));
        } else {
            throw new RuntimeException("That is not a game type");
        }
    }

    private Player slow(Player computer) {
        return new DelayedComputer(computer, new ThreadDelayer(), 1000);
    }

}