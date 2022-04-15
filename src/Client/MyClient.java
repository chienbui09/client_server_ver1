package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        Socket clientSocket;
        String serverHost = "localhost";
        int serverPort = 25000;
        RecvHand msgIn;
        SendingHand msgOut;
        // connect to server through serverHost and port
        try {
            clientSocket = new Socket(serverHost, serverPort);
            System.out.println("Connected");
            msgIn = new RecvHand(clientSocket);
            msgOut = new SendingHand(clientSocket);
            Thread recvThread = new Thread(msgIn);
            Thread sendThread = new Thread(msgOut);
            sendThread.start();
            recvThread.start();

        } catch (IOException e) {
            System.err.println("can't connect to server. access denied!");
            e.printStackTrace();
        }
    }
}
