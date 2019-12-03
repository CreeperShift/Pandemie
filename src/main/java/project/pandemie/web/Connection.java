package project.pandemie.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection extends Thread {

    private ServerSocket sock;
    private Thread thread;

    public Connection() {
        this(50123);
    }


    public Connection(int port) {

        try {
            sock = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {

        for (; ; ) {

            try {
                System.out.println("Waiting for connection");
                Socket client = sock.accept();
                System.out.println("Connected!");
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    if (line.length() == 0)
                        break;
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    }
