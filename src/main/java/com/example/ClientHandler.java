package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{

    BufferedReader in;
    PrintWriter out;
    Socket socket;
    List<ClientHandler> clientList;

    public ClientHandler(Socket socket, List<ClientHandler> clientList) throws IOException {
        this.socket = socket;
        this.clientList = clientList;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        out.println("");
    }
}
