package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class SendingHand  implements Runnable{
    private Socket sendingSocket;
    private String message = null;
    Scanner scanner = new Scanner(System.in);
    BufferedWriter msgOut;

    public SendingHand(Socket sendingSocket) {
        this.sendingSocket = sendingSocket;
    }

    @Override
    public void run() {
        try{
//            System.out.println("message: >");
            msgOut = new BufferedWriter(new OutputStreamWriter(sendingSocket.getOutputStream()));
            while (true) {
                System.out.println("message: >");
                message = scanner.nextLine();
                msgOut.write(message);
                msgOut.newLine();
                msgOut.flush();
                if("exit".equalsIgnoreCase(message)){
                    break;
                }
            }
            msgOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
