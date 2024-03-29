package pl.proccorp.eqpoints;

import pl.proccorp.eqpoints.general.ScoreTable;
import pl.proccorp.eqpoints.model.Player;
import pl.proccorp.eqpoints.specyfic.*;

import static pl.proccorp.eqpoints.model.Player.A;
import static pl.proccorp.eqpoints.model.Player.B;

public class MatchTable {

    private ScoreTable pointsTable = new PointsTable();
    private ScoreTable gamesTable = new GamesInRegularSetTable();
    private ScoreTable setsTable = new SetsTable();

    public void playerAWonPoint() {
        playerWonPoint(A);
    }

    public void playerBWonPoint() {
        playerWonPoint(B);
    }

    private void playerWonPoint(Player player) {
        pointsTable.addOneFor(player);
        if (pointsTable.won(player)) {
            gamesTable.addOneFor(player);
            resetPointsTable();
            if (gamesTable.won(player)) {
                setsTable.addOneFor(player);
                resetGameTable();
            }
        }
    }

    private void resetGameTable() {
        if("2/2".equals(setsTable.currentScore())){
            gamesTable = new GamesInLastSetTable();
        } else {
            gamesTable = new GamesInRegularSetTable();
        }
    }

    private void resetPointsTable() {
        if ("2/2".equals(setsTable.currentScore()) && "12/12".equals(gamesTable.currentScore())) {
            pointsTable = new TieBreakTable();
        } else if (!"2/2".equals(setsTable.currentScore()) && "6/6".equals(gamesTable.currentScore())) {
            pointsTable = new TieBreakTable();
        } else {
            pointsTable = new PointsTable();
        }
    }

    public String currentScore() {
        return setsTable.currentScore() + " " + gamesTable.currentScore() + " " + pointsTable.currentScore();
    }
}
