package pl.proccorp.eqpoints.general;

import pl.proccorp.eqpoints.model.Player;

public interface ScoreTable {
    void addOneFor(Player player);
    boolean won(Player player);
    String currentScore();
}
