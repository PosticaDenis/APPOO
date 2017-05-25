package APPOO.lab1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Dennis on 29-Apr-17.
 **/
public class Game {

    private ArrayList<RealPlayer> realPlayers = new ArrayList<>();
    private ArrayList<NonPlayerCharacter> nonPlayerCharacters = new ArrayList<>();
    private int nrOfPlayers = 0;

    Game() {

        //setNumberOfPlayers();
        nrOfPlayers = 2;

        /*if (nrOfPlayers == 1){
            realPlayers.add(new RealPlayer(true));
            nonPlayerCharacters.add(new NonPlayerCharacter(false));

            switchBoardsOnePlayer();
        }
        if (nrOfPlayers == 2) {*/
            realPlayers.add(new RealPlayer(true));
            realPlayers.add(new RealPlayer(false));

            switchBoardsTwoPlayers();
        //}
    }

    // switch the addresses
    private void switchBoardsTwoPlayers() {
        int[][] availableOptionsBoard = realPlayers.get(0).getBoard().getAvailableOptionsBoard();
        int[][] shipLocationsBoard = realPlayers.get(0).getBoard().getShipLocationsBoard();


        realPlayers.get(0).getBoard().setAvailableOptionsBoard(realPlayers.get(1).getBoard().getAvailableOptionsBoard());
        realPlayers.get(0).getBoard().setShipLocationsBoard(realPlayers.get(1).getBoard().getShipLocationsBoard());

        realPlayers.get(1).getBoard().setAvailableOptionsBoard(availableOptionsBoard);
        realPlayers.get(1).getBoard().setShipLocationsBoard(shipLocationsBoard);
    }

    private void switchBoardsOnePlayer() {
        int[][] availableOptionsBoard = realPlayers.get(0).getBoard().getAvailableOptionsBoard();
        int[][] shipLocationsBoard = realPlayers.get(0).getBoard().getShipLocationsBoard();


        realPlayers.get(0).getBoard().setAvailableOptionsBoard(nonPlayerCharacters.get(0).getBoard().getAvailableOptionsBoard());
        realPlayers.get(0).getBoard().setShipLocationsBoard(nonPlayerCharacters.get(0).getBoard().getShipLocationsBoard());

        nonPlayerCharacters.get(0).getBoard().setAvailableOptionsBoard(availableOptionsBoard);
        nonPlayerCharacters.get(0).getBoard().setShipLocationsBoard(shipLocationsBoard);
    }

    public void startGame(){
        if (nrOfPlayers == 1){
            startSinglePlayerGame();
            printWinnerMessage(realPlayers.get(0).getBoard().getWinner());
        }

        if (nrOfPlayers == 2) {
            startTwoPlayersGame();
            printWinnerMessageTwoPlayers(realPlayers.get(0).getBoard().getWinner());
        }

    }

    private void startSinglePlayerGame() {
        while (realPlayers.get(0).getBoard().getDestroyedBlocks() < 20 && nonPlayerCharacters.get(0).getBoard().getDestroyedBlocks() < 20) {

            if (!nonPlayerCharacters.get(0).getBoard().getTurn()) {
                realPlayers.get(0).chooseLocation();
                realPlayers.get(0).getBoard().showBoardWithoutShips();
            }

            if (!realPlayers.get(0).getBoard().getTurn()){
                nonPlayerCharacters.get(0).chooseLocation();
                nonPlayerCharacters.get(0).getBoard().showBoardWithoutShips();
            }
        }
    }

    private void startTwoPlayersGame() {
        while (realPlayers.get(0).getBoard().getDestroyedBlocks() < 20 && realPlayers.get(1).getBoard().getDestroyedBlocks() < 20) {

            if (!realPlayers.get(1).getBoard().getTurn()) {
                realPlayers.get(0).chooseLocation();
                realPlayers.get(0).getBoard().showBoardWithoutShips();
            }

            if (!realPlayers.get(0).getBoard().getTurn()){
                realPlayers.get(1).chooseLocation();
                realPlayers.get(1).getBoard().showBoardWithoutShips();
            }
        }
    }

    private void printWinnerMessage(boolean winner) {

        if (winner){
            System.out.println("Player " + realPlayers.get(0).getPlayerNickname()+ " won!!! Congratulations!");
        } else {
            System.out.println("You lost. Better luck next time!");
        }
    }

    private void printWinnerMessageTwoPlayers(boolean winner) {

        if (winner){
            System.out.println("Player " + realPlayers.get(0).getPlayerNickname()+ " won!!! Congratulations!");
        } else {
            System.out.println("Player " + realPlayers.get(1).getPlayerNickname()+ " won!!! Congratulations!");
        }
    }

    @Deprecated
    private void setNumberOfPlayers() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Please introduce the number of players:");

        while (true) {
            try {
                int players = reader.nextInt();
                if(players > 2 || players < 1) {
                    throw new IllegalArgumentException("Invalid number of players! Please try again!");
                }
                nrOfPlayers = players;
                return;
            } catch (IllegalArgumentException illegalArgumentException) {

                System.out.println(illegalArgumentException.getMessage());

            } catch (InputMismatchException inputMismatchException) {

                System.out.println("The introduced characters must be integer (1 or 2)!");
                reader.next();
            }

            System.out.println("Please introduce the number of players again:");
        }
    }
}
