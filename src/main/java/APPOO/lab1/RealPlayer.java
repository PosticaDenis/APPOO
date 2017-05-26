package APPOO.lab1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;

/**
 * Created by Dennis on 29-Apr-17.
 **/
public class RealPlayer implements ThePlayer{

    private String playerNickname;
    private Board board;

    RealPlayer(boolean myTurn) {

        playerNickname = setPlayerNickname();
        board = new Board();
        board.initTurn(myTurn);
        board.showBoard("Board of the player " + playerNickname + ":");
    }

    public Board getBoard() {
        return board;
    }
    public int[] chooseLocation() {

        int[] location = getLocationToAttack(false);

        while (!board.validateLocationForAttck(location)) {
            location = getLocationToAttack(true);
        }
        board.updateBoardAfterAttack(location);
        return location;
    }

    public int[] getLocationToAttack(boolean repeat) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] location = new int[2];

        if (repeat) {
            System.out.println("Please input the location data again as the previous one is invalid (was chosen before):");
        } else {
            System.out.println(playerNickname + " please input the location data:");
        }

        while (true) {
            try {
                for (int i = 0; i < 2; i++) {

                    int userData = Integer.parseInt(in.readLine());
                    if(userData > 10 || userData < 1) {
                        throw new IllegalArgumentException("Invalid location! Please try again!");
                    }
                    location[i] = userData;
                }
                return location;
            } catch (IllegalArgumentException illegalArgumentException) {

                System.out.println("Invalid location! Please try again!");

            } catch (IOException e) {

                System.out.println("There were errors encountered!");
            }
            System.out.println("Please introduce the location data again:");
        }
    }

    public String setPlayerNickname() {
        System.out.println("Introduce the nickname of the player:");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getPlayerNickname() {
        return playerNickname;
    }
}
