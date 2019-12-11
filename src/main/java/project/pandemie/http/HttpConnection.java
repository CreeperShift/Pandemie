package project.pandemie.http;

import com.google.gson.Gson;
import project.pandemie.api.IConnection;

import static spark.Spark.*;


public class HttpConnection implements IConnection {

    private String buffer;


    private void connect(){

        post("/", (req, res) -> {
            buffer = req.body();
            return "test";
        });
    }


    @Override
    public boolean send(final String m) {

        post("/", (req, res) -> "test");

        post("/", (req, res) -> {
            buffer = req.body();
            return null;
        });

        return false;
    }

    @Override
    public String receive() {

        String ret = buffer;
        buffer = null;
        return ret;
    }

}
