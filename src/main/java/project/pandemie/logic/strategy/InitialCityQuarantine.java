package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Pathogen;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveQuarantine;
import project.pandemie.logic.Protocols;

import java.util.*;

public class InitialCityQuarantine implements IStrategy {

    private Round round;

    public InitialCityQuarantine(Round round) {
        this.round = round;
    }

    @Override
    public List<Move> decideMoves() {
        List<Move> moveList = new ArrayList<>();

        String pathogen = identifyBiggestThreat();

        for (City city : round.getCityWrapper().getCityList(true)) {
            for (Events e : city.getEvents()) {
                if (e.hasPathogen() && e.getPathogen().getName().equalsIgnoreCase(pathogen)) {
                    moveList.add(new MoveQuarantine(Protocols.INITIAL_CITY_QUARANTINE_LENGTH, city.getName()));
                    break;
                }
            }
        }

        return moveList;
    }

    private String identifyBiggestThreat() {
        String biggestThreat = "";

        List<Events> pathogens = round.getEventByType("pathogenEncountered");

        List<Events> lowDuration = new ArrayList<>();
        Events threat = null;

        for (Events e : pathogens) {
            if (e.getPathogen().getDuration() < 0) {
                lowDuration.add(e);
            } else {
                if (threat == null) {
                    threat = e;
                } else if (comparePathogen(e.getPathogen(), threat.getPathogen())) {
                    threat = e;
                }
            }

            if (lowDuration.isEmpty()) {
                return threat.getPathogen().getName();
            }

        }
        Events lowMob = null;

        for (Events e : lowDuration) {

            if (lowMob == null) {
                lowMob = e;
            } else {
                if (comparePathogenMobility(e.getPathogen(), lowMob.getPathogen())) {
                    lowMob = e;
                }
            }
        }


        return lowMob.getPathogen().getName();
    }

    private boolean comparePathogen(Pathogen e1, Pathogen e2) {
        boolean isBetter = false;
        boolean lethal = false;

        if (e1.getLethality() > e2.getLethality()) {
            isBetter = true;
        }
        if (e1.getMobility() > e2.getMobility()) {
            isBetter = false;
        }
        if (e1.getInfectivity() > e2.getInfectivity()) {
            isBetter = false;
        }
        if (e1.getLethality() - e2.getLethality() > 1) {
            lethal = true;
        }
        if (lethal) {
            return true;
        }
        return isBetter;
    }

    private boolean comparePathogenMobility(Pathogen e1, Pathogen e2) {
        boolean isBetter = false;

        if (e1.getLethality() > e2.getLethality()) {
            isBetter = true;
        }
        if (e1.getInfectivity() - e2.getInfectivity() < 0) {
            isBetter = false;
        }
        return isBetter;
    }

}
