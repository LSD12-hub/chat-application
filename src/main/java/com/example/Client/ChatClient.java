package com.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    

    public static void main(String[] args) throws IOException {
        
        System.out.println("Waiting for server...");

        System.out.print("Enter ip address: ");

        String output = new Scanner(System.in).nextLine();
        if (output.length() == 0){
            return; // validate ip address
        }
        
        Socket socket = new Socket(output ,8080);

        System.out.println("conneceted");

        ServerConnection serverConnection = new ServerConnection(socket);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        System.out.print("Please enter your username: ");
        String username = userInput.readLine();
        out.println(username);

        Thread readServerOutput = new Thread(serverConnection);
        readServerOutput.start();

        while (readServerOutput.isAlive()) {
            String textMessage = userInput.readLine();

            out.println(textMessage);

            if (textMessage.equals("bye")) {
                readServerOutput.stop();
                System.exit(0);
            }
        }
    }
}
