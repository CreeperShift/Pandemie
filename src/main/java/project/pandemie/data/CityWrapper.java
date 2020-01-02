package project.pandemie.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class CityWrapper {

    public HashMap<String, City> cityMap;
    private Population worldPopulation;
    private int biggestPopulation;

    public CityWrapper(HashMap<String, City> cityMap) {
        this.cityMap = cityMap;
        this.cityMap.forEach((k, v) -> v.process());
        this.worldPopulation = calculatePopulation();
        this.biggestPopulation = calculateBiggestPopulation();
    }

    /*
    Calculate world population
    */
    private Population calculatePopulation() {
        Population p = new Population();
        cityMap.forEach((k, v) -> p.addPopulation(v.getPopulation()));

        return p;
    }

    public HashMap<String, City> getCities() {
        return cityMap;
    }


    private int calculateBiggestPopulation() {
        int pop = 0;
        for (City c : cityMap.values()) {
            if (c.getPopulation().size() > pop) {
                pop = c.getPopulation().size();
            }
        }
        return pop;
    }

    public Population getWorldPopulation() {
        return worldPopulation;
    }

    public int getBiggestPopulation() {
        return biggestPopulation;
    }


    public Collection<City> getInfectedCities() {
        Collection<City> col = new ArrayList<>();
        for (City c : cityMap.values()) {
            if (c.isCityInfected()) {
                col.add(c);
            }
        }
        return col;
    }

    /*
    TODO: Rewrite this monstrosity & move to ScoreHolder?.
     */
    public Collection<City> getCityByScore(boolean descending) {
        for (City c : cityMap.values()) {

            /*
            Smaller cities are less important
             */
            if (c.getPopulation().size() < getBiggestPopulation() * 0.6) {
                c.getScoreHolder().setScore(c.getScoreHolder().getScore() + 1);
            } else if (c.getPopulation().size() < getBiggestPopulation() * 0.3) {
                c.getScoreHolder().setScore(c.getScoreHolder().getScore() + 2);
            }

            /*
            Cities that are not infected yet are more important
             */
            if (!c.isCityInfected()) {
                c.getScoreHolder().setScore(c.getScoreHolder().getScore() - 2);
            } else {
                /*
                Cities with low infection % are more at risk
                 */
                float infected = (((float) c.getPopulation().getInfectedPopulation()) / ((float) c.getPopulation().size()) * 100f);
                if (infected < 20) {
                    c.getScoreHolder().setScore(c.getScoreHolder().getScore() - 2);
                }
            }
        }

        ArrayList<City> col = new ArrayList<>(cityMap.values());

        col.sort((a, b) -> a.getScoreHolder().getScore() - b.getScoreHolder().getScore());
        if (descending) {
            col.sort(Collections.reverseOrder());
        }

        return col;
    }

}
