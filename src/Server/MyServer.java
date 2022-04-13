package Server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class MyServer {
    public static void main(String[] args) {
        Socket serverSocket;
        ServerSocket listener;
        String clientMessage;
        BufferedReader inputStream;
        BufferedWriter outputStream;

        // listen and accept if Client ask for connecting
        try {
            listener = new ServerSocket(9091);
            System.out.println("Connected to Client!\tReady to chat...");
            serverSocket = listener.accept();
            System.out.println("server socket accept getLocalAddress: " + serverSocket.getLocalAddress().getHostAddress());
            System.out.println("server socket accept getPort: " + serverSocket.getPort());
            System.out.println("server socket accept getInetAddress: " + serverSocket.getInetAddress().getHostAddress());

            // initialize input & output and exchage data
            inputStream = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            outputStream = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));

            while (!serverSocket.isClosed()) {
                try {
                    clientMessage = inputStream.readLine();
                    System.out.println("message: " + clientMessage);
                    if (clientMessage != null && clientMessage.equalsIgnoreCase("end")) {
                        System.out.println("Client ask for releasing!\nConnection closed");
                        outputStream.write("close");
                        outputStream.newLine();
                        outputStream.flush();
//                        break;
                    }
                    String nomalizedString = new String();
                    clientMessage = clientMessage.toLowerCase();
                    String[] stringArray = clientMessage.split(" ");
                    for(String str : stringArray){
                        nomalizedString += str;
                        nomalizedString += " ";
                    }
                    String firstLetter = nomalizedString.substring(0,1).toUpperCase();
                    String remLetter = nomalizedString.substring(1);
                    nomalizedString = firstLetter.concat(remLetter);
                    outputStream.write(nomalizedString);
                    outputStream.newLine();
                    outputStream.flush();
                } catch (IOException e) {
                    System.err.println("Can't receiver message from client!");
                    e.printStackTrace();
                }
            }
            serverSocket.close();
            listener.close();

        } catch (IOException e) {
            System.err.println("Failed to connect to Client!");
            e.printStackTrace();
        }
    }
}
