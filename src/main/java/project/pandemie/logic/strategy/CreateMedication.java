package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.Events;
import project.pandemie.data.Pathogen;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveDevelopMedication;
import project.pandemie.util.UtilHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateMedication implements IStrategy {

    private final Round round;

    public CreateMedication(Round round) {
        this.round = round;
    }

    @Override
    public List<Move> decideMoves() {
        List<Move> moveList = new ArrayList<>();
        List<Pathogen> p = round.getPathogens();

        for (Events e : round.getEvents()) {
            if (e.getType().equalsIgnoreCase("medicationInDevelopment") || e.getType().equalsIgnoreCase("medicationAvailable")) {
                p.removeIf(pa -> e.getPathogen().getName().equalsIgnoreCase(pa.getName()));
            }
        }


        if (!p.isEmpty()) {
            Pathogen path = checkPathogens(p);


            if (path.getDuration() < 0 && p.size() > 1) {

                p.remove(path);
                path = checkPathogens(p);
            }
            moveList.add(new MoveDevelopMedication(path.getName()));
        }
        return moveList;
    }

    private Pathogen checkPathogens(List<Pathogen> list) {

        Pathogen worst = null;

        for (Pathogen p : list) {
            if (worst == null) {
                worst = p;
            } else {
                if (UtilHelper.calculateBiggestThreat(p, worst, new String[]{"infectivity", "lethality", "duration", "mobility"}, false).equalsIgnoreCase(p.getName())) {
                    worst = p;
                }
            }
        }
        return worst;
    }


}
