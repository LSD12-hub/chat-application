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
        this.out = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                String output = out.readLine();

                if (!socket.isConnected()) {
                    System.out.println("[Client] Server has disconnected");
                    break;
                }
                

                System.out.println(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
