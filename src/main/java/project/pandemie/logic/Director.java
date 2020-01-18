package project.pandemie.logic;

/*
Totally not a reference to the TV Series Travelers
which I have been watching the past few days....
 */

import project.pandemie.api.ILogic;
import project.pandemie.api.IStrategy;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveEndRound;
import project.pandemie.logic.strategy.CreateInitialVaccine;
import project.pandemie.logic.strategy.InitialCityQuarantine;

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

        if (round.getRound() == 1) {
            initialQuarantine();
            return decideAction();
        }

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

    private void initialQuarantine() {
        IStrategy initQuarantine = new InitialCityQuarantine(round);
        potentialMoves.add(initQuarantine.decideMoves());
    }

    /*
    Takes potentialMoves and decides on what to actually do!
     */
    private List<Move> decideAction() {
        List<Move> moveList = new ArrayList<>();
        if (round.getRound() == 1) {
            moveList.add(potentialMoves.get(0).get(0));
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
        if (round.getPoints() >= 40) {
            if (!existVaccine()) {
                IStrategy initialVaccine = new CreateInitialVaccine(round);
                potentialMoves.add(initialVaccine.decideMoves());
            }
        }
    }

    private boolean existVaccine() {
        return round.isDevelopVaccine() || round.haveVaccines();
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
