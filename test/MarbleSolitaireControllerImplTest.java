import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import server.controller.MarbleSolitaireController;
import server.controller.MarbleSolitaireControllerImpl;
import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.view.MarbleSolitaireTextView;
import server.view.MarbleSolitaireView;
import mocks.InvalidReadable;
import mocks.MarbleSolitaireMockModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents the tests for all operations supported by the
 * MarbleSolitaireControllerImpl class.
 */
public class MarbleSolitaireControllerImplTest {
  private MarbleSolitaireModel m;
  private MarbleSolitaireView v;
  private MarbleSolitaireController c;
  private Readable input;
  private Readable inputGameOver;
  private StringBuilder output;
  private MarbleSolitaireModel mock;
  private StringBuilder log;
  private int uniqueCode;

  // sets up initial examples to use for testing
  @Before
  public void init() {
    this.inputGameOver = new StringReader("2 4 4 4 5 4 3 4 4 6 4 4 4 3 4 5 4 1 4 3 7 4 5 4");
    this.output = new StringBuilder();
    this.m = new EnglishSolitaireModel();
    this.v = new MarbleSolitaireTextView(this.m, this.output);
    this.log = new StringBuilder();
  }

  // tests invalid constructors for the MarbleSolitaireControllerImpl class
  @Test
  public void testInitInvalid() {
    try {
      this.input = new StringReader("");
      this.c = new MarbleSolitaireControllerImpl(null, this.v, this.input);
      fail("Doesn't throw an IllegalArgumentException when model parameter is null.");
    } catch (IllegalArgumentException e) {
      // successfully catches the error thrown when the MarbleSolitaireModel parameter is null
    }

    try {
      this.c = new MarbleSolitaireControllerImpl(this.m, this.v, null);
      fail("Doesn't throw an IllegalArgumentException when input parameter is null.");
    } catch (IllegalArgumentException e) {
      // successfully catches the error thrown when the Readable input parameter is null
    }

    try {
      this.input = new StringReader("");
      this.c = new MarbleSolitaireControllerImpl(this.m, null, this.input);
      fail("Doesn't throw an IllegalArgumentException when the view parameter is null.");
    } catch (IllegalArgumentException e) {
      // successfully catches the error thrown when the MarbleSolitaireView parameter is null
    }
  }

