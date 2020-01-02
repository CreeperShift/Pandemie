package project.pandemie.logic;

import project.pandemie.api.IStrategy;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;

import java.util.List;

public abstract class Strategy implements IStrategy {

    private List<Move> possibleMoves;
    private Round r;
    private List<Move> moveList;

    public Strategy(Round r, List<Move> possibleMoves) {
        this.r = r;
        this.possibleMoves = possibleMoves;
        act();
    }

    abstract void act();

    protected List<Move> getMoveList() {
        return moveList;
    }

    protected List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    protected Round getRound() {
        return r;
    }

    @Override
    public List<Move> decideMoves() {
        return moveList;
    }
}
