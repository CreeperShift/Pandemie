package project.pandemie.http;

import project.pandemie.api.IConnection;

public class HttpConnection implements IConnection {
    @Override
    public boolean send(String m) {
        return false;
    }

    @Override
    public String receive() {
        return null;
    }

}
