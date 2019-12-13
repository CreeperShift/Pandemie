package project.pandemie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.Move;
import project.pandemie.data.Round;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Main {

    static int PORT = 50123;
    static File file;
    static List<Move> moveList = new ArrayList<>();
    static IParser parser;

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
                ILogic logic = new Actor();

                /*
                moveList contains all moves INCLUDING
                endRound
                 */
                moveList = logic.getMoves(r);

                /*
                Reply with a move, removing it from the list.
                 */
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
    }
}