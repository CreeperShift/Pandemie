package project.pandemie.logic.testing;

import project.pandemie.Main;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.util.UtilHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class MobilityTester {

    Round r1;
    Round r2;

    public void addRound(Round r) {
        if (r1 == null) {
            r1 = r;
        } else {
            r2 = r;
            test();
        }
    }

    private void test() {

        List<City> cityListInfected = r1.getCityWrapper().getCityList(true);
        List<String> connections = new ArrayList<>();
        List<City> citiesToTest = new ArrayList<>();


        for (Events e : r1.getEvents()) {
            if (e.getPathogen() != null) {
                try {
                    Main.debugLog.log(e.getPathogen().toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }


        cityListInfected.forEach(c -> connections.addAll(List.of(c.getConnections())));

        for (City c : r1.getCityWrapper().getCities().values()) {
            AtomicBoolean test = new AtomicBoolean(false);
            connections.forEach(con -> {
                if (c.getName().equalsIgnoreCase(con)) {
                    test.set(true);
                }
            });
            if (!c.isCityInfected() && !test.get()) {
                citiesToTest.add(c);
            }
        }

        List<City> citiesInfected = r2.getCityWrapper().getCityList(true);
        List<City> test = new ArrayList<>();


        for (City c : citiesInfected) {
            for (City b : citiesToTest) {
                if (c.getName().equalsIgnoreCase(b.getName())) {
                    test.add(c);
                }
            }
        }

        List<City> initialCities = new ArrayList<>();

        for (City c : r2.getCityWrapper().getCities().values()) {
            for (City b : cityListInfected) {
                if (c.getName().equalsIgnoreCase(b.getName())) {
                    initialCities.add(c);
                }
            }
        }

        for (City initialCity : initialCities) {
            for (City testCity : test) {
                boolean didInfect = false;
                String path = "";
                for (Events e : initialCity.getEvents()) {
                    if (e.hasPathogen()) {
                        for (Events ev : testCity.getEvents()) {
                            if (ev.hasPathogen()) {
                                if (e.getPathogen().equals(ev.getPathogen())) {
                                    didInfect = true;
                                    path = e.getPathogen().getName();
                                }
                            }
                        }
                    }
                }
                if (didInfect) {
                    try {
                        Main.debugLog.log(initialCity.getName() + " | " + path +  " | " + " ---> " + testCity.getName() + ": " + Math.floor(UtilHelper.haversine(initialCity.getLatitude(), initialCity.getLongitude(), testCity.getLatitude(), testCity.getLongitude())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}