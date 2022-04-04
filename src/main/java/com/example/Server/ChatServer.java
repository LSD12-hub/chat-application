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


    public static void main( String[] args )
    {
        new Thread(new Command(clientList)).start();

        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (clientList.size() < 5) {
                System.out.println("Waiting for clients to connect");
                Socket socket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(socket, clientList);
                clientList.add(clientHandler);

                pool.execute(clientHandler);
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
