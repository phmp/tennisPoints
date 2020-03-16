package pl.proccorp.eqpoints.general;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.proccorp.eqpoints.model.Player;
import pl.proccorp.eqpoints.specyfic.GamesTable;
import pl.proccorp.eqpoints.specyfic.SetsTable;
import pl.proccorp.eqpoints.specyfic.TieBreakTable;

import java.util.List;

class SimpleScoreTableTest {

    private static List<SimpleScoreTable> tables(){
        return List.of(new TieBreakTable(), new SetsTable(), new GamesTable());
    }

    @ParameterizedTest
    @MethodSource("tables")
    void pointsShouldBeCountedUsingSimpleNumbers(SimpleScoreTable table){
        Assertions.assertThat(table.currentScore()).isEqualTo("0/0");
        table.addPointFor(Player.A);
        Assertions.assertThat(table.currentScore()).isEqualTo("1/0");
        table.addPointFor(Player.B);
        Assertions.assertThat(table.currentScore()).isEqualTo("1/1");
        table.addPointFor(Player.A);
        Assertions.assertThat(table.currentScore()).isEqualTo("2/1");
        table.addPointFor(Player.A);
        Assertions.assertThat(table.currentScore()).isEqualTo("3/1");
    }

}