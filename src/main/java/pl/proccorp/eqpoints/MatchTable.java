package pl.proccorp.eqpoints;

import pl.proccorp.eqpoints.general.ScoreTable;
import pl.proccorp.eqpoints.model.Player;
import pl.proccorp.eqpoints.specyfic.GamesTable;
import pl.proccorp.eqpoints.specyfic.PointsTable;
import pl.proccorp.eqpoints.specyfic.SetsTable;

import static pl.proccorp.eqpoints.model.Player.A;
import static pl.proccorp.eqpoints.model.Player.B;

public class MatchTable {

    private ScoreTable pointsTable = new PointsTable();
    private ScoreTable gamesTable = new GamesTable();
    private ScoreTable setsTable = new SetsTable();

    private void playerWonPoint(Player player) {
        pointsTable.addPointFor(player);
        if (pointsTable.won(player)) {
            pointsTable = new PointsTable();

            gamesTable.addPointFor(player);
            if (gamesTable.won(player)) {
                gamesTable = new GamesTable();

                setsTable.addPointFor(player);
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
