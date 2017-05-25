package APPOO.lab4;
/**
 * Created by Dennis on 23-May-17.
 **/
public class User {

    private String nickname;
    private String host;
    private int port;

    User(String nickname, String host, int port) {

        this.nickname = nickname;
        this.host = host;
        this.port = port;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
