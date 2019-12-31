package project.pandemie.data.move;

public class MoveCloseAirport extends Move{

    private String type = "closeAirport";
    private String city;

    private int baseCost = 15;
    private int roundCost = 5;

    public MoveCloseAirport(int rounds, String city) {
        super(rounds);
        this.city = city;
    }

    @Override
    public int getCost() {
        return baseCost + (roundCost * rounds);
    }
}
