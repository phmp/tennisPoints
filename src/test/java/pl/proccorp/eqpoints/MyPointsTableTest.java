package pl.proccorp.eqpoints;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MyPointsTableTest {

    @Test
    void initScoreShouldBeCorrect() {
        PointsTable table = new MyPointsTable();
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo("0/0 0/0 0/0");
    }

    @Test
    void score15() {
        PointsTable table = new MyPointsTable();
        table.playerAWonPoint();
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo("0/0 0/0 15/0");
    }

    @Test
    void score30() {
        PointsTable table = new MyPointsTable();
        table.playerAWonPoint();
        table.playerAWonPoint();
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo("0/0 0/0 30/0");
    }

    @Test
    void score40() {
        PointsTable table = new MyPointsTable();
        table.playerAWonPoint();
        table.playerAWonPoint();
        table.playerAWonPoint();
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo("0/0 0/0 40/0");
    }

    @Test
    void winGame() {
        PointsTable table = new MyPointsTable();
        table.playerAWonPoint();
        table.playerAWonPoint();
        table.playerAWonPoint();
        table.playerAWonPoint();
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo("0/0 1/0 0/0");
    }
}