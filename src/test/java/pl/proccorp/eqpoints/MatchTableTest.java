package pl.proccorp.eqpoints;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.proccorp.eqpoints.general.ScoreTable;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatchTableTest {

    List<Arguments> playerAResults() {
        return List.of(
                Arguments.of(0, "0/0 0/0 0/0"),
                Arguments.of(1, "0/0 0/0 15/0"),
                Arguments.of(2, "0/0 0/0 30/0"),
                Arguments.of(3, "0/0 0/0 40/0"),
                Arguments.of(4, "0/0 1/0 0/0"),
                Arguments.of(5, "0/0 1/0 15/0"),
                Arguments.of(6, "0/0 1/0 30/0"),
                Arguments.of(7, "0/0 1/0 40/0"),
                Arguments.of(8, "0/0 2/0 0/0"),
                Arguments.of(12, "0/0 3/0 0/0"),
                Arguments.of(16, "0/0 4/0 0/0"),
                Arguments.of(20, "0/0 5/0 0/0"),
                Arguments.of(24, "0/0 6/0 0/0"),
                Arguments.of(28, "1/0 0/0 0/0"),
                Arguments.of(56, "2/0 0/0 0/0"),
                Arguments.of(60, "2/0 1/0 0/0")
        );
    }

    List<Arguments> playerBResults() {
        return List.of(
                Arguments.of(0, "0/0 0/0 0/0"),
                Arguments.of(1, "0/0 0/0 0/15"),
                Arguments.of(2, "0/0 0/0 0/30"),
                Arguments.of(3, "0/0 0/0 0/40"),
                Arguments.of(4, "0/0 0/1 0/0"),
                Arguments.of(5, "0/0 0/1 0/15"),
                Arguments.of(6, "0/0 0/1 0/30"),
                Arguments.of(7, "0/0 0/1 0/40"),
                Arguments.of(8, "0/0 0/2 0/0")
        );
    }

    List<Arguments> oneByOneResults() {
        return List.of(
                Arguments.of("A", "0/0 0/0 15/0"),
                Arguments.of("B", "0/0 0/0 15/15"),
                Arguments.of("A", "0/0 0/0 30/15"),
                Arguments.of("B", "0/0 0/0 30/30"),
                Arguments.of("A", "0/0 0/0 40/30"),
                Arguments.of("B", "0/0 0/0 40/40"),
                Arguments.of("A", "0/0 0/0 AD/40"),
                Arguments.of("B", "0/0 0/0 40/40"),
                Arguments.of("A", "0/0 0/0 AD/40"),
                Arguments.of("B", "0/0 0/0 40/40"),
                Arguments.of("A", "0/0 0/0 AD/40")
        );
    }

    private ScoreTable table;
    private ScoreTable reusableScoreTable = new MatchTable();

    @BeforeEach
    void resetTable() {
        table = new MatchTable();
    }

    @ParameterizedTest
    @MethodSource("playerAResults")
    void playerAShouldBeAbleToGetPointsAndGames(int numberOfPointsWonByA, String expectedResult) {
        addPointsForPlayerA(numberOfPointsWonByA);
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("playerBResults")
    void playerBShouldBeAbleToGetPointsAndGames(int numberOfPointsWonByA, String expectedResult) {
        addPointsForPlayerB(numberOfPointsWonByA);
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("oneByOneResults")
    void playerBShouldBeAbleToGetPointsAndGames(String player, String expectedResult) {
        addPointsForPlayer(player);
        Assertions.assertThat(reusableScoreTable.currentScore()).isEqualTo(expectedResult);
    }

    private void addPointsForPlayerA(int numberOfPoints) {
        for (int i = 0; i < numberOfPoints; i++) {
            this.table.playerAWonPoint();
        }
    }

    private void addPointsForPlayerB(int numberOfPoints) {
        for (int i = 0; i < numberOfPoints; i++) {
            this.table.playerBWonPoint();
        }
    }

    private void addPointsForPlayer(String player) {
        if ("A".equals(player)) {
            this.reusableScoreTable.playerAWonPoint();
        } else if ("B".equals(player)) {
            this.reusableScoreTable.playerBWonPoint();
        } else {
            throw new RuntimeException("wrong player");
        }
    }
}