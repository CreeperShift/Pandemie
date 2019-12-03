package project.pandemie;

import com.google.gson.Gson;
import static spark.Spark.*;

public class Main {


    public static void main(String[] args) throws Exception {

        Gson gson = new Gson(); //GoogleJson to convert between java & json
        Message m = new Message(); //obj to convert to json

        port(50123); //set custom port

        /*
        Send a post req, print out the content & send our message
         */
        post("/", (req, res) -> {

            System.out.println(req.body());

           return gson.toJson(m);
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