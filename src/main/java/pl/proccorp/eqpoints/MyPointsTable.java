package pl.proccorp.eqpoints;

import java.util.List;

public class MyPointsTable implements PointsTable {

    private List<String> pointsView = List.of("0", "15", "30", "40");
    private int pointsOfPlayerA = 0;
    private int pointsOfPlayerB = 0;
    private int gamesOfPlayerA = 0;
    private int gamesOfPlayerB = 0;

    public void playerAWonPoint() {
        pointsOfPlayerA = (pointsOfPlayerA + 1) % 4;
        if (pointsOfPlayerA == 0) {
            gamesOfPlayerA++;
        }
    }

    public void playerBWonPoint() {
        pointsOfPlayerB = (pointsOfPlayerB + 1) % 4;
        if (pointsOfPlayerB == 0) {
            gamesOfPlayerB++;
        }
    }

    public String currentScore() {
        String playerAScore = pointsView.get(pointsOfPlayerA);
        String playerBScore = pointsView.get(pointsOfPlayerB);
        String gameScore = playerAScore + '/' + playerBScore;
        String setScore = " " + gamesOfPlayerA + '/' + gamesOfPlayerB;
        return "0/0" + setScore + " " + gameScore;
    }
}
