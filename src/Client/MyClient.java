package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        Socket clientSocket;
        String serverHost = "localHost";
        String serverMessage;
        String message;
        BufferedReader inputStream;
        BufferedWriter outputStream;
        Scanner scan = new Scanner(System.in);

        // connect to server through serverHost and port 8888
        try {
            clientSocket = new Socket(serverHost, 9091);
            System.out.println("Connected");

            // initialize input/out stream
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println("Scanning: ");
            while (!clientSocket.isClosed()) {
                try {
                    //push and reciever message from server
                    System.out.print("message: >");
                    message = scan.nextLine();
//                message ="first message";
                    outputStream.write(message);
                    outputStream.newLine();
                    outputStream.flush();

                    //read message from server
                    serverMessage = inputStream.readLine();
                    System.out.println("response: " + serverMessage);
                    if(serverMessage.equalsIgnoreCase("close")){
                        clientSocket.close();
                        break;

                    }
                } catch (IOException e) {
                    System.err.println("error to chat with server!");
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("can't connect to server. access denied!");
            e.printStackTrace();
        }
    }
}
