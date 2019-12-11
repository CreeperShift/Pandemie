package project.pandemie;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.Move;
import project.pandemie.data.Round;
import project.pandemie.data.TestMove;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static spark.Spark.*;

public class Main {

    static boolean moveDone = false;


    public static void main(String[] args) throws Exception {

        Gson gson = new Gson(); //GoogleJson to convert between java & json
        Message m = new Message(); //obj to convert to json
        File file = new File("log.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        String b;
        port(50123);

        post("/", (req, res) -> {

           String body =  req.body();
//            JsonObject job = JsonParser.parseString(body).getAsJsonObject();
//            String round = job.get("round").getAsString();
//            String outcome = job.get("outcome").getAsString();
//            System.out.println("Round: " + round);
//            System.out.println("Outcome: " + outcome);
//            if(Integer.parseInt(round) == 2 && !moveDone){
//                moveDone = true;
//                return gson.toJson(new TestMove());
//            }

           IParser parser = new Parser();
           Round r = parser.parseRound(body);

          System.out.println(r.getRound());
          System.out.println(r.getOutcome());
          System.out.println(r.getPoints());




//           ILogic logic = new Actor();
//           ArrayList<Move> list = logic.getMoves(r);
//
//            if (!list.isEmpty()){
//                return gson.toJson(list.remove(0));
//            }

            return gson.toJson(new Message());
        });

    }

}

/*
Temp class to send endRound message to test functionality
 */
class Message {
    private String type = "endRound";
    Message() {
        // no-args constructor
    }
}