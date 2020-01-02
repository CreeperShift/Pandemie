package project.pandemie.data;

import project.pandemie.api.IScore;

public class ScoreHolder implements IScore {

    private int score;

    public ScoreHolder(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public void subtractScore(int score) {
        this.score -= score;
    }
}
