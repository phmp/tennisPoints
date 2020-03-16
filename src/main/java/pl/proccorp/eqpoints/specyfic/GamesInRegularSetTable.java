package pl.proccorp.eqpoints.specyfic;

import pl.proccorp.eqpoints.general.ScoreTableWithNeededAdvantage;
import pl.proccorp.eqpoints.model.Player;

public class GamesInRegularSetTable extends ScoreTableWithNeededAdvantage {

    public GamesInRegularSetTable() {
        super(6);
    }

    @Override
    public boolean won(Player player) {
        return super.won(player) || getPoints(player) >= 7;
    }
}
