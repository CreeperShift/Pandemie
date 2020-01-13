package project.pandemie.logic.strategy;

import project.pandemie.data.City;
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
            if (city.getAwareness() < TConstant.CITY_AWARENESS_THRESHOLD) {
                moveList.add(new MoveCampaign(city.getName()));
            }
        }

        return moveList;
    }
}
