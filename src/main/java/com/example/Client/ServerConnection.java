package com.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerConnection implements Runnable{

    private Socket socket;
    private BufferedReader out;

    public ServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String output = out.readLine();

                System.out.println(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
