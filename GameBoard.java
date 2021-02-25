/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;


import java.awt.event.*;
import java.io.BufferedReader;

import java.io.IOException;

import javax.swing.*;

/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated.  Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 * 
 * This game adheres to a Model-View-Controller design framework.  This
 * framework is very effective for turn-based games.  We STRONGLY 
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with 
 * its paintComponent method and the status JLabel).
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    private FiveInARow fr; // model for the game
    private JLabel status; // current status text
    
    private int x;
    private int y;

    // Game constants
    public static final int BOARD_WIDTH = 406;
    public static final int BOARD_HEIGHT = 406;
    
    
    BufferedReader bufferedReader;
    String currentLine;

    /**
     * Initializes the game board.
     */
    public GameBoard(JLabel statusInit) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        
        fr = new FiveInARow(); // initializes model for the game
        status = statusInit; // initializes the status JLabel

        /*
         * Listens for mouseclicks.  Updates the model, then updates the game board
         * based off of the updated model.
         */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked");
                int xp = e.getX();
                int yp = e.getY();
                x = Math.round((float) (xp - 29) / 29);
                y = Math.round((float) (yp - 29) / 29);
                try {
                    fr.playTurn(x, y);
                    updateStatus(); // updates the status JLabel
                    repaint(); // repaints the game board
                } catch (ArrayIndexOutOfBoundsException i) {
                    System.out.println("Not valid move location");
                }
                }
            });
    }

    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        fr.reset();
        status.setText("Player 1's Turn");
        repaint();

        // Makes sure this component has keyboard/mouse focus
        requestFocusInWindow();
    }

    /**
     * Updates the JLabel to reflect the current state of the game.
     */
    private void updateStatus() {
        if (fr.getCurrentPlayer()) {
            status.setText("Player 1's Turn");
        } else {
            status.setText("Player 2's Turn");
        }
        fr.checkWinner();
    }

    /**
     * Draws the game board.
     * 
     * There are many ways to draw a game board.  This approach
     * will not be sufficient for most games, because it is not 
     * modular.  All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper 
     * methods.  Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draws board grid  
        for (int i = 0; i < 15; i++) {
            g.drawLine(29 * i, 0, 29 * i, BOARD_WIDTH);
            g.drawLine(0, 29 * i, BOARD_HEIGHT, 29 * i);
        }
        
        // Placing pieces on the board
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int state = fr.getCell(i, j); 
                if (state == 1) {
                    g.setColor(Color.black);
                    g.fillOval(i * 29 + 22, j * 29 + 22, 15, 15);
                } else if (state == 2) {
                    g.setColor(Color.white);
                    g.fillOval(i * 29 + 22, j * 29 + 22, 15, 15);
                } 
            }
        }
    } 
    
    
    public void saveGame() {
        fr.saveBoard("files/savedFile.txt");
        System.out.println("Game saved succesfully");
    }
    
    public void loadGame() {
        try {
            fr.loadBoard("files/savedFile.txt");
            System.out.println("Game loaded succesfully");
        } catch (IOException e) {
            status.setText("No game is saved");
        }
        repaint();
        requestFocusInWindow();
    }
    
    public void undo() {
        try {
            fr.removeLastMove();
            repaint();
            requestFocusInWindow();
        } catch (NullPointerException e) {
            System.out.println("No moves to undo");
        }
    }
        
    
    
    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}