package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.move.Move;

import java.util.List;

public class StrategySkip implements IStrategy {
    @Override
    public List<Move> decideMoves() {
        return null;
    }
}
