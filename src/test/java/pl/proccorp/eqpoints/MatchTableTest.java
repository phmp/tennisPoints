package pl.proccorp.eqpoints;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.proccorp.eqpoints.general.ScoreTable;

import java.util.List;

class MatchTableTest {

    static List<Arguments> playerAResults() {
        return List.of(
                Arguments.of(0, "0/0 0/0 0/0"),
                Arguments.of(1, "0/0 0/0 15/0"),
                Arguments.of(2, "0/0 0/0 30/0"),
                Arguments.of(3, "0/0 0/0 40/0"),
                Arguments.of(4, "0/0 1/0 0/0"),
                Arguments.of(5, "0/0 1/0 15/0"),
                Arguments.of(6, "0/0 1/0 30/0"),
                Arguments.of(7, "0/0 1/0 40/0"),
                Arguments.of(8, "0/0 2/0 0/0")
        );
    }

    static List<Arguments> playerBResults() {
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

    private ScoreTable table;

    @ParameterizedTest
    @MethodSource("playerAResults")
    void playerAShouldBeAbleToGetPointsAndGames(int numberOfPointsWonByA, String expectedResult) {
        table = new MatchTable();
        addPointsForPlayerA(numberOfPointsWonByA);
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @MethodSource("playerBResults")
    void playerBShouldBeAbleToGetPointsAndGames(int numberOfPointsWonByA, String expectedResult) {
        table = new MatchTable();
        addPointsForPlayerA(numberOfPointsWonByA);
        String score = table.currentScore();
        Assertions.assertThat(score).isEqualTo(expectedResult);
    }

    private void addPointsForPlayerA(int numberOfPoints) {
        for (int i = 0; i < numberOfPoints; i++) {
            this.table.playerAWonPoint();
        }
    }
}