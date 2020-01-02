package project.pandemie.visualization;

import project.pandemie.api.IVisual;
import project.pandemie.data.Round;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Visualizer {

    private static Visualizer instance = null;

    /*
    Singleton pattern
     */
    private Visualizer() {
    }

    public static Visualizer getInstance() {
        if (instance == null) {
            instance = new Visualizer();
        }
        return instance;
    }

    private boolean newRound = true;

    private static ArrayList<Collection<Round>> roundCollection = new ArrayList<>();

    private static List<IVisual> visualizationList = new ArrayList<>();

    private Collection<Round> rounds;

    public static List<IVisual> getVisualizerList() {
        return visualizationList;
    }

    public void addRound(Round r) {

        if (newRound) {
            rounds = new ArrayList<>();
            rounds.add(r);
            roundCollection.add(rounds);
            newRound = false;
            return;
        }

        if (!r.getOutcome().equalsIgnoreCase("pending")) {
            rounds.add(r);
            newRound = true;
        } else {
            rounds.add(r);
        }
        processVisualization(r);
    }

    private void processVisualization(Round r) {
        visualizationList.forEach(o -> o.visualize(r));
    }

    public static Collection<Collection<Round>> getRoundCollection() {
        return roundCollection;
    }

    public static Collection<Round> getRound(int round) {
        if (round <= roundCollection.size()) {
            return roundCollection.get(round - 1);
        } else {
            return null;
        }
    }


}
