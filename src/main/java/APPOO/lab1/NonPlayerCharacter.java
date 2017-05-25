package APPOO.lab1;

import java.util.List;

/**
 * Created by Dennis on 29-Apr-17.
 **/
public class NonPlayerCharacter implements ThePlayer{

    private Board board;

    NonPlayerCharacter(boolean myTurn) {
        board = new Board();
        board.initTurn(myTurn);
        board.showBoard("Board of the NPC");
    }

    public int[] chooseLocation() {
        return null;
    }

    public int[] getLocationToAttack(boolean repeat) {


        return null;
    }

    public Board getBoard() {
        return board;
    }
}
