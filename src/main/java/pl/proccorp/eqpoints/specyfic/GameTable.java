package pl.proccorp.eqpoints.specyfic;

import pl.proccorp.eqpoints.general.SimplePointsTable;

import java.util.List;

public class GameTable extends SimplePointsTable {

    private List<String> pointsView = List.of("0", "15", "30", "40");

    public GameTable() {
        super(4);
    }

    @Override
    public String currentScore(){
        String playerAScore = pointsView.get(pointsOfPlayerA%numberOfPointsNeededToWin);
        String playerBScore = pointsView.get(pointsOfPlayerB%numberOfPointsNeededToWin);
        return playerAScore + '/' + playerBScore;
    }

}
