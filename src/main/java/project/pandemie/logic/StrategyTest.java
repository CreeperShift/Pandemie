package project.pandemie.logic;

import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveEndRound;

import java.util.List;

public class StrategyTest extends Strategy {


    public StrategyTest(Round r, List<Move> possibleMoves) {
        super(r, possibleMoves);
    }

    @Override
    void act() {
        getMoveList().add(new MoveEndRound());
    }
}
