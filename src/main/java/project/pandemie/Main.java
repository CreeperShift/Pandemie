package project.pandemie;

import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.Move;
import project.pandemie.data.Round;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;
import spark.Service;

import java.util.ArrayList;
import java.util.List;

import static spark.Service.ignite;

public class Main {

    static int PORT = 50123;
    static List<Move> moveList = new ArrayList<>();
    static IParser parser;

    public static void main(String[] args) {
        parser = new Parser();

        igniteFirstSpark(args);
        igniteSecondSpark();


    }

    private static void igniteSecondSpark(){

        Service http = ignite().port(80).threadPool(20);
        // root is 'src/main/resources', so put files in 'src/main/resources/public'
        http.staticFiles.location("/public"); // Static files
        http.get("/test.json", (q,a) -> {

            return "{\n" +
                    "  \"cols\": [\n" +
                    "    {\n" +
                    "      \"id\": \"\",\n" +
                    "      \"label\": \"Topping\",\n" +
                    "      \"pattern\": \"\",\n" +
                    "      \"type\": \"string\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": \"\",\n" +
                    "      \"label\": \"Slices\",\n" +
                    "      \"pattern\": \"\",\n" +
                    "      \"type\": \"number\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\": \"\",\n" +
                    "      \"label\": \"Slices2\",\n" +
                    "      \"pattern\": \"\",\n" +
                    "      \"type\": \"number\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"rows\": [\n" +
                    "    {\n" +
                    "      \"c\": [\n" +
                    "        {\n" +
                    "          \"v\": \"Mushrooms\",\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 3,\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 1,\n" +
                    "          \"f\": null\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"c\": [\n" +
                    "        {\n" +
                    "          \"v\": \"Onions\",\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 1,\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 2,\n" +
                    "          \"f\": null\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"c\": [\n" +
                    "        {\n" +
                    "          \"v\": \"Olives\",\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 1,\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 5,\n" +
                    "          \"f\": null\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"c\": [\n" +
                    "        {\n" +
                    "          \"v\": \"Zucchini\",\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 1,\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 7,\n" +
                    "          \"f\": null\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"c\": [\n" +
                    "        {\n" +
                    "          \"v\": \"Pepperoni\",\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 2,\n" +
                    "          \"f\": null\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"v\": 3,\n" +
                    "          \"f\": null\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
        });

    }

    private static void igniteFirstSpark(String[] args) {

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
        Service http = ignite().port(PORT).threadPool(20);

          /*
        Post ROUTE
         */
        http.post("/", (req, res) -> {

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
}