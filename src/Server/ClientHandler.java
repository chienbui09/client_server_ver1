//package Server;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//public class ClientHandler implements Runnable{
//    private final Socket clientsocket;
//
//    public ClientHander(Socket socket){
//        this.clientsocket = socket;
//    }
//
//    @Override
//    public void run() {
//        PrintWriter messOut = null;
//        BufferedReader messIN = null;
//        try{
//            messOut = new PrintWriter(clientsocket.getOutputStream(), true);
//            messIN = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
//            String message = null;
//            while ((message = messIN.readLine()) != null){
////                message = messIN.readLine();
//                System.out.printf("Sent from client: %s\n", message);
//                messOut.println(message);
//            }
//
//        } catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            try{
//                if(messOut != null){
//                    messOut.close();
//                }
//                if(messIN != null){
//                    messIN.close();
//                }
//                clientsocket.close();
//            } catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
