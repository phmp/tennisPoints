package pl.proccorp.eqpoints.general;

import pl.proccorp.eqpoints.model.Player;

public abstract class ScoreTableWithNeededAdvantage extends SimpleScoreTable {
    private final int neededAdvantage = 2;

    public ScoreTableWithNeededAdvantage(int numberOfPointsNeededToWin) {
        super(numberOfPointsNeededToWin);
    }

    @Override
    public boolean won(Player player) {
        boolean hasEnoughPoints = super.won(player);
        return hasEnoughPoints && hasNeededAdvantage(player);
    }

    private boolean hasNeededAdvantage(Player player) {
        Player opponent = player.opponent();
        return getPoints(player) - getPoints(opponent) >= neededAdvantage;
    }

}
