
import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;


import javax.swing.JOptionPane;

/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

/**
 * This class is a model for TicTacToe.  
 * 
 * This game adheres to a Model-View-Controller design framework.
 * This framework is very effective for turn-based games.  We
 * STRONGLY recommend you review these lecture slides, starting at
 * slide 8, for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * This model is completely independent of the view and controller.
 * This is in keeping with the concept of modularity! We can play
 * the whole game from start to finish without ever drawing anything
 * on a screen or instantiating a Java Swing object.
 * 
 * Run this file to see the main method play a game of TicTacToe,
 * visualized with Strings printed to the console.
 */
public class FiveInARow {

    private int[][] board;
    private int numTurns;
    private boolean player1; //true if player 1 false if player 2 is playing 
    private boolean gameOver;
    private List<int[][]> movesList = new LinkedList<int[][]>();
    

    /**
     * Constructor sets up game state.
     */
    public FiveInARow() {
        reset();
    }

    /**
     * playTurn allows players to play a turn. Returns true 
     * if the move is successful and false if a player tries
     * to play in a location that is taken or after the game
     * has ended. If the turn is successful and the game has 
     * not ended, the player is changed. If the turn is 
     * unsuccessful or the game has ended, the player is not 
     * changed.
     * 
     * @param c column to play in
     * @param r row to play in
     * @return whether the turn was successful
     */
    public boolean playTurn(int c, int r) {
        if (board[r][c] != 0 || gameOver) {
            return false;
        }
        if (player1) {
            board[r][c] = 1;
            int[][] board1 = new int[15][15];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board1[i][j] = board[i][j];  
                }
            }
            movesList.add(board1);
        } else {
            board[r][c] = 2;
            int[][] board1 = new int[15][15];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board1[i][j] = board[i][j];
                }
            }
            movesList.add(board1);
        }
        player1 = !player1;
        numTurns++;

        return true;
    }
    

    /**
     * checkWinner checks whether the game has reached a win 
     * condition. checkWinner only looks for horizontal wins.
     * 
     * @return 0 if nobody has won yet, 1 if player 1 has won, and
     *            2 if player 2 has won, 3 if the game hits stalemate
     */
    public int horizontalWin() {
        // Check horizontal win
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i][j + 1] &&
                    board[i][j + 1] == board[i][j + 2] &&
                    board[i][j + 2] == board[i][j + 3] &&
                    board[i][j + 3] == board[i][j + 4] 
                 
                ) {
                    gameOver = true;
                    if (player1) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        }
        if (numTurns >= 225) {
            gameOver = true;
            return 3;
        } else {
            return 0;
        }
    }
    
    
    
    public int verticalWin() {
        // Check vertical win
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i + 1][j] &&
                    board[i + 1][j] == board[i + 2][j] &&
                    board[i + 2][j] == board[i + 3][j] &&
                    board[i + 3][j] == board[i + 4][j] 
                ) {
                    gameOver = true;
                    if (player1) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        }
        if (numTurns >= 225) {
            gameOver = true;
            return 3;
        } else {
            return 0;
        }
    }
    
    //checking for diagonalWin going bottom left to top right
    public int diagonalWin1() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i + 1][j + 1] &&
                    board[i + 1][j + 1] == board[i + 2][j + 2] &&
                    board[i + 2][j + 2] == board[i + 3][j + 3] &&
                    board[i + 3][j + 3] == board[i + 4][j + 4]
                ) {
                    gameOver = true;
                    if (player1) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        }
        if (numTurns >= 225) {
            gameOver = true;
            return 3;
        } else {
            return 0;
        }
    }
           
    
    //checking for diagonalWin going top left to bottom right
    public int diagonalWin2() {
        for (int i = 4; i < board.length; i++) {
            for (int j = 0; j < board[i].length - 4; j++) {
                if (board[i][j] != 0 &&
                    board[i][j] == board[i - 1][j + 1] &&
                    board[i - 1][j + 1] == board[i - 2][j + 2] &&
                    board[i - 2][j + 2] == board[i - 3][j + 3] &&
                    board[i - 3][j + 3] == board[i - 4][j + 4]
                ) {
                    gameOver = true;
                    if (player1) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        }
        if (numTurns >= 225) {
            gameOver = true;
            return 3;
        } else {
            return 0;
        }
    }
    
    public void checkWinner() {
        if (diagonalWin1() == 1 || diagonalWin2() == 1 || 
                verticalWin() == 1 || horizontalWin() == 1) {
            whiteWin();
        } else if (diagonalWin1() == 2 || diagonalWin2() == 2 || 
                verticalWin() == 2 || horizontalWin() == 2) {
            blackWin();
        }
    }

    private void whiteWin() {
        JOptionPane.showMessageDialog(null, "White Wins!");
    }

    private void blackWin() {
        JOptionPane.showMessageDialog(null, "Black Wins!");
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("\n\nTurn " + numTurns + ":\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length) { 
                    System.out.print(" | "); 
                }
            }
            if (i < board.length) {
                System.out.println("\n---------"); 
            }
        }
    }
    
    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        board = new int[15][15];
        numTurns = 0;
        player1 = true;
        gameOver = false;
        movesList = (new LinkedList<int[][]>());
    }
    
    /**
     * getCurrentPlayer is a getter for the player
     * whose turn it is in the game.
     * 
     * @return true if it's Player 1's turn,
     * false if it's Player 2's turn.
     */
    public boolean getCurrentPlayer() {
        return player1;
    }
    
    public List<int[][]> getMoves() {   
        return movesList;
    }
    
    public int[][] getCurrentBoard() {
        return board;
    }
    
    public int[][] removeLastMove() {
        movesList.remove(movesList.size() - 1);
        board = movesList.get(movesList.size() - 1);
        return board;
    }
    
   
    
    public void saveBoard(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException();
        }
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < board.length; i++) {
                String line = "";
                for (int j = 0; j < board[i].length; j++) {
                    line = line + board[i][j]; 
                }
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            String player = "";
            if (getCurrentPlayer()) {
                player = "player 1 to play";
            } else {
                player = "player 2 to play";
            }
            bufferedWriter.write(player);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("No more lines to write");
        } finally {
            System.out.println();
        }
    }
    
    public void loadBoard(String filename) throws IOException {
        if (filename == null) {
            throw new IllegalArgumentException();
        }
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int i = 0;
            while (line != null && i < 15) {
                String[] array = line.split("");
                for (int j = 0; j < 15; j++) {
                    board[i][j] = Integer.parseInt(array[j]);
                }
                line = bufferedReader.readLine();
                i++;
            }
            String player = bufferedReader.readLine();
            if (player == "player 1 to play") {
                getCurrentPlayer();
            } 
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("no such file");
        }
    }
    /**
     * getCell is a getter for the contents of the
     * cell specified by the method arguments.
     * 
     * @param c column to retrieve
     * @param r row to retrieve
     * @return an integer denoting the contents
     *         of the corresponding cell on the 
     *         game board.  0 = empty, 1 = Player 1,
     *         2 = Player 2
     */
    public int getCell(int c, int r) {
        return board[r][c];
    }
    
    
    
    /**
     * This main method illustrates how the model is
     * completely independent of the view and
     * controller.  We can play the game from start 
     * to finish without ever creating a Java Swing 
     * object.
     * 
     * This is modularity in action, and modularity 
     * is the bedrock of the  Model-View-Controller
     * design framework.
     * 
     * Run this file to see the output of this
     * method in your console.
     */
    
    // can play game without view
    // but game should be played in game
    public static void main(String[] args) {
        FiveInARow t = new FiveInARow();

        t.playTurn(1, 1);
        t.printGameState();
        
        t.playTurn(0, 0);
        t.printGameState();

        t.playTurn(0, 2);
        t.printGameState();
        
        t.playTurn(2, 0);
        t.printGameState();

        t.playTurn(1, 0);
        t.printGameState();
        
        t.playTurn(1, 2);
        t.printGameState();
        
        t.playTurn(0, 1);
        t.printGameState();
        
        t.playTurn(2, 2);
        t.printGameState();
        
        t.playTurn(2, 1);
        t.printGameState();
        
    }

   
}
