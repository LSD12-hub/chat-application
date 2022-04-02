package com.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    

    public static void main(String[] args) throws UnknownHostException, IOException {
        
        System.out.println("Waiting for server...");
        
        Socket socket = new Socket("127.0.0.1",1234);

        System.out.println("conneceted");

        ServerConnection serverConnection = new ServerConnection(socket);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        System.out.print("Please enter your username: ");
        out.println(userInput.readLine());

        Thread readServerOutput = new Thread(serverConnection);
        readServerOutput.start();

        while (readServerOutput.isAlive()) {
            String textMessage = userInput.readLine();

            out.println(textMessage);
        }
    }
}
