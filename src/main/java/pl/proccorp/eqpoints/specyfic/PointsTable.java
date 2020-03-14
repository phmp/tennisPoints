package pl.proccorp.eqpoints.specyfic;

import pl.proccorp.eqpoints.general.ScoreTableWithNeededAdvantage;

import java.util.List;

public class PointsTable extends ScoreTableWithNeededAdvantage {

    private final List<String> pointsView = List.of("0", "15", "30", "40");
    private final List<String> adView = List.of("40", "AD", "WIN");

    public PointsTable() {
        super(4);
    }

    @Override
    public String currentScore() {
        if (pointsOfPlayerA < numberOfPointsNeededToWin && pointsOfPlayerB < numberOfPointsNeededToWin) {
            return standardScore();
        } else {
            return scoreWithAdvantage();
        }
    }

    private String standardScore() {
        String playerAScore = pointsView.get(pointsOfPlayerA);
        String playerBScore = pointsView.get(pointsOfPlayerB);
        return playerAScore + '/' + playerBScore;
    }

    private String scoreWithAdvantage() {
        int min = Math.min(pointsOfPlayerA, pointsOfPlayerB);
        int indexOfScoreA = pointsOfPlayerA - min;
        int indexOfScoreB = pointsOfPlayerB - min;
        String playerAScore = adView.get(indexOfScoreA);
        String playerBScore = adView.get(indexOfScoreB);
        return playerAScore + '/' + playerBScore;
    }
}