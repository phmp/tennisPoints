package pl.proccorp.eqpoints.general;

public abstract class ScoreTableWithNeededAdvantage extends SimpleScoreTable {
    private int neededAdvantage = 2;

    public ScoreTableWithNeededAdvantage(int numberOfPointsNeededToWin) {
        super(numberOfPointsNeededToWin);
    }

    @Override
    public boolean playerAWonPoint() {
        return super.playerAWonPoint() && pointsOfPlayerA - pointsOfPlayerB >= neededAdvantage;
    }

    @Override
    public boolean playerBWonPoint() {
        return super.playerBWonPoint() && pointsOfPlayerB - pointsOfPlayerA >= neededAdvantage;
    }
}
