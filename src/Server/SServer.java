package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SServer {
    static int clientID = 0;
    static final int port = 25000;

    String msg = null;
    public static void main(String[] args) throws IOException {

        String message;
        ServerSocket listener;
        Socket serverSocket;
        SSendingProcessing sendThread;
        SReceivingProcesing recvThread;
        SStringProcessing processingThread;

        //
        try {
            listener = new ServerSocket(port);
            System.out.println("server is on");
            while (true){
                try{
                    serverSocket = listener.accept();
                    clientID++;
                    System.out.println("New client request received: " +
                                serverSocket);
                    System.out.println("client " + clientID);

                    recvThread = new SReceivingProcesing(serverSocket,clientID);
                    ///////
                    sendThread = new SSendingProcessing(serverSocket,clientID, recvThread.getMessage());
                    Thread receiver = new Thread(recvThread);
                    Thread sender = new Thread(sendThread);

                    receiver.start();
                    sender.start();

                } catch (IOException e){
                    e.printStackTrace();
                }
        }

        } catch (IOException e){
            System.err.println("failed to start");
            e.printStackTrace();
        }

    }


}
