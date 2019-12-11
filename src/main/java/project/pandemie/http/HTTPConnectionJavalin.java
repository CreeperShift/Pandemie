package project.pandemie.http;

import io.javalin.Javalin;
import project.pandemie.api.IConnection;

public class HTTPConnectionJavalin implements IConnection {

    Javalin app;
    String buffer;

    public HTTPConnectionJavalin(int port){
        app = Javalin.create().start(port);
        app.post("/", ctx -> {
            buffer = ctx.body();
            ctx.result("test");
        });
    }

    public HTTPConnectionJavalin(){
        this(50123);
    }


    @Override
    public boolean send(String m) {
        app.post("/", ctx -> {
            ctx.result(m);
        });
        app.post("/", ctx -> {
            buffer = ctx.body();
            ctx.result("test");
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
