package com.example.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class ChatServer 
{
    private static List<ClientHandler> clientList = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    private static ServerSocket serverSocket;
    private static Socket socket;

    public static void main( String[] args )
    {
        try {
            serverSocket = new ServerSocket(1234);

            while (clientList.size() < 5) {
                System.out.println("Waiting for clients to connect");
                socket = serverSocket.accept();
                System.out.println(" has connected");

                ClientHandler clientHandler = new ClientHandler(socket, clientList);
                clientList.add(clientHandler);

                pool.execute(clientHandler);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
