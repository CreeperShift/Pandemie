package project.pandemie.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreHolderTest {

    static ScoreHolder scoreHolder;

    @BeforeEach
    void setUp() {
        scoreHolder = new ScoreHolder(5);
    }

    @Test
    void getScore() {
        Assertions.assertEquals(5, scoreHolder.getScore());
    }

    @Test
    void setScore() {
        scoreHolder.setScore(10);
        Assertions.assertEquals(10, scoreHolder.getScore());
    }

    @Test
    void addScore() {
        scoreHolder.addScore(5);
        Assertions.assertEquals(10, scoreHolder.getScore());
    }

    @Test
    void subtractScore() {
        scoreHolder.subtractScore(5);
        Assertions.assertEquals(0, scoreHolder.getScore());
    }

}