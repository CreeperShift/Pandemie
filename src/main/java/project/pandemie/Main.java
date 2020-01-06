package project.pandemie;

import com.beust.jcommander.JCommander;
import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.logging.LogWriter;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;
import project.pandemie.util.Args;
import project.pandemie.visualization.Plotter;
import project.pandemie.visualization.Visualizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static spark.Spark.port;
import static spark.Spark.post;

public class Main {

    static List<Move> moveList = new ArrayList<>();
    static IParser parser;
    static LogWriter pathogenLog;
    static LogWriter eventLog;
    static LogWriter cityEventLog;

    static Args cliArgs;

    public static void main(String[] args) {
        cliArgs = new Args();
        JCommander.newBuilder().addObject(cliArgs).build().parse(args);

        init();

        /*
        Post ROUTE
         */
        post("/", (req, res) -> {

            if (moveList.isEmpty()) {

                /*
                Read req and translate it into a ROUND object
                 */
                Round r = parser.parseRound(req.body());

                /*
                We don't save states so it creates a new Actor
                 */
                ILogic logic = new Actor(r);

                /*
                moveList contains all moves INCLUDING
                endRound
                 */
                moveList = logic.getMoves();

                /*
                Reply with a move, removing it from the list.
                 */

                doLogging(r);
                doVisualization(r);

                sleep(cliArgs.getSleepTimer());

                return parser.parseMove(moveList.remove(0));
            }

            /*
            TODO: Since we can observe the effect of a move
                  in the same round, it might be better to process
                  only one move at a time, and feed the input back into
                  our logic actor.
             */
            sleep(cliArgs.getSleepTimer());
            return parser.parseMove(moveList.remove(0));

        });

    }

    private static void doVisualization(Round r) {
        if (cliArgs.doVisualization()) {
            Visualizer.getInstance().addRound(r);
        }
    }

    private static void doLogging(Round r) throws IOException {

        if (r.getRound() == 1) {
            for (Events e : r.getEvents()) {
                if (e.getPathogen() != null) {
                    pathogenLog.log(e.getPathogen().toString());
                }
            }
        }

        for (Events e : r.getEvents()) {
            if (e.getPathogen() == null) {
                eventLog.log(e.toString());
            }
        }

        for (City c : r.getCities().values()) {
            if (c.hasEvents()) {
                for (Events e : c.getEvents()) {
                    if (e.getPathogen() == null) {
                        cityEventLog.log(e.toString());
                    }
                }

            }
        }

    }

    /*
    Initialization before we start the ROUTE
     */
    private static void init() {

        port(cliArgs.getPort());
        
        parser = new Parser();

        if (cliArgs.doLogging()) {
            pathogenLog = new LogWriter("C:/Pandemie/pathogens.txt");
            eventLog = new LogWriter("C:/Pandemie/events.txt");
            cityEventLog = new LogWriter("C:/Pandemie/cityEvents.txt");
        }

        if (cliArgs.doVisualization()) {
            Plotter plot = new Plotter("World Population over time", "x", "y", 800, 800);
        }
    }
}