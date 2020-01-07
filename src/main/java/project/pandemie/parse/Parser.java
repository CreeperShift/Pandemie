package project.pandemie.parse;

import com.google.gson.*;
import project.pandemie.Main;
import project.pandemie.api.IParser;
import project.pandemie.data.City;
import project.pandemie.data.CityWrapper;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;

import java.util.*;

public class Parser implements IParser {
    @Override
    public Round parseRound(String s) {

        JsonObject job = JsonParser.parseString(s).getAsJsonObject();

        String outcome = job.get("outcome").getAsString();
        int round = job.get("round").getAsInt();
        int points = job.get("points").getAsInt();
        HashMap<String, City> cities = mapCities(job);
        Collection<Events> events = mapEvents(job);

        return new Round.Builder(round, outcome).withPoints(points).withCities(new CityWrapper(cities)).withEvents(events).build();
    }

    @Override
    public String parseMove(Move m) {
        return new Gson().toJson(m);
    }

    private HashMap<String, City> mapCities(JsonObject job) {

        Gson gson = new Gson();
        JsonObject cit = job.getAsJsonObject("cities");

        Set<Map.Entry<String, JsonElement>> entrySet = cit.entrySet();

        HashMap<String, City> cityList = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : entrySet) {
            City c = gson.fromJson(entry.getValue(), City.class);
            cityList.put(c.getName(), c);
        }
        return cityList;
    }

    private Collection<Events> mapEvents(JsonObject job) {
        Gson gson = new Gson();

        JsonArray jsonArray = job.getAsJsonArray("events");

        Events[] events = gson.fromJson(jsonArray, Events[].class);

        return Arrays.asList(events);
    }

}
