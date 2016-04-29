package com.company.players;

import com.company.GameType;
import com.company.Player;

import java.util.Arrays;
import java.util.List;

import static com.company.GameType.*;
import static com.company.Mark.O;
import static com.company.Mark.X;

public class PlayerFactory {

    public List<Player> create(GameType type) {
        if (type == HvsH) {
            return Arrays.asList(new Human(X), new Human(O));
        } else if (type == HvsAi) {
            return Arrays.asList(new Human(X), new DumbComputer(O));
        } else if (type == AivsH) {
            return Arrays.asList(new DumbComputer(X), new Human(O));
        } else if (type == AivsAi) {
            return Arrays.asList(new DumbComputer(X), new DumbComputer(O));
        } else if (type == HvsPAi) {
            return Arrays.asList(new Human(X), new PerfectComputer(O));
        } else if (type == PAivsH) {
            return Arrays.asList(new PerfectComputer(X), new Human(O));
        } else if (type == PAivsPAi) {
            return Arrays.asList(new PerfectComputer(X), new PerfectComputer(O));
        } else {
            throw new RuntimeException("That is not a game type");
        }
    }

}