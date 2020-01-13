package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveDevelopVaccine;

import java.util.List;

public class StrategyDevelopVaccine implements IStrategy {

    private final Round r;
    private final String pathogen;

    public StrategyDevelopVaccine(Round r, String pathogen) {
        this.r = r;
        this.pathogen = pathogen;
    }

    @Override
    public List<Move> decideMoves() {

        if (!isValid()) return null;

        return List.of(new MoveDevelopVaccine(pathogen));
    }

    private boolean isValid() {

        if (r.getPoints() < 40) {
            return false;
        }

        for (Events e : r.getEvents()) {
            if (e.getType().equalsIgnoreCase("vaccineInDevelopment") || e.getType().equalsIgnoreCase("vaccineAvailable")) {
                if (e.hasPathogen()) {
                    if (e.getPathogen().getName().equalsIgnoreCase(pathogen)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
