package project.pandemie.api;

public interface IConnection {

    public boolean send(final String m);

    public String receive();

}
