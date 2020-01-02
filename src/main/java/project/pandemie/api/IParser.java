package project.pandemie.api;

import project.pandemie.data.Round;
import project.pandemie.data.move.Move;

public interface IParser {


    public Round parseRound(String s);

    public String parseMove(Move m);

}
