package project.pandemie.parse;

import com.google.gson.*;
import project.pandemie.api.IParser;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.Move;

import java.util.*;

public class Parser implements IParser {
    @Override
    public Round parseRound(String s) {

        Round round = new Round();
        Gson gson = new Gson(); //GoogleJson to convert between java & json
        JsonObject job = JsonParser.parseString(s).getAsJsonObject();

        round.setOutcome(job.get("outcome").getAsString());
        round.setRound(job.get("round").getAsInt());
        round.setPoints(job.get("points").getAsInt());
        round.setCities(mapCities(job));
        round.setEvents(mapEvents(job));

        return round;
    }

    @Override
    public String parseMove(Move m) {
        return new Gson().toJson(m);
    }

    private Map<String, City> mapCities(JsonObject job){

        Gson gson = new Gson();
        JsonObject cit = job.getAsJsonObject("cities");

        Map<String, JsonObject> map = new HashMap<>();
        Set<Map.Entry<String, JsonElement>> entrySet = cit.entrySet();

        Map<String, City> cityList = new HashMap<>();

        for(Map.Entry<String,JsonElement> entry : entrySet) {
            City c = gson.fromJson(entry.getValue(), City.class);
            cityList.put(c.getName(), c);
        }
        return cityList;
    }

    private Collection<Events> mapEvents(JsonObject job){
        Gson gson = new Gson();

        JsonArray jsonArray = job.getAsJsonArray("events");
        Events[] events = gson.fromJson(jsonArray, Events[].class);

        return Arrays.asList(events);
    }

}
