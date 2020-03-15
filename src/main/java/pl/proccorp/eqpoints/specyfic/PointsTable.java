package pl.proccorp.eqpoints.specyfic;

import pl.proccorp.eqpoints.general.ScoreTableWithNeededAdvantage;

import java.util.List;

import static pl.proccorp.eqpoints.model.Player.A;
import static pl.proccorp.eqpoints.model.Player.B;

public class PointsTable extends ScoreTableWithNeededAdvantage {

    private final List<String> pointsView = List.of("0", "15", "30", "40");

    public PointsTable() {
        super(4);
    }

    @Override
    public String currentScore() {
        if (getPoints(A) < numberOfPointsNeededToWin && getPoints(B) < numberOfPointsNeededToWin) {
            return standardScore();
        } else {
            return scoreWithAdvantage();
        }
    }

    private String standardScore() {
        String playerAScore = pointsView.get(getPoints(A));
        String playerBScore = pointsView.get(getPoints(B));
        return playerAScore + '/' + playerBScore;
    }

    private String scoreWithAdvantage() {
        if(getPoints(A) > getPoints(B)){
            return "AD/40";
        } else if (getPoints(A) < getPoints(B)){
            return "40/AD";
        } else {
            return "40/40";
        }
    }
}