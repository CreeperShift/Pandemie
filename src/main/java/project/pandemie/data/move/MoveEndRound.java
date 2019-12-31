package project.pandemie.data.move;

public class MoveEndRound extends Move{

    private String type = "endRound";
    public MoveEndRound() {
        super(0);
    }

    @Override
    public int getCost() {
        return 0;
    }
}
