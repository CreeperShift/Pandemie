package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveQuarantine;
import project.pandemie.logic.TConstant;

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
                    moveList.add(new MoveQuarantine(TConstant.INITIAL_CITY_QUARANTINE_LENGTH,city.getName()));
                    break;
                }
            }
        }

        return moveList;
    }

    private String identifyBiggestThreat() {

        HashMap<String, Integer> popMap = new HashMap<>();

        for (City city : round.getCityWrapper().getCityList(true)) {
            for (Events e : city.getEvents()) {
                if (e.hasPathogen()) {
                    popMap.put(e.getPathogen().getName(), city.getPopulation().getInfectedPopulation());
                    break;
                }
            }
        }

        String biggestThreat = "";
        int biggest = 0;
        for (Map.Entry<String, Integer> s : popMap.entrySet()) {
            if (s.getValue() > biggest) {
                biggest = s.getValue();
                biggestThreat = s.getKey();
            }
        }
        return biggestThreat;
    }
}
