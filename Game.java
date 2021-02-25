/**
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import java.awt.*;

import java.awt.event.*;


import javax.swing.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework.  This
 * framework is very effective for turn-based games.  We STRONGLY 
 * recommend you review these lecture slides, starting at slide 8, 
 * for more details on Model-View-Controller:  
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard.  The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class Game implements Runnable {
    public void run() {
        // NOTE: the 'final' keyword denotes immutability even for local variables.

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("5 in a Row");
        frame.setLocation(500, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);
        
        //add buttons for player options here (for south)
        
        //need to create east and west as well
        final JPanel buttons = new JPanel();
        frame.add(buttons, BorderLayout.EAST);
        
        final JPanel buttons2 = new JPanel();
        frame.add(buttons2, BorderLayout.WEST);
        
        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);
        
        board.setBackground(new Color(145, 135, 125));

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("New Game");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        control_panel.add(reset);
        
        final JButton save = new JButton("Save Game");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.saveGame();
            }
        });
        control_panel.add(save);
        
        final JButton load = new JButton("Resume");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    board.loadGame();
            }
        });
        control_panel.add(load);
        
        final JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.undo();
            }
        });
        
        control_panel.add(undo);
        
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "This is a traditional 5 in a row game."
                        + "\nIn order to win, connect 5 of your pieces before the opponent does."
                        + "\nThis can be done vertically, horizontally, or diagonally."
                        + "\nThe first player to go will be black, "
                        + "and the second player will be white"
                        + "\nThe pieces are placed on the intersections of the board lines."
                        + "\nHave fun and good luck!");
            }
        });
        buttons.add(instructions);
        
        

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}