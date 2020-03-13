package pl.proccorp.eqpoints.general;

public abstract class SimplePointsTable implements ScoreTable {

    protected final int numberOfPointsNeededToWin;
    protected int pointsOfPlayerA = 0;
    protected int pointsOfPlayerB = 0;

    public SimplePointsTable(int numberOfPointsNeededToWin) {
        this.numberOfPointsNeededToWin = numberOfPointsNeededToWin;
    }

    @Override
    public boolean playerAWonPoint (){
        return ++pointsOfPlayerA == numberOfPointsNeededToWin;
    }

    @Override
    public boolean playerBWonPoint(){
        return ++pointsOfPlayerB == numberOfPointsNeededToWin;
    }

    public String currentScore(){
        return "" + pointsOfPlayerA + '/' + pointsOfPlayerB;
    }

}
