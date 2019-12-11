package project.pandemie.api;

import project.pandemie.data.Move;
import project.pandemie.data.Round;

import java.util.List;

public interface ILogic {

    public List<Move> getMoves(Round r);

}
