package project.pandemie.logic;

import project.pandemie.Main;
import project.pandemie.api.ILogic;
import project.pandemie.api.IStrategy;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Pathogen;
import project.pandemie.data.Round;
import project.pandemie.data.move.*;
import project.pandemie.logic.testing.MobilityTester;

import java.util.ArrayList;
import java.util.List;

public class Actor implements ILogic {

    private List<Move> moveList = new ArrayList<>();

    private List<Move> potentialMoves = new ArrayList<>();

    private boolean hasDeadlyPathogen = false;
    private boolean hasMobilePathogen = false;
    private boolean hasInfectiousPathogen = false;
    private boolean hasVaccine = false;
    private boolean hasMedication = false;
    private Round round;

    public Actor(Round round) {
        this.round = round;
    }

    public void addMove(Move m) {
        moveList.add(m);
    }

    @Override
    public List<Move> getMoves() {

        checkPathogens();
        checkCities();
        decideMoves();
        if (round.getRound() == 1) {
            Main.mobilityTester.addActor(this);
            Main.mobilityTester.addRound(round);
        }

        if (round.getRound() == 2) {
            Main.mobilityTester.addRound(round);
        }


//        if(round.getRound() == 3){
//            for(Events e : round.getEvents()){
//                if(e.hasPathogen()){
//                    moveList.add(new MoveDevelopVaccine(e.getPathogen().getName()));
//                    break;
//                }
//            }
//        }

        endRound();
        return moveList;
    }

    private void checkPathogens() {

        for (Pathogen p : round.getPathogens()) {
            if (!hasDeadlyPathogen && p.getLethality() > 1) {
                hasDeadlyPathogen = true;
            }
            if (!hasMobilePathogen && p.getMobility() > 1) {
                hasMobilePathogen = true;
            }
            if (!hasInfectiousPathogen && p.getInfectivity() > 1) {
                hasInfectiousPathogen = true;
            }
        }

    }

    private void checkCities() {

        for (City c : round.getCities().values()) {
            if (c.getScoreHolder().getScore() > TConstant.CITY_SCORE_IGNORE) {
                continue;
            }
            /*
            Look at pathogens & events + modify score should happen in city class
             */


            boolean[] takeAction = new boolean[c.getConnections().length];
            for (int i = 0; i < c.getConnections().length; i++) {
                takeAction[i] = checkConnection(c.getConnections()[i]);
            }
            int count = 0;
            for (boolean b : takeAction) {
                if (b) {
                    count++;
                }
            }

            if (count > TConstant.CONNECTION_CLOSE_AIRPORT) {
                potentialMoves.add(new MoveCloseAirport(TConstant.AIRPORT_CLOSE_ROUNDS, c.getName())); //TODO: Add severity modifier
            } else if (count > 0) {
                for (int i = 0; i < takeAction.length; i++) {
                    if (takeAction[i]) {
                        potentialMoves.add(new MoveCloseConnection(TConstant.CONNECTION_CLOSE_ROUNDS, c.getName(), c.getConnections()[i])); //TODO: Add severity modifier
                    }
                }
            }


        }
    }

    private boolean checkConnection(String city) {

        City c = round.getCities().get(city);
        if (c.hasEvents()) {
            for (Events e : c.getEvents()) {
                if (e.getPathogen() != null && (e.getPathogen().getInfectivity() > TConstant.CONNECTION_INFECTIOUS_PATHOGEN || e.getPathogen().getPathogenScore() > TConstant.CONNECTION_PATHOGEN_THRESHOLD)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void decideMoves() {


    }


    public void endRound() {
        moveList.add(new MoveEndRound());
    }

}
