package pl.proccorp.eqpoints;

public class MyPointsTable implements PointsTable {

    private String current = "0/0 0/0 0/0";

    public void playerAWonPoint() {

    }

    public void playerBWonPoint() {

    }

    public String currentScore() {
        return current;
    }
}
