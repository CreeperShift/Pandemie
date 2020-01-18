package project.pandemie.data.move;

public abstract class Move {

    private int score;
    int rounds;

    public Move(int rounds) {
        this.rounds = rounds;
    }

    public abstract int getCost();
}
