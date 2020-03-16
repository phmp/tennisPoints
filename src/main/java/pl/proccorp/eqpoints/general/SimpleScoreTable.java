package pl.proccorp.eqpoints.general;

import pl.proccorp.eqpoints.model.Player;

import java.util.HashMap;
import java.util.Map;

import static pl.proccorp.eqpoints.model.Player.A;
import static pl.proccorp.eqpoints.model.Player.B;

public abstract class SimpleScoreTable implements ScoreTable {

    protected final int numberOfPointsNeededToWin;
    private Map<Player, Integer> map = new HashMap<>();

    public SimpleScoreTable(int numberOfPointsNeededToWin) {
        this.numberOfPointsNeededToWin = numberOfPointsNeededToWin;
    }

    protected int getPoints(Player player) {
        return map.getOrDefault(player, 0);
    }

    @Override
    public void addOneFor(Player player) {
        map.merge(player, 1, Integer::sum);
    }

    @Override
    public boolean won(Player player) {
        return getPoints(player) >= numberOfPointsNeededToWin;
    }

    @Override
    public String currentScore() {
        return "" + getPoints(A) + '/' + getPoints(B);
    }

}
