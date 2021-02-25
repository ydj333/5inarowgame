=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. As the game is played on a 15 by 15 grid, I can represent that as a 2D array, 
  with 1 dimension representing columns and the other representing rows. Furthermore, 
  this will be a 2D integer array, as each row and each column will be each stored as
  a number representing the coordinates on the board. I changed this implementation
  a little bit from what I originally planned, because as I began writing the code
  I realised that using the int array to store the x and y locations of the moves was
  probably more logical and definitely more easier for me to implement.

  2. In order to allow for a player to undo their last move, I will store the moves 
  in a collection so that I have a list of moves, where the last element in the list
  represents the last move. I will be using a LinkedList Collection, because order is 
  important, as a user must be able to undo their moves in order. Furthermore, I changed 
  my implementation by allowing a user to undo more than 1 move, in order to satisfy 
  the grading rubric. Lastly, the collection will be a list of (x,y) coordinates, where 
  the (x,y) coordinates are contained in a 2D int array.

  3. By using File I/O, the user will be able to pause the game and then return to it at 
  a later time. When the game is paused, the state of the 2D array (representing the game 
  board), and the player whose turn it is to play (the second state) will be written in a 
  text file, and when a user loads the game, they will be able to continue playing from 
  where they left off because the text file can be read to restore the game to the previous 
  state. I think that storing these 2 states make the most sense, because 5 in a row is 
  usually not timed, so really storing the game board and the player whose turn it is are 
  the only 2 things that make a lot of sense.

  4. I think that the most beneficial thing to test about this game is whether or not the 
  2D array updates the board correctly. As the placing of pieces is the most important 
  component of this game. Edge cases would involve one user trying to put a piece somewhere 
  where a piece already exists, or if the user is trying to place the piece somewhere that 
  is not specified by the 2D array, such as clicking somewhere out of bounds (in that case 
  nothing should happen). Other important methods to test would be to see if a winner is 
  being found correctly, and if methods such as removeLastMove() (methods that I implemented
  and are fundamental to a component of the game) are implemented correctly.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  The Game class is rather straightforward. The GUI components of the game (such as the buttons
  and the displaying of the game) are all handled in game. I also create a button, that when
  pressed, gives the instructions on how to play.
  
  In GameBoard, the most important methods are paintComponent, as this actually paints the 
  board and is in charge of making sure that white and black pieces appear alternately at the
  correct locations on the board, and the mouseclick listener, because this handles how the game
  handles the event where a mouse clicks on the board, which is vital for playing functionality.
  I actually had a lot of trouble with these 2 methods, as I kept having problems displaying the
  pieces in the correct locations. Other than that, the class is also very important as it deals
  with other changes to the game's state and allows them to be eventually displayed on screen. 
  For example, load(), save(), and undo() are all in this class.
  
  FiveInARow is the class that handles the core game state, without worrying about the GUI
  component. As a result, it handles vital problems such as checking if there is a winner, and if
  there is a winner, who the winner is. Furthermore, it also handles the switching and playing of
  a turn, and the BufferedReader and BufferedWriters for the load and save functionality are also 
  found in this class. They are implmented in this class and then for the on screen game, methods
  in GameBoard call on them.
  
  I also have a txt file called savedFile. This is just an empty text file that BufferedWriter 
  writes to when saving a game. The first 15 lines represent the game board, and the last line
  represents whose turn it is to play. Furthermore, BufferedReader is set to read from this file
  when loading the game (this also means that a user does not need to specify which file they want
  to save to, and this makes sense because I think it makes sense to only want to save 1 game at a
  time).
  
  Finally, the GameTest class is where I test my project. In this class, I focus on testing the vital
  components for a successful 5 in a row game, such as making sure that the board is being updated
  correctly, or making sure that a correct winner is found when it is supposed to be found. 


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  I think my design was fine overall, and I did not have to make too many modifications to that. 
  However I did have a lot of trouble implementing some parts of the game. For instance, figuring
  out the math and how to get the pieces to display at the correct point took me many hours (felt
  like forever), and having undo successfully undo also took me a long time. At first, calling undo
  wasn't doing anything to the board (wasn't removing the last piece), although I finally was able
  to fix that. So overall I'd say while I did face many challenges when writing the code, I thought
  my design was good. 


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  For the most part, I think there is a good separation of functionality. My GUI related functionality
  is handled by Game and GameBoard (I feel that GameBoard acts like sort of a bridge between Game and 
  FiveInARow), and then FiveInARow is responsible for handling a lot of the core game logic/state. I
  was running a little tight on time towards the end, so I know that there are definitely some bugs in
  my code, and so that would be what I would focus on fixing when given the chance.



========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  I consulted the Finally Block Tutorial from the Oracle Java Documentation website.
  Other than that and looking at a few javadocs (e.g the one for lists) for ideas of methods 
  that I could use, I did not consult any outside resources. Piazza and office hours 
  helped a lot though!
