import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import server.model.hw02.EnglishSolitaireModel;
import server.model.hw04.AbstractSolitaireModel;
import server.model.hw04.EuropeanSolitaireModel;
import server.model.hw04.TriangleSolitaireModel;
import server.view.AbstractMarbleSolitaireTextView;
import server.view.MarbleSolitaireTextView;
import server.view.TriangleSolitaireTextView;
import mocks.InvalidAppendable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * represents all the tests for methods supported
 * in the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {
  private AbstractSolitaireModel m1;
  private AbstractSolitaireModel m2;
  private AbstractMarbleSolitaireTextView v1;
  private AbstractMarbleSolitaireTextView v2;
  private AbstractMarbleSolitaireTextView v3;
  private AbstractMarbleSolitaireTextView v4;
  private AbstractMarbleSolitaireTextView v5;

  private AbstractMarbleSolitaireTextView newV;
  private Appendable a;

  // sets up initial examples to use for testing
  @Before
  public void init() {
    this.m1 = new EnglishSolitaireModel();
    this.v1 = new MarbleSolitaireTextView(this.m1);
    this.m2 = new EnglishSolitaireModel(5);
    this.v2 = new MarbleSolitaireTextView(m2);
    AbstractSolitaireModel m3 = new EnglishSolitaireModel(4, 6);
    this.v3 = new MarbleSolitaireTextView(m3);
    this.a = new StringBuilder();
    this.newV = new MarbleSolitaireTextView(this.m1, this.a);
    AbstractSolitaireModel m4 = new EuropeanSolitaireModel(3);
    this.v4 = new MarbleSolitaireTextView(m4);
    AbstractSolitaireModel m5 = new TriangleSolitaireModel(5);
    this.v5 = new TriangleSolitaireTextView(m5);
  }

  // sets up initial examples with invalid arguments
  @Test
  public void initInvalidTextViews() {
    try {
      this.v1 = new MarbleSolitaireTextView(null);
      fail("\"Did not throw an IllegalArgumentException for a null model parameter.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a null
      // MarbleSolitaireModelState parameter
    }

    try {
      this.a = new StringBuilder();
      this.newV = new MarbleSolitaireTextView(null, this.a);
      fail("Did not throw an IllegalArgumentException for a null model parameter.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a null
      // MarbleSolitaireModelState and valid appendable parameter
    }

    try {
      this.a = new StringBuilder();
      this.newV = new MarbleSolitaireTextView(this.m1, null);
      fail("Did not throw an IllegalArgumentException for a null appendable parameter.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a valid
      // MarbleSolitaireModelState parameter and null appendable object
    }
  }

  // tests the toString method
  @Test
  public void testToString() {
    String v1String = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";
    assertEquals(v1String, v1.toString());
    System.out.println();
    String v2String = "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O";
    assertEquals(v2String, v2.toString());
    System.out.println();
    String v3String = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O _\n" +
            "    O O O\n" +
            "    O O O";
    assertEquals(v3String, v3.toString());

    // move right
    m1.move(3, 1, 3, 3);
    System.out.println();
    String v1StringMoved = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O";
    assertEquals(v1StringMoved, v1.toString());

    String v4String = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O";
    assertEquals(v4String, v4.toString());

    String v5String = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O";
    assertEquals(v5String, v5.toString());
    System.out.println(this.v5.toString());
  }

  // tests the renderBoard method with a valid appendable object
  @Test
  public void testRenderBoard() {
    try {
      this.a = new StringBuilder();
      this.newV = new MarbleSolitaireTextView(this.m1, this.a);
      this.newV.renderBoard();
      String[] message = this.a.toString().split("\n");
      StringBuilder standard = new StringBuilder();
      for (int i = 0; i < 7; i++) {
        standard.append(message[i]).append("\n");
      }
      assertEquals("    O O O\n" +
              "    O O O\n" +
              "O O O O O O O\n" +
              "O O O _ O O O\n" +
              "O O O O O O O\n" +
              "    O O O\n" +
              "    O O O\n", standard.toString());
      this.newV.renderMessage("\n");
      this.newV = new MarbleSolitaireTextView(this.m2, this.a);
      this.newV.renderBoard();
      message = this.a.toString().split("\n");
      StringBuilder boardAT5 = new StringBuilder();
      for (int i = 7; i < 20; i++) {
        boardAT5.append(message[i]).append("\n");
      }
      assertEquals("        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "O O O O O O _ O O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n", boardAT5.toString());
      this.newV.renderMessage("\n");
      this.m2.move(6, 4, 6, 6);
      this.newV.renderBoard();
      StringBuilder boardAT5Moved = new StringBuilder();
      message = this.a.toString().split("\n");
      for (int i = 20; i < 33; i++) {
        boardAT5Moved.append(message[i]).append("\n");
      }
      assertEquals("        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "O O O O _ _ O O O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "O O O O O O O O O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n" +
              "        O O O O O\n", boardAT5Moved.toString());
    } catch (IOException e) {
      fail("Fails to render the board successfully.");
    }
  }

  // tests the renderBoard method with an invalid Appendable object
  @Test
  public void testInvalidRenderBoard() {
    this.a = new InvalidAppendable();
    this.newV = new MarbleSolitaireTextView(this.m1, this.a);
    try {
      this.newV.renderBoard();
      fail("Failed to render the board with an invalid appendable.");
    } catch (IOException e) {
      // successfully catches the error when calling renderBoard with an
      // invalid Appendable object
    }
  }

  // tests the renderMessage method with a valid Appendable object
  @Test
  public void testRenderMessage() {
    try {
      this.a = new StringBuilder();
      assertEquals("", this.a.toString());
      this.newV = new MarbleSolitaireTextView(this.m1, a);
      this.newV.renderMessage("squishy squad");
      assertEquals("squishy squad", this.a.toString());
      this.newV.renderMessage(" is lit");
      assertEquals("squishy squad is lit", this.a.toString());
    } catch (IOException e) {
      fail("Throws an IOException when not supposed to.");
    }
  }

  // tests the renderMessage method with an invalid Appendable object
  @Test
  public void testInvalidRenderMessage() {
    this.a = new InvalidAppendable();
    this.newV = new MarbleSolitaireTextView(this.m1, this.a);
    try {
      this.a = new InvalidAppendable();
      this.newV.renderMessage("render a message");
      fail("Failed to render the message with an invalid appendable.");
    } catch (IOException e) {
      // successfully catches the error when calling renderMessage with an
      // invalid Appendable object
    }
  }
}