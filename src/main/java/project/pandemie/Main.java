package project.pandemie;

import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.City;
import project.pandemie.data.Events;
import project.pandemie.data.Move;
import project.pandemie.data.Round;
import project.pandemie.logging.LogWriter;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.port;
import static spark.Spark.post;

public class Main {

    static int PORT = 50123;
    static List<Move> moveList = new ArrayList<>();
    static IParser parser;
    static LogWriter pathogenLog;
    static LogWriter eventLog;
    static LogWriter cityEventLog;

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

                return parser.parseMove(moveList.remove(0));
            }

            /*
            TODO: Since we can observe the effect of a move
                  in the same round, it might be better to process
                  only one move at a time, and feed the input back into
                  our logic actor.
             */

            return parser.parseMove(moveList.remove(0));

        });

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
        TODO: Move into own section/class
         */
        if (args.length > 1 && args[0].equals("-p")) {
            Integer p = null;
            try {
                p = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            if (null != p) {
                PORT = p;

            }

        }

        port(PORT);
        parser = new Parser();
        pathogenLog = new LogWriter("C:/Pandemie/pathogens.txt");
        eventLog = new LogWriter("C:/Pandemie/events.txt");
        cityEventLog = new LogWriter("C:/Pandemie/cityEvents.txt");
    }
}