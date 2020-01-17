package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;

import java.util.List;

public class DeployVaccine implements IStrategy {

    private final Round round;
    private final int cost = 5;
    private List<Events> vaccineList;

    public DeployVaccine(Round round) {
        this.round = round;
        vaccineList = round.getEventByType("vaccineAvailable");
    }

    @Override
    public List<Move> decideMoves() {

        Events vaccine = vaccineList.get(0);

        if (vaccineList.size() > 1) {
            for (Events e : vaccineList) {
                if (e.getSinceRound() > vaccine.getSinceRound()) {
                    vaccine = e;
                }
            }
        }

        return null;
    }

}
