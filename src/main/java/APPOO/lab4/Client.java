package APPOO.lab4;


import APPOO.lab4.tools.ClientMessageReceiver;
import APPOO.lab4.tools.Constants;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dennis on 22-May-17.
 **/
public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader reader;
    private User user;
    private ClientMessageReceiver clientMessageReceiver;

    Client() {

        readClientData();
        try {
            clientSocket = new Socket(user.getHost(), Constants.Server.PORT);

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            reader = new BufferedReader(new InputStreamReader(System.in));

            clientMessageReceiver = new ClientMessageReceiver(in);
            clientMessageReceiver.start();

        } catch (IOException e) {
            System.out.println("\nPlease try later. Currently, the server is under maintenance!");
            System.exit(-1);
        }

        while (!validateUser(user.getNickname())) {
            newNickname();
        }

        System.out.println("Welcome to the chat!\nIf you don't know what to do type 'help'!\n");
    }

    public void startClient() {

        String toServer;

        try {
            while(true) {
                toServer = reader.readLine();
                if (toServer != null) {

                    if(toServer.equals(Constants.Client.EXIT_COMMAND)) {
                        System.out.println("You left the chat.");

                        sendExitMessage();
                        break;
                    }

                    else if(toServer.equals(Constants.Client.HELP_COMMAND)) {
                        System.out.println(Constants.Client.HELP_TEXT);
                    }

                    else if(toServer.equals(Constants.Client.LIST_COMMAND)) {
                        System.out.println("Online users:");
                        List<String> usersList = Arrays.asList(getOnlineUsers().split(Constants.Client.SEPARATOR));

                        for (String user: usersList) {
                            if (!user.isEmpty() && !user.equals(Constants.Client.LIST_COMMAND)) {
                                System.out.println(user);
                            }
                        }
                        System.out.println("\n");
                    }

                    else {

                        System.out.println("You: " + toServer);
                        sendMessage(toServer);
                    }
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error");
        }

    }

    private void sendMessage(String message) {
        out.println(Constants.Client.MESSAGE_COMMAND + Constants.Client.SEPARATOR + message);
    }

    private void sendExitMessage() {
        out.println(Constants.Client.EXIT_COMMAND + Constants.Client.SEPARATOR);
    }

    private String getOnlineUsers() {


        out.println(Constants.Client.LIST_COMMAND + Constants.Client.SEPARATOR);

        //TODO it's dangerous, rewrite me please!
        while (clientMessageReceiver.noMessages()) {}

        String response = clientMessageReceiver.getMessage();
        List<String> serverPackage = Arrays.asList(response.split(Constants.Client.SEPARATOR));

        while (!serverPackage.get(0).equals(Constants.Client.LIST_COMMAND)) {
           response = clientMessageReceiver.getMessage();
           serverPackage = Arrays.asList(response.split(Constants.Client.SEPARATOR));
        }

        return response;
    }



    private boolean validateUser(String nickname) {

        out.println(Constants.Client.NEW_USER + Constants.Client.SEPARATOR + nickname);

        //TODO it's dangerous, rewrite me please!
        while (clientMessageReceiver.noMessages()) {}

        String response = clientMessageReceiver.getMessage();

        List<String> serverPackage = Arrays.asList(response.split(Constants.Client.SEPARATOR));

        while (!serverPackage.get(0).equals(Constants.Client.NEW_USER)) {
            response = clientMessageReceiver.getMessage();
            serverPackage = Arrays.asList(response.split(Constants.Client.SEPARATOR));
        }

        return Boolean.valueOf(serverPackage.get(1));
    }

    private void readClientData() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String host = "";
        String nickname = "";

        System.out.print("Enter the server address: ");

        try {
            host = br.readLine();

            System.out.print("Enter your nickname: ");
            nickname = br.readLine();

            while(!isValid(nickname)) {
                System.out.print("Invalid nickname. A nickname can only contain letters and numbers. Try again.\n");
                nickname = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error for host and nickname");
        }
        user = new User(nickname, host, Constants.Server.PORT);
    }

    private void newNickname() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nickname = "";

        try {

            System.out.print("Enter you nickname as the previous one is already used: \n");
            nickname = br.readLine();

            while(!isValid(nickname)) {
                System.out.print("Invalid nickname. A nickname can only contain letters and numbers. Try again.\n");
                nickname = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Error for host and nickname");
        }
        user.setNickname(nickname);
    }

    private boolean isValid(String nickname) {

        if(!nickname.matches("[A-Za-z0-9]+")) {
            return false;
        }
        return true;
    }
}