  // tests if the game starts properly
  @Test
  public void testStartGame() {
    this.input = new StringReader("q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertEquals("", this.output.toString());
    this.c.playGame();
    String[] startAndQuit = this.output.toString().split("Game quit!");
    String start = startAndQuit[0];
    assertEquals("Welcome to Marble Solitaire!\n" // this.v.toString() + "\n"
            + "Press 'q' or 'Q' to quit the game at any time.\n"
            + "Play Game Here:\n"
            + "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", start);
  }

  // tests and confirms the inputs for a valid move in the game
  @Test
  public void testConfirmInputsValidMove() {
    this.input = new StringReader("4 2 4 4 q");
    this.uniqueCode = 123;
    this.mock = new MarbleSolitaireMockModel(this.uniqueCode, this.log);
    this.c = new MarbleSolitaireControllerImpl(this.mock, this.v, this.input);
    assertEquals("", this.log.toString());
    c.playGame();
    assertEquals("fromRow: 3, fromCol: 1, toRow: 3, toCol: 3", this.log.toString());
  }

  // tests a valid move and its expected output
  @Test
  public void testValidMove() {
    this.input = new StringReader("2 4 4 4 q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertFalse(this.output.toString().contains("Score: 31"));
    c.playGame();
    assertTrue(this.output.toString().contains("Score: 31"));
    String[] full = this.output.toString().split("\nGame quit!");
    String startAndMove = full[0];
    assertEquals("Welcome to Marble Solitaire!\n" +
            "Press 'q' or 'Q' to quit the game at any time.\n" +
            "Play Game Here:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31", startAndMove);
  }

  // tests an interrupted valid move
  @Test
  public void testInterruptedValidMove() {
    this.input = new StringReader("6 4 4 -4 4 q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertEquals("", this.output.toString());
    c.playGame();
    String[] message = this.output.toString().split("\n");
    StringBuilder isoOutput = new StringBuilder();
    for (int i = 0; i < 21; i++) {
      isoOutput.append(message[i]).append("\n");
    }
    assertEquals("Welcome to Marble Solitaire!\n" +
            "Press 'q' or 'Q' to quit the game at any time.\n" +
            "Play Game Here:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid number input. Try again.\n" +
            "Pass in an integer greater than zero.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n", isoOutput.toString());
  }

  // tests and confirms the inputs for an invalid move in the game
  @Test
  public void testConfirmInputsInvalidMove() {
    this.input = new StringReader("3  a 84 fs 5 -3 39 q");
    this.uniqueCode = 123;
    this.mock = new MarbleSolitaireMockModel(this.uniqueCode, this.log);
    this.c = new MarbleSolitaireControllerImpl(this.mock, this.v, this.input);
    assertEquals("", this.log.toString());
    c.playGame();
    assertEquals("fromRow: 2, fromCol: 83, toRow: 4, toCol: 38", this.log.toString());
  }

  // tests an invalid move and its expected output
  @Test
  public void testInvalidMove() {
    this.input = new StringReader("3 84 5 39 q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertFalse(this.output.toString().contains("Invalid move. Play again and try a new move.\n"));
    assertEquals("", this.output.toString());
    c.playGame();
    assertTrue(this.output.toString().contains("Invalid move. Play again and try a new move.\n"));
    assertEquals("Welcome to Marble Solitaire!\n" +
            "Press 'q' or 'Q' to quit the game at any time.\n" +
            "Play Game Here:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again and try a new move.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());
  }

  // tests an invalid interrupted move
  @Test
  public void testInterruptedInvalidMove() {
    this.input = new StringReader("4 5 hotdiggitydawg 8 4 q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertEquals("", this.output.toString());
    c.playGame();
    assertEquals("Welcome to Marble Solitaire!\n" +
            "Press 'q' or 'Q' to quit the game at any time.\n" +
            "Play Game Here:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid string input. Try again.\n" +
            "Must pass in 'q' or 'Q' to quit.\n" +
            "Invalid move. Play again and try a new move.\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", this.output.toString());
  }

  // tests the expected output for symbols that are either a negative number or not "q"
  @Test
  public void testAllInvalidInputs() {
    this.input = new StringReader("2 -4 gfds 5 ! -203 4 2.0 8 q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertEquals("", this.output.toString());
    c.playGame();
    String[] full = this.output.toString().split("\n");
    String negativeError1 = full[11] + "\n" + full[12];
    String stringError = full[13] + "\n" + full[14];
    String symbolError = full[15] + "\n" + full[16];
    String negativeError2 = full[17] + "\n" + full[18];
    String decimalError = full[19] + "\n" + full[20];
    String invalidMoveError = full[21];
    assertEquals("Invalid number input. Try again.\n" +
            "Pass in an integer greater than zero.", negativeError1);
    assertEquals("Invalid string input. Try again.\n" +
            "Must pass in 'q' or 'Q' to quit.", stringError);
    assertEquals("Invalid string input. Try again.\n" +
            "Must pass in 'q' or 'Q' to quit.", symbolError);
    assertEquals("Invalid number input. Try again.\n" +
            "Pass in an integer greater than zero.", negativeError2);
    assertEquals("Invalid string input. Try again.\n" +
            "Must pass in 'q' or 'Q' to quit.", decimalError);
    assertEquals("Invalid move. Play again and try a new move.", invalidMoveError);
  }

  // tests if the game quits properly when entering "q" (lower case)
  @Test
  public void testLowerCaseQuitGame() {
    this.input = new StringReader("3 4 q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertEquals("", this.output.toString());
    this.c.playGame();
    String[] startAndQuit = this.output.toString().split("\n");
    StringBuilder quit = new StringBuilder();
    for (int i = 11; i < 21; i++) {
      quit.append(startAndQuit[i]).append("\n");
    }
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n", quit.toString());

  }

  // tests if the game quits properly when entering "Q" (upper case)
  @Test
  public void testUpperCaseQuitGame() {
    this.input = new StringReader("2 4 4 4 Q");
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
    assertEquals("", this.output.toString());
    this.c.playGame();
    String[] startAndQuit = this.output.toString().split("\n");
    StringBuilder quit = new StringBuilder();
    for (int i = 19; i < 29; i++) {
      quit.append(startAndQuit[i]).append("\n");
    }
    assertEquals("Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n", quit.toString());
  }

  // tests the playGame method when a series of moves are made to end the game
  @Test
  public void testGameOver() {
    this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.inputGameOver);
    assertEquals("", this.output.toString());
    this.c.playGame();
    assertEquals("Welcome to Marble Solitaire!\n" +
            "Press 'q' or 'Q' to quit the game at any time.\n" +
            "Play Game Here:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O O _ _ O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O _ _ O _ O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26\n" +
            "Game over!\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "Score: 26\n", this.output.toString());
  }

  // tests when the readable object has runs out of inputs
  @Test
  public void testNoRemainingInputs() {
    try {
      this.input = new StringReader("");
      this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
      this.c.playGame();
      fail("Fails when there are no more inputs.");
    } catch (IllegalStateException e) {
      // successfully throws an error when there are no more inputs
      // without quitting and the game has not yet finished
    }

    try {
      this.input = new StringReader("4 4 2 4");
      this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
      this.c.playGame();
      fail("Fails when there are no more inputs.");
    } catch (IllegalStateException e) {
      // successfully throws an error when there are no more inputs
      // without quitting and the game has not yet finished
    }
  }

  // tests playing the game using an invalid Readable
  @Test
  public void testInvalidReadable() {
    try {
      this.input = new InvalidReadable();
      this.c = new MarbleSolitaireControllerImpl(this.m, this.v, this.input);
      c.playGame();
      fail("Does not throw an error when the controller is given an invalid Readable.");
    } catch (IllegalStateException e) {
      // successfully throws an error when the controller is passed an invalid Readable
    }
  }
}