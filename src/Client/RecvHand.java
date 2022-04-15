package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RecvHand implements Runnable{
    private Socket recvSocket;
    private BufferedReader serverResponse = null;
    private String messIn = null;
    public RecvHand(Socket recvSocket) {
        this.recvSocket = recvSocket;
    }
    int clientID;
    @Override
    public void run() {
        try {
            serverResponse = new BufferedReader(new InputStreamReader(recvSocket.getInputStream()));
            System.out.println("assigned ID: " + serverResponse.readLine());
            while (true) {
                if ((messIn = serverResponse.readLine()) !=null)
//                messIn = serverResponse.readLine();
                System.out.println("server message: " + messIn);
                if("exit".equalsIgnoreCase(messIn)){
                    break;
                }
            }
        } catch ( IOException e){
            System.err.println("");
            e.printStackTrace();
        }

    }
}