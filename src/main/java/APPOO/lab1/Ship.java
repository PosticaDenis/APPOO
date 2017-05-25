package APPOO.lab1;

import java.util.ArrayList;

/**
 * Created by Dennis on 29-Apr-17.
 **/
public class Ship {

    private int size = 0;
    private int[][] usedLocations;
    private int[][] damagedLocations;

    public void setSize(int size) {
        this.size = size;

    }

    public int getSize() {
        return size;
    }

    public void setUsedLocations(int[][] usedLocations) {
        this.usedLocations = usedLocations;
    }

    public int[][] getUsedLocations() {
        return usedLocations;
    }

}
