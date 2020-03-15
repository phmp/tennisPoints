package pl.proccorp.eqpoints.general;

import pl.proccorp.eqpoints.model.Player;

public interface ScoreTable {
    void addPointFor(Player player);
    boolean won(Player player);
    String currentScore();
}
