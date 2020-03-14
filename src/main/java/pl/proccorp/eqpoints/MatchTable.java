package pl.proccorp.eqpoints;

import pl.proccorp.eqpoints.general.ScoreTable;
import pl.proccorp.eqpoints.specyfic.PointsTable;
import pl.proccorp.eqpoints.specyfic.GamesTable;
import pl.proccorp.eqpoints.specyfic.SetsTable;

public class MatchTable implements ScoreTable {

    private ScoreTable pointsTable = new PointsTable();
    private ScoreTable gamesTable = new GamesTable();
    private ScoreTable setsTable = new SetsTable();

    public boolean playerAWonPoint() {
        if (pointsTable.playerAWonPoint()) {
            pointsTable = new PointsTable();
            if (gamesTable.playerAWonPoint()){
                gamesTable = new GamesTable();
                return setsTable.playerAWonPoint();
            }
        }
        return false;
    }

    public boolean playerBWonPoint() {
        if (pointsTable.playerBWonPoint()) {
            pointsTable = new PointsTable();
            if (gamesTable.playerBWonPoint()){
                gamesTable = new GamesTable();
                return setsTable.playerBWonPoint();
            }
        }
        return false;
    }

    public String currentScore() {
        return setsTable.currentScore() + " " + gamesTable.currentScore() + " " + pointsTable.currentScore();
    }
}
