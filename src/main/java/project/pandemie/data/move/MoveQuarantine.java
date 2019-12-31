package project.pandemie.data.move;

public class MoveQuarantine extends Move {

    private String type = "putUnderQuarantine";
    private String city;

    private int baseCost = 20;
    private int roundCost = 10;

    public MoveQuarantine(int rounds, String city) {
        super(rounds);
        this.city = city;
    }

    @Override
    public int getCost() {
        return baseCost + (roundCost * rounds);
    }
}
