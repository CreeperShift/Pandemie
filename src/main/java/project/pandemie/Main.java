package project.pandemie;

import com.google.gson.Gson;
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

    static final int PORT = 50123;
    static File file;
    static List<Move> moveList = new ArrayList<>();
    static IParser parser;

    public static void main(String[] args){

        init();

        post("/", (req, res) -> {

           if(moveList.isEmpty()){

               Round r = parser.parseRound(req.body());

               ILogic logic = new Actor();
               moveList = logic.getMoves(r);

               //Debug Output
               System.out.println(r.getRound());
               System.out.println(r.getOutcome());
               System.out.println(r.getPoints());
               System.out.println(parser.parseMove(moveList.get(0)));
               System.out.println(r.getCities().iterator().next().toString());


               return parser.parseMove(moveList.remove(0));
           }

           return parser.parseMove(moveList.remove(0));

        });

    }


    private static void init(){

        port(PORT);

        parser = new Parser();

        file = new File("log.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Setup Done");

    }
}