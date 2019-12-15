package project.pandemie.util;

import project.pandemie.data.City;

import java.util.Comparator;

public class SortByCityScore implements Comparator<City> {


    @Override
    public int compare(City c1, City c2) {

        return c1.getScore() - c2.getScore();
    }
}
