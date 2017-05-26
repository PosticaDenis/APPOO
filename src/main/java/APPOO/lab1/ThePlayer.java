package APPOO.lab1;

/**
 * Created by Dennis on 29-Apr-17.
 **/

public interface ThePlayer {

    Board getBoard();
    int[] chooseLocation();
    int[] getLocationToAttack(boolean repeat);
    String setPlayerNickname();
    String getPlayerNickname();
}