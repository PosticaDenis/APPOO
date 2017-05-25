package APPOO.lab4.tools;

/**
 * Created by Dennis on 23-May-17.
 **/
public final class Constants {
    public final class Server {
        public final static int PORT = 12340;
        //public final static long MAX_USERS = 2;
    }
    public final class Client {

        public final static String LIST_COMMAND = "list";
        public final static String HELP_COMMAND = "help";

        public final static String NEW_USER = "n";
        public final static String MESSAGE_COMMAND = "m";
        public final static String EXIT_COMMAND = "exit";

        public final static String SEPARATOR = ";";

        public final static String HELP_TEXT = "\nType '" + LIST_COMMAND + "' to get the list of online clients.\n" +
                "Type '" + HELP_COMMAND + "' to display this message.\n" +
                "Type '" + EXIT_COMMAND + "' to exit.\n" +
                "Any other text will be recognized as message and will be sent to all online users.\n" +
                "Have fun!\n";
    }
}

