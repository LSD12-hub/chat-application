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
        System.out.println(this.username + " has connected");
        sendToAllOtherUsers("[SERVER] " + this.username + " has joined the chat");
    }

    @Override
    public void run() {
        
        try {
            while (true) {
                String textMessage = in.readLine();
                
                sendToAllOtherUsers(textMessage);

                if (textMessage.equals("bye")) {
                    System.out.println(this.username + " has disconnected");
                    sendToAllOtherUsers("[SERVER] " + this.username + " has left the chat");
                    socket.close();
                    this.clientList.remove(this);
                    break;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendToAllOtherUsers(String textMessage) {
        for (ClientHandler clientHandler : clientList){
            if (this != clientHandler) {
                clientHandler.out.println(username + " >>: " + textMessage);
            }
        }
    }

    @Override
    public String toString() {
        return this.username;
    }
}
