package project.pandemie;

import com.google.gson.Gson;
import project.pandemie.api.ILogic;
import project.pandemie.api.IParser;
import project.pandemie.data.Move;
import project.pandemie.data.Round;
import project.pandemie.data.TestMove;
import project.pandemie.logic.Actor;
import project.pandemie.parse.Parser;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static spark.Spark.*;

public class Main {


    public static void main(String[] args) throws Exception {

        Gson gson = new Gson(); //GoogleJson to convert between java & json
        Message m = new Message(); //obj to convert to json

        String b;
        port(50123);

        List<Move> testList = Collections.emptyList();
       // testList.add(null);

        post("/", (req, res) -> {

           String body =  req.body();

            IParser parser = new Parser();
            Round r = parser.parseRound(body);
            ILogic logic = new Actor();
            Collection<Move> list = logic.getMoves(r);
            System.out.println(req.body());

            TestMove t = new TestMove();

            System.out.println(i + "/n");

            return gson.toJson(t);
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