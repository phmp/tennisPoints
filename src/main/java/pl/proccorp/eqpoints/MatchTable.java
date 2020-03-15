package pl.proccorp.eqpoints;

import pl.proccorp.eqpoints.general.ScoreTable;
import pl.proccorp.eqpoints.model.Player;
import pl.proccorp.eqpoints.specyfic.GamesTable;
import pl.proccorp.eqpoints.specyfic.PointsTable;
import pl.proccorp.eqpoints.specyfic.SetsTable;
import pl.proccorp.eqpoints.specyfic.TieBreakTable;

import static pl.proccorp.eqpoints.model.Player.A;
import static pl.proccorp.eqpoints.model.Player.B;

public class MatchTable {

    private ScoreTable pointsTable = new PointsTable();
    private ScoreTable gamesTable = new GamesTable();
    private ScoreTable setsTable = new SetsTable();

    private void playerWonPoint(Player player) {
        pointsTable.addPointFor(player);
        if (pointsTable.won(player)) {
            gamesTable.addPointFor(player);
            if ("6/6".equals(gamesTable.currentScore())){
                pointsTable = new TieBreakTable();
            } else {
                pointsTable = new PointsTable();
            }

            if (gamesTable.won(player)) {
                setsTable.addPointFor(player);
                gamesTable = new GamesTable();
            }
        }
    }

    public void playerAWonPoint() {
        playerWonPoint(A);
    }

    public void playerBWonPoint() {
        playerWonPoint(B);
    }

    public String currentScore() {
        return setsTable.currentScore() + " " + gamesTable.currentScore() + " " + pointsTable.currentScore();
    }
}
