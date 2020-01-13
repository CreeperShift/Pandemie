package project.pandemie.logic.testing;

import project.pandemie.Main;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.MoveQuarantine;
import project.pandemie.logic.Actor;
import project.pandemie.util.UtilHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MobilityTester {

    Round r1;
    Round r2;
    Actor a;

    public void addRound(Round r) {
        if (r1 == null && r.getRound() == 1) {
            r1 = r;
            setQuarantine();
        } else if (r2 == null && r.getRound() == 2) {
            r2 = r;
            test();

            // System.out.println("connected?: " + isConnected(r2.getCityWrapper().getCities().get("عمان"), r2.getCityWrapper().getCities().get("ירושלים")));
        }
    }

    private void setQuarantine() {

        List<City> citiesInfected = r1.getCityWrapper().getCityList(true);
        System.out.println(citiesInfected.get(2).getName());
        a.addMove(new MoveQuarantine(2, citiesInfected.get(2).getName()));
    }

    public void addActor(Actor a) {
        this.a = a;
    }

    private boolean isConnected(City c1, City c2) {
        if (c1 == c2) {
            return true;
        }
        Queue<City> queue = new LinkedList<>();
        queue.add(c1);

        do {
            for (String con : queue.remove().getConnections()) {
                if (con.equals(c2.getName())) {
                    return true;
                } else {
                    if (r2.getCityWrapper().getCities().get(con).isCityInfected()) {
                        for (Events e : r2.getCityWrapper().getCities().get(con).getEvents()) {
                            for (Events f : c2.getEvents()) {
                                if (f.hasPathogen() && e.hasPathogen() && f.getPathogen() == e.getPathogen()) {
                                    queue.add(r2.getCityWrapper().getCities().get(con));
                                    break;
                                }
                            }
                        }

                    }
                }
            }

        } while (!queue.isEmpty());

        return false;
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
                if (initialCity.hasEvents()) {
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
                }
                if (didInfect) {
                    try {
                        Main.debugLog.log(initialCity.getName() + " | " + path + " | " + " ---> " + testCity.getName() + ": " + Math.floor(UtilHelper.haversine(initialCity.getLatitude(), initialCity.getLongitude(), testCity.getLatitude(), testCity.getLongitude())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}