package project.pandemie.logic;

import project.pandemie.api.ILogic;
import project.pandemie.data.Move;
import project.pandemie.data.Round;
import project.pandemie.data.WriterFile;

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

        WriterFile write =new WriterFile("D:/PandemieLog.txt", true);
        try{
            write.writeCity(r.getCities().get("Abuja"));
        }catch(Exception e){
            System.out.print(e);
        }

        //Debug Output
        System.out.println(r.getRound());
        System.out.println(r.getOutcome());
        System.out.println(r.getPoints());
        System.out.println(r.getCities().get("Abuja"));

        list.add(new Move.Builder("endRound").build());
        return list;
    }
}
