package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient2 {
    final static int serverPort = 25000;

    public static void main(String[] args) throws UnknownHostException, IOException {
        Scanner scanner = new Scanner(System.in);
        String host = "localhost";
        Socket clientSocket  = new Socket(host, serverPort);
        BufferedReader msgIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter msgOut = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        //

        Thread sendMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String msg = scanner.nextLine();
                    try{
                        msgOut.write(msg);
                        msgOut.newLine();
                        msgOut.flush();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread readMessage = new Thread(new Runnable() {
            @Override
            public void run() {
                   while (true){
                       try{
                           String msg = msgIn.readLine();
                           System.out.println("response message: " + msg);
                       } catch (IOException e){
                           e.printStackTrace();
                       }
                   }
            }
        });

        sendMessage.start();
        readMessage.start();
    }
}
