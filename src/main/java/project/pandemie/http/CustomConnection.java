package project.pandemie.http;

import com.google.gson.Gson;
import project.pandemie.api.IConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class CustomConnection extends Thread implements IConnection {
    final ServerSocket socket;
    Thread thread;

    public CustomConnection(int port) throws IOException {
        socket = new ServerSocket(port);
        thread = new Thread(this);
        thread.start();
    }

    public CustomConnection() throws IOException {
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

    @Override
    public void run() {
        try {
            final Socket client = socket.accept();

        while(true){

            InputStreamReader isr =  new InputStreamReader(client.getInputStream(),"UTF8");
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while (!line.isEmpty()) {
                System.out.println(line);
                line = reader.readLine();
            }

            System.out.println("out");

            Message m = new Message();
            Gson g = new Gson();

            String content = g.toJson(m);

            // Send headers
            BufferedWriter wr =
                    new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF8"));
            wr.write("POST / HTTP/1.1");
            wr.write("Content-Length: "+content.length());
            wr.write("Content-Type: application/json");

            // Send parameters
            wr.write(content);
            wr.flush();

        }
        } catch (IOException e) {
            e.printStackTrace();
        }

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
