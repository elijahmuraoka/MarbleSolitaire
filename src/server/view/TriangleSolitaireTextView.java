package server.view;

import server.model.hw02.MarbleSolitaireModelState;

/**
 * This class is used to produce the appropriate game display for the TriangleSolitaireModel class.
 */
public class TriangleSolitaireTextView extends AbstractMarbleSolitaireTextView {

  /**
   * This constructor only takes in a single MarbleSolitaireModelState
   * in order to produce a valid text view.
   *
   * @param m the given MarbleSolitaireModelState
   * @throws IllegalArgumentException when the MarbleSolitaireModelState parameter (m) is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState m)
          throws IllegalArgumentException {
    super(m);
  }

  /**
   * This constructor takes in both a MarbleSolitaireModelState and Appendable object
   * in order to produce a valid text view.
   *
   * @param m the given MarbleSolitaireModelState
   * @param a the given Appendable object
   * @throws IllegalArgumentException when either the ModelState (m) or Appendable (a) is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState m, Appendable a)
          throws IllegalArgumentException {
    super(m, a);
  }

  @Override
  public String toString() {
    int bs = this.m.getBoardSize();
    int totalSpaces = bs - 1;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < bs; i++) {
      int spacesLeft = totalSpaces;
      for (int j = 0; j < bs; j++) {
        MarbleSolitaireModelState.SlotState current = this.m.getSlotAt(i, j);
        String s = this.toStringHelper(current);
        while (spacesLeft > 0) {
          result.append(" ");
          spacesLeft--;
        }
        // if the current slot is not invalid
        if (current != MarbleSolitaireModelState.SlotState.Invalid) {
          // if current is not the starting slot
          if (j != 0) {
            result.append(" ").append(s);
          } else {
            result.append(s);
          }
        } else {
          result.append("\n");
          totalSpaces--;
          break;
        }
      }
    }
    return result.toString();
  }
}
