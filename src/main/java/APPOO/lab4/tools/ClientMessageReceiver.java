package APPOO.lab4.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Dennis on 25-May-17.
 **/
public class ClientMessageReceiver extends Thread {

    private Stack<String> messagesStack;
    private BufferedReader in;

    public ClientMessageReceiver(BufferedReader in) {
        this.in = in;
        messagesStack = new Stack<>();
    }

    public void run() {

        try {
            String fromClient;

            while (true) {
                fromClient = in.readLine();
                List<String> serverPackage = Arrays.asList(fromClient.split(Constants.Client.SEPARATOR));


                messagesStack.push(fromClient);

                if (serverPackage.get(0).equals(Constants.Client.MESSAGE_COMMAND)) {
                    System.out.println(serverPackage.get(1));

                    messagesStack.pop();
                }
            }
        } catch (IOException e) {

            this.interrupt();
        }
    }

    public String getMessage() {
        return messagesStack.pop();
    }

    public boolean noMessages() {
        return messagesStack.empty();
    }
}
