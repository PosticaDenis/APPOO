package APPOO.lab1;

import java.util.List;

/**
 * Created by Dennis on 29-Apr-17.
 **/

public interface ThePlayer {

    int[] chooseLocation();
    int[] getLocationToAttack(boolean repeat);
}