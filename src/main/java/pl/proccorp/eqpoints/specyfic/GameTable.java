package pl.proccorp.eqpoints.specyfic;

import pl.proccorp.eqpoints.general.SimplePointsTable;

import java.util.List;

public class GameTable extends SimplePointsTable {

    private List<String> pointsView = List.of("0", "15", "30", "40");

    public GameTable() {
        super(4);
    }

    @Override
    public boolean playerAWonPoint() {
        return super.playerAWonPoint() && pointsOfPlayerA - pointsOfPlayerB >= 2;
    }

    @Override
    public boolean playerBWonPoint() {
        return super.playerBWonPoint() && pointsOfPlayerB - pointsOfPlayerA >= 2;
    }

    @Override
    public String currentScore() {
        try {
            String playerAScore = pointsView.get(pointsOfPlayerA);
            String playerBScore = pointsView.get(pointsOfPlayerB);
            return playerAScore + '/' + playerBScore;
        } catch (IndexOutOfBoundsException e) {
            if (pointsOfPlayerA>pointsOfPlayerB ){
                return "AD/40";
            } else if (pointsOfPlayerA == pointsOfPlayerB){
                return "40/40";
            } else {
                return "40/AD";
            }
        }
    }

}
