package project.pandemie.data.move;

/*
This class is not really used to send a command
but to know when one of our Strategies wants to not do anything.
 */

public class MoveSkip extends Move {

    public MoveSkip() {
        super(0);
    }

    @Override
    public int getCost() {
        return 0;
    }
}
