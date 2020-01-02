package project.pandemie;

import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Round;
import project.pandemie.data.move.Move;
import project.pandemie.logging.LogWriter;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;
import project.pandemie.visualization.Plotter;
import project.pandemie.visualization.Visualizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static spark.Spark.port;
import static spark.Spark.post;

public class Main {

    static int PORT = 50123;
    static List<Move> moveList = new ArrayList<>();
    static IParser parser;
    static LogWriter pathogenLog;
    static LogWriter eventLog;
    static LogWriter cityEventLog;

    private static int pauseTimer = 0;

    public static void main(String[] args) {

        init(args);
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

                sleep(pauseTimer);

                return parser.parseMove(moveList.remove(0));
            }

            /*
            TODO: Since we can observe the effect of a move
                  in the same round, it might be better to process
                  only one move at a time, and feed the input back into
                  our logic actor.
             */
            sleep(pauseTimer);
            return parser.parseMove(moveList.remove(0));

        });

    }

    private static void doVisualization(Round r) {
        Visualizer.getInstance().addRound(r);

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
    private static void init(String[] args) {
        /*
        Handles cmd arguments
         */
        if (args.length == 2) {
            processInput(args[0], args[1]);
        }

        port(PORT);
        parser = new Parser();
        pathogenLog = new LogWriter("C:/Pandemie/pathogens.txt");
        eventLog = new LogWriter("C:/Pandemie/events.txt");
        cityEventLog = new LogWriter("C:/Pandemie/cityEvents.txt");

        Plotter plot = new Plotter("World Population over time", "x", "y", 400, 400);
    }

    private static void processInput(String a, String b) {

        switch (a) {
            case "-p":
            case "-port":
                Integer p = null;
                try {
                    p = Integer.parseInt(b);
                } catch (NumberFormatException e) {
                    System.out.println("Port is not a valid number!");
                    System.exit(1);
                }
                if (null != p) {
                    PORT = p;

                }
                break;
            case "-s":
            case "-sleep":
                Integer s = null;
                try {
                    s = Integer.parseInt(b);
                } catch (NumberFormatException e) {
                    System.out.println("SleepTimer is not a valid number!");
                    System.exit(1);
                }
                if (null != s) {
                    pauseTimer = s;
                }
                break;
        }

    }

}