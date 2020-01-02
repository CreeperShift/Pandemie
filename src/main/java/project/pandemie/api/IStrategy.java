package project.pandemie.api;

import project.pandemie.data.move.Move;

import java.util.List;

public interface IStrategy {

    List<Move> decideMoves();

}
