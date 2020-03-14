package pl.proccorp.eqpoints;

import pl.proccorp.eqpoints.general.ScoreTable;
import pl.proccorp.eqpoints.specyfic.PointsTable;
import pl.proccorp.eqpoints.specyfic.GamesTable;

public class MatchTable implements ScoreTable {

    private ScoreTable pointsTable = new PointsTable();
    private ScoreTable gamesTable = new GamesTable();

    public boolean playerAWonPoint() {
        if (pointsTable.playerAWonPoint()) {
            gamesTable.playerAWonPoint();
            pointsTable = new PointsTable();
        }
        return false;
    }

    public boolean playerBWonPoint() {
        if (pointsTable.playerBWonPoint()) {
            gamesTable.playerBWonPoint();
            pointsTable = new PointsTable();
        }
        return false;
    }

    public String currentScore() {
        return "0/0 " + gamesTable.currentScore() + " " + pointsTable.currentScore();
    }
}
