package pl.proccorp.eqpoints;

import java.text.MessageFormat;
import java.util.List;

public class MyPointsTable implements PointsTable {

    private List<String> pointsView = List.of("0","15","30","40");
    private int pointsOfPlayerA = 0;
    private int pointsOfPlayerB = 0;
    private String displayPattern = "0/0 0/0 {0}/{1}";

    public void playerAWonPoint() {
        pointsOfPlayerA++;
    }

    public void playerBWonPoint() {
        pointsOfPlayerB++;
    }

    public String currentScore() {
        String playerAScore = pointsView.get(pointsOfPlayerA);
        String playerBScore = pointsView.get(pointsOfPlayerB);
        return MessageFormat.format(displayPattern, playerAScore, playerBScore);
    }
}
