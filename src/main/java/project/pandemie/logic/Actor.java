package project.pandemie.logic;

import project.pandemie.api.ILogic;
import project.pandemie.data.Move;
import project.pandemie.data.Round;

import java.util.ArrayList;
import java.util.List;

public class Actor implements ILogic {
    @Override
    public List<Move> getMoves(Round r) {

        List<Move> list = new ArrayList<>();

        if(r.getRound() == 1){
            list.add(new Move.Builder("closeAirport").withCity("Abuja").withRounds(2).build());
        }

        list.add(new Move.Builder("endRound").build());
        return list;
    }
}
