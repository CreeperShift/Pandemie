package project.pandemie.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class CityWrapperTest {

    static CityWrapper cityWrapper;

    @BeforeEach
    void setUp() {

        Pathogen pathogen = new Pathogen.Builder().setName("TestPathogen").setDuration("-").setInfectivity("--").setLethality("o").setMobility("++").build();
        Pathogen pathogen2 = new Pathogen.Builder().setName("TestPathogen2").setDuration("--").setInfectivity("-").setLethality("++").setMobility("+").build();

        Events events = new Events.Builder().setType("outbreak").setPathogen(pathogen).setSinceRound(5).setPrevalence(0.1d).build();
        Events events2 = new Events.Builder().setType("outbreak").setPathogen(pathogen2).setSinceRound(1).setPrevalence(0.2d).build();

        City a = new City.Builder().setName("Berlin")
                .setLatitude(500).setLongitude(200).setPopulation(500).setConnections(new String[]{"Paris", "Rome"}).
                        setEconomy("--").setGovernment("++").setHygiene("o").setAwareness("-").setEvents(null).build();

        City b = new City.Builder().setName("Rome")
                .setLatitude(200).setLongitude(500).setPopulation(100).setConnections(new String[]{"Paris", "Berlin"}).
                        setEconomy("-").setGovernment("+").setHygiene("++").setAwareness("o").setEvents(new Events[]{events, events2}).build();

        HashMap<String, City> cityMap = new HashMap<>();
        cityMap.put(a.getName(), a);
        cityMap.put(b.getName(), b);

        cityWrapper = new CityWrapper(cityMap);
    }

    @Test
    void getCities() {
        Assertions.assertTrue(cityWrapper.getCities().containsKey("Berlin"));
        Assertions.assertTrue(cityWrapper.getCities().containsKey("Rome"));
    }

    @Test
    void hasCities() {
        Assertions.assertFalse(cityWrapper.getCities().isEmpty());
    }

    @Test
    void getWorldPopulation() {
        Assertions.assertEquals(cityWrapper.getPopulation().size(), 600);
    }

    @Test
    void getBiggestPopulation() {
        Assertions.assertEquals(cityWrapper.getBiggestPopulation(), 500);
    }

    @Test
    void getInfectedCities() {
        Assertions.assertEquals(cityWrapper.getCityList(true).get(0).getName(), "Rome");
    }

    @Test
    void getCityByScore() {
        //TODO: Write this stupid test
    }
}