package project.pandemie.api;

import project.pandemie.data.Move;
import project.pandemie.data.Round;

import java.util.Collection;

public interface ILogic {

    public Collection<Move> getMoves(Round r);

}
