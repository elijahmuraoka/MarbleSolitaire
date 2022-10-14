package server.view;

import java.io.IOException;

import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the text view for a single Marble Solitaire Model and its game board.
 */
public abstract class AbstractMarbleSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState m;
  protected final Appendable a;

  /**
   * This constructor only takes in a single MarbleSolitaireModelState
   * in order to produce a valid text view.
   *
   * @param m the given MarbleSolitaireModelState
   * @throws IllegalArgumentException when the MarbleSolitaireModelState parameter (m) is null
   */
  public AbstractMarbleSolitaireTextView(MarbleSolitaireModelState m)
          throws IllegalArgumentException {
    this.m = m;
    this.a = System.out;
    if (this.m == null) {
      throw new IllegalArgumentException("The given Marble Solitaire Model cannot be  null.");
    }
  }

  /**
   * This constructor takes in both a MarbleSolitaireModelState and Appendable object
   * in order to produce a valid text view.
   *
   * @param m the given MarbleSolitaireModelState
   * @param a the given Appendable object
   * @throws IllegalArgumentException when either the ModelState (m) or Appendable (a) is null
   */
  public AbstractMarbleSolitaireTextView(MarbleSolitaireModelState m, Appendable a)
          throws IllegalArgumentException {
    this.m = m;
    this.a = a;

    if (this.m == null || a == null) {
      throw new IllegalArgumentException("The given Marble Solitaire Model" +
              "and/or Appendable object cannot be null.");
    }
  }

  @Override
  public String toString() {
    int bs = this.m.getBoardSize();
    int pivot = bs - ((bs + 2) / 3) + 1;
    StringBuilder result = new StringBuilder();
    // for each row
    for (int i = 0; i < bs; i++) {
      // for each column
      for (int j = 0; j < bs; j++) {
        MarbleSolitaireModel.SlotState current = this.m.getSlotAt(i, j);
        String s = this.toStringHelper(current);
        // if not in the first column
        if (j != 0) {
          // if not in the last column
          if (j != bs - 1) {
            // if greater than pivot column and invalid
            if ((j >= pivot && current == MarbleSolitaireModel.SlotState.Invalid)) {
              // if it is not in the last row
              if (i != bs - 1) {
                // append a new line
                result.append("\n");
              }
              // break to a new line
              break;
            } else {
              // create the current symbol with a space before
              result.append(" ").append(s);
            }
            // if the last slot in the row is invalid
          } else if (current == MarbleSolitaireModelState.SlotState.Invalid) {
            // break to a new line
            result.append("\n");
            break;
          } else {
            // create the current symbol with a space before and a new line after
            result.append(" ").append(s).append("\n");
          }
        } else {
          // create the current symbol
          result.append(s);
        }
      }
    }
    // prints the text in the console
    // System.out.println(result);
    return result.toString();
  }

  /**
   * The helper method for toString used to display a symbol for each respective slot state.
   *
   * @param s the given slot state
   * @return the string representation of a specific slot state
   */
  protected String toStringHelper(MarbleSolitaireModel.SlotState s) throws
          IllegalArgumentException {
    String result;
    switch (s) {
      case Empty:
        result = "_";
        break;
      case Marble:
        result = "O";
        break;
      case Invalid:
        result = " ";
        break;
      default:
        throw new IllegalArgumentException("The given slot state parameter is invalid.");
    }
    return result;
  }

  @Override
  public void renderBoard() throws IOException {
    this.a.append(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.a.append(message);
  }
}
