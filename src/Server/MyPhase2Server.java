package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyPhase2Server {
    static int clientID = 0;
    static String g_mesage = null;
    static int numOfThread = 10;
    public static void main(String[] args)  throws Exception {
        ServerSocket listener;
        Socket serverSocket;
        int serverPort = 25000;
//        int numOfThread = 10;

        try {
            listener = new ServerSocket(serverPort);
            System.out.println("server is on");
            ExecutorService threadPool = Executors.newFixedThreadPool(numOfThread);


            while (true){
                threadPool.execute(new MultiThread(listener.accept(), clientID++));
            }
        } catch (IOException e){
            System.err.println("cant connect to client\nport is in use");
            e.printStackTrace();
        }
    }

    private static class MultiThread implements Runnable{
        private Socket serversocket;
        private String msg;
        private int clientID;
        BufferedReader msgIn;
        PrintWriter msgOut;
        Server.StringNormallize stringNormallize = new Server.StringNormallize();
        public MultiThread(Socket serversocket, int clientID) {

            this.serversocket = serversocket;
            this.clientID = clientID;
        }

        @Override
        public void run() {
            System.out.println("Connected: "+ serversocket);
            System.out.println("client: " + clientID);
            try{
                msgIn = new BufferedReader(new InputStreamReader(serversocket.getInputStream()));
                msgOut = new PrintWriter(serversocket.getOutputStream());
                msgOut.println(clientID);
                msgOut.flush();

                while (true){
                    g_mesage = msgIn.readLine();
                    if(g_mesage == null){
                        continue;
                    }
                    System.out.println("sent by client " +
                                                    clientID +
                                                            ": " +
                                                                g_mesage);
                    if(g_mesage.equalsIgnoreCase("exit")){
                        System.out.println("client "+ clientID + ": exit");
                    }
                    stringNormallize.setNormalString(g_mesage);
                    g_mesage = stringNormallize.normalize();
//                    System.out.println("server" + g_mesage);
                    msgOut.println(g_mesage);
                    msgOut.flush();
                }
            } catch (IOException e){
                System.err.println("error: "+ serversocket);
                e.printStackTrace();
            } finally {
                try {
                    serversocket.close();
                } catch (IOException e){
                    e.printStackTrace();
                    System.out.println("closed: " + serversocket);
                }
            }
        }
    }
}
