package server.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import server.model.hw02.MarbleSolitaireModel;
import server.view.MarbleSolitaireView;

/**
 * This class represents a controller used to play a game of Marble Solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  MarbleSolitaireModel m;
  MarbleSolitaireView v;
  Readable in;

  /**
   * The constructor for the Marble Solitaire controller which takes
   * in a model, view, and readable.
   *
   * @param m  the model for the MarbleSolitaire game
   * @param v  the view for the Marble Solitaire game
   * @param in the Readable object passed in by the user
   * @throws IllegalArgumentException if any of the inputs (m, v, or in) are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel m, MarbleSolitaireView v, Readable in)
          throws IllegalArgumentException {
    if (m == null || v == null || in == null) {
      throw new IllegalArgumentException("One or many of these controller's parameters are null.");
    }

    this.m = m;
    this.v = v;
    this.in = in;
  }

  /**
   * This method is used in the controller class to play a game of Marble Solitaire.
   * playGame properly communicates between the model and view fields of this controller,
   * supporting the operations of the model and displaying the game and any messages as needed.
   * In particular, this game should...
   * - properly scan and read user inputs
   * - initialize a proper starting board and screen
   * - move a marble and show its appropriate changes
   * - reject and produce a message for an invalid input including:
   * - negative numbers
   * - any letters that are not "q" or "Q"
   * - any other symbols
   * - produce a message when attempting an invalid move with four positive integers
   * - produce an appropriate display when the game is over
   * - quit the game and show the ending state of the game when passed in "q" or "Q"
   *
   * @throws IllegalStateException if the controller is unable to successfully
   *                               read input or transmit outputs, or if there are no more inputs
   *                               before the game is over or the user quits
   */
  @Override
  public void playGame() throws IllegalStateException {
    try {
      if (m.isGameOver()) {
        this.gameOverMessage();
      }
      // else start the game
      else {
        this.welcomeMessage();
        this.playGameHelper();
      }
    } catch (IOException e) {
      throw new IllegalStateException("Invalid input and/or output");
    }
  }

  /**
   * The helper method for the playGame method.
   *
   * @throws IOException           when there is an invalid input and/or output
   * @throws IllegalStateException when the scanner runs out of inputs
   */
  private void playGameHelper() throws IOException, IllegalStateException {
    Scanner scan = new Scanner(this.in);
    // while scan's next value is an integer
    while (!this.m.isGameOver()) {
      ArrayList<Integer> moveSet = new ArrayList<>();
      while (moveSet.size() < 4) {
        String current;
        try {
          current = scan.next();
        } catch (NoSuchElementException e) {
          throw new IllegalStateException("There are no more inputs");
        }
        if (current.equalsIgnoreCase("q")) {
          this.v.renderMessage("Game quit!\n");
          this.v.renderMessage("State of game when quit:\n");
          this.renderBoardAndScore();
          return;
        } else {
          this.tryInputs(current, moveSet);
        }
      }
      try {
        // initialize positive input value to use for a move
        int fromRow = moveSet.get(0) - 1;
        int fromCol = moveSet.get(1) - 1;
        int toRow = moveSet.get(2) - 1;
        int toCol = moveSet.get(3) - 1;
        // make a move
        this.m.move(fromRow, fromCol, toRow, toCol);
        // render the board after a successful move
        this.renderBoardAndScore();
        // if game is over
        if (this.m.isGameOver()) {
          this.gameOverMessage();
          return;
        }
      } catch (IllegalArgumentException e) {
        // String error = e.getMessage();
        // passes message to user if passing in an invalid move
        this.v.renderMessage("Invalid move. Play again and try a new move.\n");
      }
    }
  }

  /**
   * Analyzes and does something based on the current user input.
   * Functions include:
   * - adding a valid integer to the current move set
   * - passing a message to the user for an invalid negative integer
   * - passing a message to the user for an invalid string input that is
   * not an integer or the letter 'q'
   *
   * @param userInteraction the current user's input as a string
   * @param moveSet         the array list used to store a single move
   * @throws IOException when there is an invalid input and/or output
   */
  private void tryInputs(String userInteraction, ArrayList<Integer> moveSet) throws IOException {
    int currentVal;
    try {
      currentVal = Integer.parseInt(userInteraction);
      if (currentVal > 0) {
        moveSet.add(currentVal);
      } else {
        this.v.renderMessage("Invalid number input. Try again.\n" +
                "Pass in an integer greater than zero.\n");
      }
    } catch (NumberFormatException e) {
      this.v.renderMessage("Invalid string input. Try again.\n" +
              "Must pass in 'q' or 'Q' to quit.\n");
    }
  }

  /**
   * Displays a welcome message to the user when first starting up the game.
   *
   * @throws IOException when the input and/or output value(s) are invalid.
   */
  private void welcomeMessage() throws IOException {
    this.v.renderMessage("Welcome to Marble Solitaire!\n" +
            "Press 'q' or 'Q' to quit the game at any time.\n" +
            "Play Game Here:\n");
    this.renderBoardAndScore();
  }

  /**
   * Renders the current model's board and score and using methods from
   * the interface, MarbleSolitaireView.
   *
   * @throws IOException when the input and/or output value(s) are invalid
   */
  private void renderBoardAndScore() throws IOException {
    this.v.renderBoard();
    this.v.renderMessage("\n");
    this.v.renderMessage("Score: " + this.m.getScore() + "\n");
  }

  /**
   * Displays a 'Game Over' message to the user specific to whether
   * they have won or lost the game.
   *
   * @throws IOException when the input and/or output value(s) are invalid
   */
  private void gameOverMessage() throws IOException {
    //    if (this.m.getScore() != 1) {
    //      this.v.renderMessage("Game over!\n"
    //              + "No more moves can be made.\n"
    //              + "Better luck next time!\n"
    //              + "Final state of game:\n");
    //      this.renderBoardAndScore();
    //    } else {
    //      this.v.renderMessage("Game over!\n"
    //              + "You won! Congrats Gamer!\n"
    //              + "Final state of game:\n");
    //      this.renderBoardAndScore();
    //    }
    this.v.renderMessage("Game over!\n");
    this.renderBoardAndScore();
  }
}