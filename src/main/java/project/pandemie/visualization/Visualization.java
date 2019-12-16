package project.pandemie.visualization;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import project.pandemie.data.City;
import project.pandemie.data.Round;

import java.util.ArrayList;

public class Visualization{

    private ArrayList<Round> rounds;

    public void addRound(Round r){
        rounds.add(r);
        if(!r.getOutcome().equals("pending")){
            genVis();
        }
    }



    public Visualization(){
        this.rounds= new ArrayList<Round>();
    }

    private void genVis() {
        ArrayList<City> infected = (ArrayList<City>) rounds.get(rounds.size()-1).getInfectedCities();
        for (City c: infected) {
            genCity(c);
        }
       //genCity(rounds.get(1).getCities().get("Berlin"));
    }

    private void genCity(City c) {
        ArrayList round = new ArrayList();
        ArrayList hygiene = new ArrayList();
        ArrayList economy = new ArrayList();
        ArrayList government = new ArrayList();
        ArrayList awareness = new ArrayList();
        ArrayList pop = new ArrayList();
        ArrayList inf = new ArrayList();
        for (Round r:rounds) {
            round.add(r.getRound());
            City city = r.getCities().get(c.getName());
            hygiene.add(city.getHygiene());
            economy.add(city.getEconomy());
            government.add(city.getGovernment());
            awareness.add(city.getAwareness());
            pop.add(city.getPopulation());
            inf.add(city.getInfectedPopulation());
        }
        disCityAt(round,hygiene,"Hygiene", c.getName());
        disCityAt(round,economy,"Economy", c.getName());
        disCityAt(round,government,"Government", c.getName());
        disCityAt(round,awareness,"Awareness", c.getName());
        disCityPop(round,pop, inf,c.getName());
    }

    private void disCityAt(ArrayList round, ArrayList y, String name, String city){
        XYChart chart = new XYChartBuilder().title(city).yAxisTitle(name).xAxisTitle("Round").build();
        chart.addSeries(name,round,y);
        //XYChart chart = QuickChart.getChart(name,"Round",name,name,round,y);
        new SwingWrapper(chart).displayChart();
    }
    private void disCityPop(ArrayList round, ArrayList pop, ArrayList inf, String city){
        XYChart chart = new XYChartBuilder().title(city).yAxisTitle("Population").xAxisTitle("Round").build();
        chart.addSeries("Population",round,pop);
        chart.addSeries("Infected",round,inf);
        new SwingWrapper(chart).displayChart();
    }
}