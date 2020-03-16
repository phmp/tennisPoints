package pl.proccorp.eqpoints.specyfic;

import pl.proccorp.eqpoints.general.ScoreTableWithNeededAdvantage;
import pl.proccorp.eqpoints.model.Player;

public class GamesInLastSetTable extends ScoreTableWithNeededAdvantage {

    public GamesInLastSetTable() {
        super(6);
    }

    @Override
    public boolean won(Player player) {
        return super.won(player) || getPoints(player) >= 13;
    }
}
