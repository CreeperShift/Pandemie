package project.pandemie.logic;

import project.pandemie.api.ILogic;
import project.pandemie.data.Move;
import project.pandemie.data.Round;

import java.util.ArrayList;
import java.util.List;

public class Actor implements ILogic {

    @Override
    public List<Move> getMoves(Round r) {

        //TODO: Replace DUMMY logic

        List<Move> list = new ArrayList<>();

        if(r.getRound() == 1){
            list.add(new Move.Builder("closeAirport").withCity("Abuja").withRounds(2).build());
        }

        //Debug Output
        System.out.println(r);
        System.out.println(r.getCityByScore(false));

        list.add(new Move.Builder("endRound").build());
        return list;
    }
}
