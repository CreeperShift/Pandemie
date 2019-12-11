package project.pandemie.logic;

import project.pandemie.api.ILogic;
import project.pandemie.data.Move;
import project.pandemie.data.Round;

import java.util.ArrayList;
import java.util.List;

public class Actor implements ILogic {
    @Override
    public List<Move> getMoves(Round r) {

        List<Move> list = new ArrayList<Move>();
        list.add(new Move.Builder("endRound").build());
        return list;
    }
}
