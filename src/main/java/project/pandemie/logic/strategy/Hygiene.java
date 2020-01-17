package project.pandemie.logic.strategy;

import project.pandemie.api.IStrategy;
import project.pandemie.data.City;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.data.move.MoveHygienic;
import project.pandemie.logic.Protocols;

import java.util.*;

public class Hygiene implements IStrategy {

    Round round;

    public Hygiene(Round round) {

        this.round = round;
    }

    @Override
    public List<Move> decideMoves() {
        ArrayList<City> cityList = new ArrayList<>(round.getCityWrapper().getCities().values());
        cityList.sort(Comparator.comparingInt(City::getValue));

        return getMoveList(cityList);
    }

    List<Move> getMoveList(List<City> cityList) {
        ArrayList<Move> moveList = new ArrayList<>();
        for (City city : cityList) {
            if (city.getHygiene() < Protocols.CITY_HYGIENE_THRESHOLD) {
                moveList.add(new MoveHygienic(city.getName()));
            }
        }
        return moveList;
    }

}
