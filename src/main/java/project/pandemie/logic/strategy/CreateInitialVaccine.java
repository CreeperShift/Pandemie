package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.Events;
import project.pandemie.data.Pathogen;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveDevelopVaccine;

import java.util.ArrayList;
import java.util.List;

public class CreateInitialVaccine implements IStrategy {

    private final Round round;

    public CreateInitialVaccine(Round round) {
        this.round = round;
    }

    @Override
    public List<Move> decideMoves() {
        List<Move> moveList = new ArrayList<>();

        String worstPathogen = findWorstPathogen();
        if (!worstPathogen.isBlank()) {
            moveList.add(new MoveDevelopVaccine(worstPathogen));
        }

        return moveList;
    }

    private String findWorstPathogen() {
        String path = "";
        int score = -4;
        for (Events e : round.getEvents()) {
            if (e.hasPathogen()) {
                Pathogen pathogen = e.getPathogen();
                if (pathogen.getInfectivity() > 0 && pathogen.getLethality() > 1) {
                    return pathogen.getName();
                } else {
                    if (pathogen.getLethality() + pathogen.getInfectivity() > score) {
                        path = pathogen.getName();
                        score = pathogen.getLethality() + pathogen.getInfectivity();
                    }
                }
            }
        }
        return path;
    }
}
