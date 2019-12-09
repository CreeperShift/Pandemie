package project.pandemie.api;

import project.pandemie.data.Move;
import project.pandemie.data.Round;

public interface IParser {


    public Round parseRound(String s);

    public String parseMove(Move m);

}
