package com.example.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{

    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;
    private List<ClientHandler> clientList;
    private String username;

    public ClientHandler(Socket socket, List<ClientHandler> clientList) throws IOException {
        this.socket = socket;
        this.clientList = clientList;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.username = in.readLine();
    }

    @Override
    public void run() {
        
        try {
            while (true) {
                String textMessage = in.readLine();
                
                sendToAllOtherUsers(textMessage);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendToAllOtherUsers(String textMessage) {
        for (ClientHandler clientHandler : clientList){
            if (this != clientHandler)
            clientHandler.out.println(username + ">>: " + textMessage);
        }
    }
}
