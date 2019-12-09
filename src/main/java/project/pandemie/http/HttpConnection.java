package project.pandemie.http;

import project.pandemie.api.IConnection;
import spark.Spark;


public class HttpConnection implements IConnection {

    private String buffer;

    public HttpConnection(){
        Spark.port(50123);
        connect();
    }

    private void connect(){

        Spark.post("/", (req, res) -> {
            buffer = req.body();
            return null;
        });
    }


    @Override
    public boolean send(final String m) {

        Spark.post("/", (req, res) -> {
            return m;
        });
        Spark.post("/", (req, res) -> {
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
