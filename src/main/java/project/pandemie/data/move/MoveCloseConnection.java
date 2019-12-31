package project.pandemie.data.move;

public class MoveCloseConnection extends Move{

    private String type = "closeConnection";
    private String fromCity;
    private String toCity;

    private int baseCost = 3;
    private int roundCost = 3;

    public MoveCloseConnection(int rounds, String fromCity, String toCity) {
        super(rounds);
        this.fromCity = fromCity;
        this.toCity = toCity;
    }

    @Override
    public int getCost() {
        return baseCost + (roundCost * rounds);
    }

}
