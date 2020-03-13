package pl.proccorp.eqpoints;

import pl.proccorp.eqpoints.general.ScoreTable;
import pl.proccorp.eqpoints.specyfic.GameTable;
import pl.proccorp.eqpoints.specyfic.SetTable;

public class MatchTable implements ScoreTable {

    private ScoreTable gameTable = new GameTable();
    private ScoreTable setTable = new SetTable();

    public boolean playerAWonPoint() {
        if (gameTable.playerAWonPoint()) {
            setTable.playerAWonPoint();
            gameTable = new GameTable();
        }
        return false;
    }

    public boolean playerBWonPoint() {
        if (gameTable.playerBWonPoint()) {
            setTable.playerBWonPoint();
            gameTable = new GameTable();
        }
        return false;
    }

    public String currentScore() {
        return "0/0 " + setTable.currentScore() + " " + gameTable.currentScore();
    }
}
