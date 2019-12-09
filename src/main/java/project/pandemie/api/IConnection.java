package project.pandemie.api;

public interface IConnection {

    public boolean send(String m);

    public String receive();

}
