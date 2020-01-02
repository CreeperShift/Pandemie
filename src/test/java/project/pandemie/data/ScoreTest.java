package project.pandemie.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    static Score score;

    @BeforeEach
    void setUp() {
        score = new Score(5);
    }

    @Test
    void getScore() {
        Assertions.assertEquals(5, score.getScore());
    }

    @Test
    void setScore() {
        score.setScore(10);
        Assertions.assertEquals(10, score.getScore());
    }

    @Test
    void addScore() {
        score.addScore(5);
        Assertions.assertEquals(10, score.getScore());
    }

    @Test
    void subtractScore() {
        score.subtractScore(5);
        Assertions.assertEquals(0, score.getScore());
    }

}