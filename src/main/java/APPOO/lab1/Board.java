package APPOO.lab1;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Dennis on 29-Apr-17.
 **/
public class Board {

    private List<Ship> ships = new ArrayList<>();

    private int[][] availableOptionsBoard = new int[12][12]; // used to identify available options when generating ships
    private int[][] shipLocationsBoard = new int[12][12]; // used to store the information about actual state of Board

    private int destroyedBlocks = 0;
    private boolean winner = false;

    private int startPosX, startPosY, endPosX, endPosY;
    private boolean myTurn;

    Board(){
        generateAllShips();
    }

    private void generateAllShips() {

        generateShips(4, 1);
        generateShips(3, 2);
        generateShips(2, 3);
        generateShips(1, 4);
    }

    private void generateShips(int size, int quantity) {

        for (int i = 0; i < quantity; i++) {
            Ship ship = new Ship();
            ship.setSize(size);

            int[] location = new int[2];
            location = getValidLocation(location);

            int[][] shipLocation = getFullShip(location, size);

            while (shipLocation == null) {
                location = getValidLocation(location);
                shipLocation = getFullShip(location, size);
            }

            updateBoard(shipLocation);

            ship.setUsedLocations(shipLocation);
            ships.add(ship);
        }
    }

    private int[] getValidLocation(int[] location) {

        location[0] = generateRandomInt();
        location[1] = generateRandomInt();

        while (!validateLocation(location)) {

            location[0] = generateRandomInt();
            location[1] = generateRandomInt();
        }

        return location;
    }

    //TODO refactor, add random feature
    public int[][] getFullShip(int[] validLocation, int size) {

        int[] vars = {1, 2, 3, 4};
        int[] shuffledVars = shuffleArray(vars);
        //System.out.println("Here: " + shuffledVars[0] + " " + shuffledVars[1]+ " " + shuffledVars[2]+ " " + shuffledVars[3]);

        int[][] shipLocation = new int[size][2];
        shipLocation[0] = validLocation.clone();

        for (int j = 0; j < 4; j++) {
            switch (shuffledVars[j]) {

                case 1:
                    int[] lctn = validLocation.clone();
                    int check = 1;

                    for (int i = 1; i < size; i++) {
                        if (validateIndex(validLocation[0] + i)) {
                            lctn[0] = validLocation[0] + i;

                            if (validateLocation(lctn)) {
                                shipLocation[i] = lctn.clone();
                                check++;
                            }
                        }
                    }

                    if (check == size) {
                        return shipLocation;
                    }

                case 2:
                    lctn = validLocation.clone();
                    check = 1;

                    for (int i = 1; i < size; i++) {
                        if (validateIndex(validLocation[0] - i)) {
                            lctn[0] = validLocation[0] - i;

                            if (validateLocation(lctn)) {
                                shipLocation[i] = lctn.clone();
                                check++;
                            }
                        }
                    }

                    if (check == size) {
                        return shipLocation;
                    }

                case 3:
                    lctn = validLocation.clone();
                    check = 1;

                    for (int i = 1; i < size; i++) {
                        if (validateIndex(validLocation[1] + i)) {
                            lctn[1] = validLocation[1] + i;

                            if (validateLocation(lctn)) {
                                shipLocation[i] = lctn.clone();
                                check++;
                            }
                        }
                    }

                    if (check == size) {
                        return shipLocation;
                    }

                case 4:
                    lctn = validLocation.clone();
                    check = 1;

                    for (int i = 1; i < size; i++) {
                        if (validateIndex(validLocation[1] - i)) {
                            lctn[1] = validLocation[1] - i;

                            if (validateLocation(lctn)) {
                                shipLocation[i] = lctn.clone();
                                check++;
                            }
                        }
                    }

                    if (check == size) {
                        return shipLocation;
                    }
            }
        }
        return null;
    }

    public boolean validateLocation(int[] location) {
        setupPositions(location[0], location[1]);

        return availableOptionsBoard[location[0]][location[1]] == 0 && validateIndex(location[0]) && validateIndex(location[1]);
    }

    public boolean validateLocationForAttck(int[] location) {
        setupPositions(location[0], location[1]);

        return (shipLocationsBoard[location[0]][location[1]] == 0 || shipLocationsBoard[location[0]][location[1]] == 1) && validateIndex(location[0]) && validateIndex(location[1]);
    }

