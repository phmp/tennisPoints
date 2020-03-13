package pl.proccorp.eqpoints;

import java.util.List;

public class MyPointsTable implements PointsTable {

    private List<String> pointsView = List.of("0", "15", "30", "40");
    private int pointsOfPlayerA = 0;
    private int pointsOfPlayerB = 0;

    public void playerAWonPoint() {
        pointsOfPlayerA++;
    }

    public void playerBWonPoint() {
        pointsOfPlayerB++;
    }

    public String currentScore() {
        String playerAScore = pointsView.get(pointsOfPlayerA);
        String playerBScore = pointsView.get(pointsOfPlayerB);
        return "0/0 0/0 " + playerAScore + '/' + playerBScore;
    }
}
