package project.pandemie.visualization;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import project.pandemie.data.City;
import project.pandemie.data.Round;

import java.util.ArrayList;
import java.util.List;

public class Visualization {

    private ArrayList<Round> rounds;

    public void addRound(Round r) {
        rounds.add(r);
        if (!r.getOutcome().equals("pending")) {
            genVis();
        }
    }


    public Visualization() {
        this.rounds = new ArrayList<Round>();
    }

    private void genVis() {
        ArrayList<City> infected = (ArrayList<City>) rounds.get(rounds.size() - 1).getInfectedCitiesbyScore(false);
       /* for (City c: infected) {
            genCity(c);
        }*/
        for (int i = 0; i < infected.size() && i < 10; i++) {
            genCity(infected.get(i));
        }
        //genCity(rounds.get(1).getCities().get("Berlin"));
    }

    private void genCity(City c) {
        ArrayList<Integer> round = new ArrayList<>();
        ArrayList<Integer> hygiene = new ArrayList<>();
        ArrayList<Integer> economy = new ArrayList<Integer>();
        ArrayList<Integer> government = new ArrayList<Integer>();
        ArrayList<Integer> awareness = new ArrayList<>();
        ArrayList<Integer> pop = new ArrayList<>();
        ArrayList<Integer> inf = new ArrayList<>();
        for (Round r : rounds) {
            round.add(r.getRound());
            City city = r.getCities().get(c.getName());
            hygiene.add(city.getHygiene());
            economy.add(city.getEconomy());
            government.add(city.getGovernment());
            awareness.add(city.getAwareness());
            pop.add(city.getPopulation());
            inf.add(city.getInfectedPopulation());
        }
        ArrayList<XYChart> charts = new ArrayList<>();
        charts.add(disCityAt(round, hygiene, "Hygiene", c.getName()));
        charts.add(disCityAt(round, economy, "Economy", c.getName()));
        charts.add(disCityAt(round, government, "Government", c.getName()));
        charts.add(disCityAt(round, awareness, "Awareness", c.getName()));
        charts.add(disCityPop(round, pop, inf, c.getName()));
        new SwingWrapper<XYChart>(charts).displayChartMatrix();

    }

    private XYChart disCityAt(List<Integer> round, List<Integer> y, String name, String city) {
        XYChart chart = new XYChartBuilder().title(city).yAxisTitle(name).xAxisTitle("Round").build();
        chart.addSeries(name, round, y);
        return chart;
        //new SwingWrapper(chart).displayChart();
    }

    private XYChart disCityPop(List<Integer> round, List<Integer> pop, List<Integer> inf, String city) {
        XYChart chart = new XYChartBuilder().title(city).yAxisTitle("Population").xAxisTitle("Round").build();
        chart.addSeries("Population", round, pop);
        chart.addSeries("Infected", round, inf);
        return chart;
    }
}