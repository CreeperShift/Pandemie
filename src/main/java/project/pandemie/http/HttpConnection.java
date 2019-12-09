package project.pandemie.http;

import project.pandemie.api.IConnection;
import spark.Spark;

import static spark.Spark.post;

public class HttpConnection implements IConnection {

    private String buffer;

    public HttpConnection(){
        Spark.port(50123);
        connect();
    }

    private void connect(){

        post("/", (req, res) -> {
            buffer = req.body();
            return null;
        });
    }


    @Override
    public boolean send(final String m) {

        post("/", (req, res) -> {
            return m;
        });
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
