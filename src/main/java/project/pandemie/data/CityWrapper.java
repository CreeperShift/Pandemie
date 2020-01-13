package project.pandemie.data;

import java.util.*;

public class CityWrapper {

    private HashMap<String, City> cityMap;
    private Population population;
    private int biggestPopulation;
    private int smallestPopulation;

    public CityWrapper(HashMap<String, City> cityMap) {
        this.cityMap = cityMap;
        this.cityMap.forEach((k, v) -> v.process());
        this.population = calculatePopulation();
        this.biggestPopulation = calculateBiggestPopulation();
        this.smallestPopulation = calculateSmallestPopulation();
        scale();
    }

    private void scale() {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (City c : cityMap.values()) {
            if (c.getValue() > max) {
                max = c.getValue();
            }
            if (c.getValue() < min) {
                min = c.getValue();
            }
        }

        cityMap.forEach((k, v) -> {

        });

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

    private int calculateSmallestPopulation() {
        int pop = 0;
        for (City c : cityMap.values()) {
            if (c.getPopulation().size() < pop) {
                pop = c.getPopulation().size();
            }
        }
        return pop;
    }

    public Population getPopulation() {
        return population;
    }

    public int getBiggestPopulation() {
        return biggestPopulation;
    }

    public List<City> getCityList(boolean infected) {
        List<City> col = new ArrayList<>();
        for (City c : cityMap.values()) {
            if (c.isCityInfected() == infected) {
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

        col.sort(Comparator.comparingInt(a -> a.getScoreHolder().getScore()));
        if (descending) {
            col.sort(Collections.reverseOrder());
        }

        return col;
    }

    public int getSmallestPopulation() {
        return smallestPopulation;
    }
}
