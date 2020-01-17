package project.pandemie.logic;

/*
Totally not a reference to the TV Series Travelers
which I have been watching the past few days....
 */

import project.pandemie.api.ILogic;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveEndRound;

import java.util.ArrayList;
import java.util.List;

public class Director implements ILogic {

    private List<List<Move>> potentialMoves = new ArrayList<>();
    private final Round round;

    public Director(Round round) {
        this.round = round;
    }

    @Override
    public List<Move> getMoves() {

        actOrSavePoints();
        checkVaccines();
        checkMedication();
        checkCreateVaccines();
        checkCreateMedication();
        checkQuarantine();
        checkCloseConnection();
        checkCloseAirport();
        pushHygiene();
        reactEvents();


        return decideAction();
    }

    /*
    Takes potentialMoves and decides on what to actually do!
     */
    private List<Move> decideAction() {
        List<Move> moveList = new ArrayList<>();
        for (Move m : potentialMoves.get(0)) {
            if (m.getType().equalsIgnoreCase("endRound")) {
                moveList.add(new MoveEndRound());
                return moveList;
            }
        }

        /*
        Always end our list with endRound.
         */
        moveList.add(new MoveEndRound());
        return moveList;
    }

    private void reactEvents() {
    }

    private void pushHygiene() {
    }

    private void checkCloseAirport() {
    }

    private void checkCloseConnection() {
    }

    private void checkQuarantine() {
    }

    private void checkCreateMedication() {
    }

    private void checkCreateVaccines() {
    }

    private void checkMedication() {
        if (round.haveMedication()) {
            //TODO: ADD LOGIC
        }
    }

    private void checkVaccines() {
        if (round.haveVaccines()) {
            //TODO: ADD LOGIC
        }
    }

    private void actOrSavePoints() {
        //TODO:Implement Strategy
    }
}
