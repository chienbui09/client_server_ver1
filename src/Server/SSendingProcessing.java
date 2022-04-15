package Server;

import java.io.*;
import java.net.Socket;
import java.security.PrivateKey;

public class SSendingProcessing implements Runnable{

    private String message;
    BufferedWriter sendBuffer;
//    DataOutputStream sendOutput;
    Socket sendSocket;
    int clientID;

    public SSendingProcessing( Socket sendSocket,int clientID, String message) throws IOException {
        this.message = message;
        this.clientID = clientID;
//        this.sendOutput = sendOutput;
        this.sendSocket = sendSocket;
        try {
            this.sendBuffer = new BufferedWriter(
                    new OutputStreamWriter(this.sendSocket.getOutputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("SSending");
            System.out.println(message);
            sendBuffer.write(clientID);
            sendBuffer.newLine();
            sendBuffer.flush();
            while (!sendSocket.isClosed()) {
                try {
                    sendBuffer.write(message);
                    sendBuffer.flush();
                    sendBuffer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                sendBuffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
