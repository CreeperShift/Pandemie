package project.pandemie.http;

import com.sun.net.httpserver.HttpServer;
import project.pandemie.api.IConnection;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerCustom implements IConnection {

    HttpServer httpServer;


    public HttpServerCustom(int port) throws IOException {

        httpServer = HttpServer.create(new InetSocketAddress("localhost", port), 0);
        httpServer.start();

    }
    public HttpServerCustom() throws IOException {
        this(50123);
    }


    @Override
    public boolean send(String m) {
        return false;
    }

    @Override
    public String receive() {
        return null;
    }
}
