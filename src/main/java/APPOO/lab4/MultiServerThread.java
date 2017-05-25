package APPOO.lab4;

import APPOO.lab4.tools.Constants;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import static APPOO.lab4.Server.listOfUsers;
import static APPOO.lab4.Server.multiServerThreads;

/**
 * Created by Dennis on 24-May-17.
 **/
public class MultiServerThread extends Thread {

    private Socket socket = null;
    private PrintWriter out;
    private String nickname;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }

    public void run() {

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String fromClient;
            try {
                while (true) {

                    fromClient = in.readLine();
                    if (fromClient != null) {

                        List<String> clientPackage = Arrays.asList(fromClient.split(Constants.Client.SEPARATOR));

                        if(clientPackage.get(0).equals(Constants.Client.EXIT_COMMAND)) {
                            stopSession();
                            break;
                        }
                        else if(clientPackage.get(0).equals(Constants.Client.LIST_COMMAND)) {
                            String users = getOnlineUsers();
                            out.println(users);
                        }
                        else if(clientPackage.get(0).equals(Constants.Client.NEW_USER)) {
                            validateNickname(clientPackage.get(1));
                        }

                        else {

                            if (multiServerThreads.size() > 1) {

                                for (MultiServerThread multiServerThread: multiServerThreads) {
                                    if (!multiServerThread.getNickname().equals(nickname)) {

                                        multiServerThread.sendToClient(nickname, clientPackage.get(1));
                                    }
                                }
                            }
                            else {
                                System.out.println("There is just 1 user online. No need to send message.");
                            }
                        }
                    }
                }
            } catch (IOException e) {
                removeThread();
            }

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateNickname(String nickname) {

        if(listOfUsers.size() == 0) {
            System.out.println("User " + nickname + " joined the chat.");
            listOfUsers.add(nickname);
            this.nickname = nickname;
            out.println(Constants.Client.NEW_USER + Constants.Client.SEPARATOR + "true");
        }

        else {
            if (listOfUsers.contains(nickname)) {
                System.out.println("Invalid username " + nickname);
                out.println(Constants.Client.NEW_USER + Constants.Client.SEPARATOR + "false");
            }
            else {
                System.out.println("User " + nickname + " joined the chat.");

                listOfUsers.add(nickname);
                this.nickname = nickname;
                out.println(Constants.Client.NEW_USER + Constants.Client.SEPARATOR + "true");
            }
        }
    }

    private void removeThread() {

        System.out.println("User " + nickname + " unexpectedly disconnected.");

        if(listOfUsers.contains(nickname)) {
            listOfUsers.remove(nickname);
        }
        multiServerThreads.remove(this);
    }

    private String getOnlineUsers() {

        System.out.println("Request: get online users from " + nickname + ".");

        /*if (listOfUsers.size() < 2) {
            return "Just you are online!";
        }*/

        String users = "";
        for (String user: listOfUsers) {
            users = users + user + Constants.Client.SEPARATOR;
        }

        return Constants.Client.LIST_COMMAND + Constants.Client.SEPARATOR + users;
    }

    private void sendToClient(String senderNickname, String message) {

        out.println(Constants.Client.MESSAGE_COMMAND + Constants.Client.SEPARATOR + senderNickname + ": " + message);
    }

    private String getNickname() {
        return nickname;
    }

    private void stopSession() {
        System.out.println("User " + nickname + " left the chat.");

        listOfUsers.remove(nickname);
        multiServerThreads.remove(this);
    }
}
