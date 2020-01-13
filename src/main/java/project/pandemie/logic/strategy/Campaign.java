package project.pandemie.logic.strategy;

import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveCampaign;
import project.pandemie.logic.TConstant;

import java.util.ArrayList;
import java.util.List;

public class Campaign extends Hygiene {

    public Campaign(Round round) {
        super(round);
    }

    @Override
    List<Move> getMoveList(List<City> cityList) {

        ArrayList<Move> moveList = new ArrayList<>();
        for (City city : cityList) {
            var a = false;
            if (city.getAwareness() < TConstant.CITY_AWARENESS_THRESHOLD) {
                if (city.hasEvents()) {
                    for (Events e : city.getEvents()) {
                        if (e.getType().equalsIgnoreCase("antiVaccinationism")) {
                            if (city.getValueScaled() > TConstant.CITY_ANTIVAX_THRESHOLD) {
                                moveList.add(0, new MoveCampaign(city.getName()));
                                a = true;
                            }
                        }
                    }
                }
                if (!a) {
                    moveList.add(new MoveCampaign(city.getName()));
                }
            }
        }

        return moveList;
    }
}
