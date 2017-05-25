package APPOO.lab4;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 22-May-17.
 **/
public class Server {

    static ArrayList<String> listOfUsers = new ArrayList<>();
    static List<MultiServerThread> multiServerThreads = new ArrayList<>();

    Server(int port) {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {

                MultiServerThread multiServerThread = new MultiServerThread(serverSocket.accept());
                multiServerThreads.add(multiServerThread);
                multiServerThread.start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}
