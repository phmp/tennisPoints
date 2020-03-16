package pl.proccorp.eqpoints;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
                Arguments.of(24, "1/0 0/0 0/0"),
                Arguments.of(48, "2/0 0/0 0/0"),
                Arguments.of(52, "2/0 1/0 0/0")
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
                Arguments.of(8, "0/0 0/2 0/0"),
                Arguments.of(12, "0/0 0/3 0/0"),
                Arguments.of(16, "0/0 0/4 0/0"),
                Arguments.of(20, "0/0 0/5 0/0"),
                Arguments.of(24, "0/1 0/0 0/0"),
                Arguments.of(48, "0/2 0/0 0/0"),
                Arguments.of(52, "0/2 0/1 0/0")
        );
    }

    @ParameterizedTest
    @MethodSource("playerAResults")
    void playerAShouldBeAbleToGetPointsAndGames(int numberOfPointsWonByA, String expectedScore) {
        MatchTable table = new MatchTable();
        repeat(table::playerAWonPoint, numberOfPointsWonByA);
        String currentScore = table.currentScore();
        assertThat(currentScore).isEqualTo(expectedScore);
    }

    @ParameterizedTest
    @MethodSource("playerBResults")
    void playerBShouldBeAbleToGetPointsAndGames(int numberOfPointsWonByB, String expectedScore) {
        MatchTable table = new MatchTable();
        repeat(table::playerBWonPoint, numberOfPointsWonByB);
        String currentScore = table.currentScore();
        assertThat(currentScore).isEqualTo(expectedScore);
    }

    @Test
    void toWinGameYouNeed2PointsAdvantage() {
        MatchTable table = new MatchTable();
        assertThat(table.currentScore()).isEqualTo("0/0 0/0 0/0");

        addPointAndCheckScore(table, "A", "0/0 0/0 15/0");
        addPointAndCheckScore(table, "B", "0/0 0/0 15/15");
        addPointAndCheckScore(table, "A", "0/0 0/0 30/15");
        addPointAndCheckScore(table, "B", "0/0 0/0 30/30");
        addPointAndCheckScore(table, "A", "0/0 0/0 40/30");
        addPointAndCheckScore(table, "B", "0/0 0/0 40/40");
        addPointAndCheckScore(table, "A", "0/0 0/0 AD/40");
        addPointAndCheckScore(table, "B", "0/0 0/0 40/40");
        addPointAndCheckScore(table, "A", "0/0 0/0 AD/40");
        addPointAndCheckScore(table, "B", "0/0 0/0 40/40");
        addPointAndCheckScore(table, "A", "0/0 0/0 AD/40");
        addPointAndCheckScore(table, "A", "0/0 1/0 0/0");
    }

    @Test
    void lastSetShouldBePlayedLonger() {
        MatchTable table = new MatchTable();
        addSetForPlayerA(table);
        addSetForPlayerB(table);
        addSetForPlayerA(table);
        addSetForPlayerB(table);
        assertThat(table.currentScore()).isEqualTo("2/2 0/0 0/0");
        addSixGamesForBothPlayers(table);
        assertThat(table.currentScore()).isEqualTo("2/2 6/6 0/0");

        addPointAndCheckScore(table, "A", "2/2 6/6 15/0");
        addPointAndCheckScore(table, "A", "2/2 6/6 30/0");
        addPointAndCheckScore(table, "A", "2/2 6/6 40/0");
        addPointAndCheckScore(table, "A", "2/2 7/6 0/0");

        addPointAndCheckScore(table, "B", "2/2 7/6 0/15");
        addPointAndCheckScore(table, "B", "2/2 7/6 0/30");
        addPointAndCheckScore(table, "B", "2/2 7/6 0/40");
        addPointAndCheckScore(table, "B", "2/2 7/7 0/0");
    }

    private void addSetForPlayerA(MatchTable table) {
        repeat(()->addGameForPlayerA(table),6);
    }

    private void addSetForPlayerB(MatchTable table) {
        repeat(()->addGameForPlayerB(table),6);
    }

    @Test
    void onState_6to6_tiebreakShouldBePlayed() {
        MatchTable table = new MatchTable();
        addSixGamesForBothPlayers(table);
        assertThat(table.currentScore()).isEqualTo("0/0 6/6 0/0");

        addPointAndCheckScore(table, "A", "0/0 6/6 1/0");
        addPointAndCheckScore(table, "A", "0/0 6/6 2/0");
        addPointAndCheckScore(table, "B", "0/0 6/6 2/1");
        addPointAndCheckScore(table, "A", "0/0 6/6 3/1");
        addPointAndCheckScore(table, "A", "0/0 6/6 4/1");
        addPointAndCheckScore(table, "A", "0/0 6/6 5/1");
        addPointAndCheckScore(table, "A", "0/0 6/6 6/1");
        addPointAndCheckScore(table, "A", "1/0 0/0 0/0");
    }

    @Test
    void toWinTieBreak2PointAdvantageAreNeeded() {
        MatchTable table = new MatchTable();
        addSixGamesForBothPlayers(table);
        repeat(table::playerAWonPoint, 6);
        repeat(table::playerBWonPoint, 6);
        assertThat(table.currentScore()).isEqualTo("0/0 6/6 6/6");

        addPointAndCheckScore(table, "A", "0/0 6/6 7/6");
        addPointAndCheckScore(table, "B", "0/0 6/6 7/7");
        addPointAndCheckScore(table, "A", "0/0 6/6 8/7");
        addPointAndCheckScore(table, "B", "0/0 6/6 8/8");
        addPointAndCheckScore(table, "B", "0/0 6/6 8/9");
        addPointAndCheckScore(table, "B", "0/1 0/0 0/0");
    }

    private void addPointAndCheckScore(MatchTable table, String player, String expectedResult) {
        addPointsForPlayer(table, player);
        assertThat(table.currentScore()).isEqualTo(expectedResult);
    }

    private void addSixGamesForBothPlayers(MatchTable table) {
        repeat(() -> {
            addGameForPlayerA(table);
            addGameForPlayerB(table);
        }, 6);
    }

    private void addGameForPlayerB(MatchTable table) {
        repeat(table::playerBWonPoint, 4);
    }

    private void addGameForPlayerA(MatchTable table) {
        repeat(table::playerAWonPoint, 4);
    }

    void repeat(Runnable action, int numberOfTimes) {
        for (int i = 0; i < numberOfTimes; i++) {
            action.run();
        }
    }

    private void addPointsForPlayer(MatchTable table, String player) {
        if ("A".equals(player)) {
            table.playerAWonPoint();
        } else if ("B".equals(player)) {
            table.playerBWonPoint();
        } else {
            throw new RuntimeException("wrong player");
        }
    }
}