package APPOO.lab4;

import APPOO.lab4.tools.Constants;

/**
 * Created by Dennis on 23-May-17.
 **/
public class ServerMain {

    public static void main(String argv[]) throws Exception {

        Server server = new Server(Constants.Server.PORT);

        //server.startServer();

        /*String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);*/

        /*while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Received: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }*/
    }
}
