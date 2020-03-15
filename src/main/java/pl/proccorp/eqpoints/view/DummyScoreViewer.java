package pl.proccorp.eqpoints.view;

import pl.proccorp.eqpoints.MatchTable;
import pl.proccorp.eqpoints.general.ScoreTable;

import static spark.Spark.get;
import static spark.Spark.port;

public class DummyScoreViewer {

    private static ScoreTable matchTable = new MatchTable();

    public static void main(String... ars) {
        port(8181);
        get("/points/", (request, response) -> score());
        get("/points/A", (request, response) -> {
            matchTable.playerAWonPoint();
            return score();
        });
        get("/points/B", (request, response) -> {
            matchTable.playerBWonPoint();
            return score();
        });
    }

    private static String score() {
        return view.replace("{{score}}", matchTable.currentScore());
    }

    private static String view = "<html>\n" +
            "<head>\n" +
            "</head>\n" +
            "<body>\n" +
            "<a href=\"http://localhost:8181/points/A\">A</a> <br></br>\n" +
            "<a href=\"http://localhost:8181/points/B\">B</a> <br></br>\n" +
            "{{score}}\n" +
            "</body>\n" +
            "</html>";
}