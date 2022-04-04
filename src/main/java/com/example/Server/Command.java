package com.example.Server;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Command implements Runnable{

    private Scanner input;
    private List<ClientHandler> clientlist;

    public Command(List<ClientHandler> clientList) {
        input = new Scanner(System.in);
        this.clientlist = clientList;
    }

    @Override
    public void run() {
        while (true) {
            if (input.nextLine().equals("kill")) {
                System.exit(0);
            }
        }
    }

}
