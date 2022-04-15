package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SReceivingProcesing implements Runnable{
    private Socket socket;
//    private DataInputStream recvInput;
    private String message = null;

    BufferedReader receiverBuffer;
    int clientID;
    public SReceivingProcesing(Socket socket, int clientID) {
        this.socket = socket;
        this.clientID = clientID;
    }

    // getter

    public String getMessage() {
        return message;
    }

    @Override
    public void run() {
//        receiverBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try {
            this.receiverBuffer = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e){
            System.err.println("SReceiverProcessing error");
            e.printStackTrace();
        }
        while (true) {

            try {
                message = receiverBuffer.readLine();
                if(message != null && message.equalsIgnoreCase("exit")){
                    break;
                }
                if(message == null){
                    break;
                }
                System.out.println("sent by client " + clientID + " : " + message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
//            recvInput.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