    private boolean validateIndex(int index) {

        return index >= 1 && index <=10;
    }

    private void updateBoard(int[][] shipLocation) {

        for (int i = 0; i < shipLocation.length; i++) {
            int row = shipLocation[i][0];
            int column = shipLocation[i][1];

            setupPositions(row, column);

            for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
                for (int colNum = startPosY; colNum <= endPosY; colNum++) {
                    availableOptionsBoard[rowNum][colNum] = 1;
                }
            }

            shipLocationsBoard[row][column] = 1;
            //System.out.print(row + "_" + column + " ");
        }
    }

    public void updateBoardAfterAttack(int[] shipLocation) {

            int row = shipLocation[0];
            int column = shipLocation[1];

            if (shipLocationsBoard[row][column] == 0) {
                shipLocationsBoard[row][column] = 2;
                myTurn = false;

            }
            if (shipLocationsBoard[row][column] == 1) {
                shipLocationsBoard[row][column] = 3;
                destroyedBlocks ++;
                myTurn = true;

                if(destroyedBlocks == 20){
                    winner = true;
                }
                //System.out.print(destroyedBlocks);
            }

    }

    private void setupPositions(int row, int column) {
        startPosX = (row - 1 < 0) ? row : row-1;
        startPosY = (column - 1 < 0) ? column : column-1;
        endPosX =   (row + 1 > 11) ? row : row+1;
        endPosY =   (column + 1 > 11) ? column : column+1;
    }

    private int generateRandomInt() {
        return ThreadLocalRandom.current().nextInt(1, 11);
    }

    public int[][] getBoard() {
        return availableOptionsBoard;
    }

    public int[][] getBoard1() {
        return shipLocationsBoard;
    }

    public void showBoard(String message){

        System.out.println(message);
        final int[][] matrix = getBoard1();

        printColumnEdge();
        for (int i = 1; i < matrix.length -1; i++) {
            printRowEdge(i);
            for (int j = 1; j < matrix[i].length -1; j++) {
                if (matrix[i][j] == 0){

                    System.out.print("__|");
                } if (matrix[i][j] == 2){

                    System.out.print("**|");
                } if (matrix[i][j] == 3){

                    System.out.print("XX|");
                } if (matrix[i][j] == 1){

                    System.out.print("##|");
                }
            }
            System.out.println();
        }

        System.out.println("\n\n");
    }

    public void showBoardWithoutShips(){
        final int[][] matrix = getBoard1();

        printColumnEdge();
        for (int i = 1; i < matrix.length -1; i++) {
            printRowEdge(i);
            for (int j = 1; j < matrix[i].length -1; j++) {
                if (matrix[i][j] == 0 || matrix[i][j] == 1){

                    System.out.print("__|");
                } if (matrix[i][j] == 2){

                    System.out.print("**|");
                } if (matrix[i][j] == 3) {

                    System.out.print("XX|");
                }
            }
            System.out.println();
        }

        System.out.println("\n\n");
    }

    public int getDestroyedBlocks() {
        return destroyedBlocks;
    }

    private void printRowEdge(int indexValue) {
        if (indexValue<10){
            System.out.print(indexValue + "   |");
        }
        else {
            System.out.print(indexValue + "  |");
        }
    }

    private void printColumnEdge() {
        System.out.println("     1  2  3  4  5  6  7  8  9  10");
        System.out.println("     __ __ __ __ __ __ __ __ __ __");
    }

    public boolean getWinner() {
        return winner;
    }

    public boolean getTurn() {
        return myTurn;
    }

    public void initTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public int[][] getAvailableOptionsBoard(){
        return availableOptionsBoard;
    }

    public void setAvailableOptionsBoard(int[][] availableOptionsBoard){
        this.availableOptionsBoard = availableOptionsBoard;
    }

    public int[][] getShipLocationsBoard() {
        return shipLocationsBoard;
    }

    public void setShipLocationsBoard(int[][] shipLocationsBoard) {
        this.shipLocationsBoard = shipLocationsBoard;
    }

    private static int[] shuffleArray(int[] array)
    {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }

        return array;
    }
}
