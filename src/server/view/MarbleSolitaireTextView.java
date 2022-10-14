package server.view;

import server.model.hw02.MarbleSolitaireModelState;

/**
 * This class is used to produce the appropriate game displays for the EnglishSolitaireModel and
 * the EuropeanSolitaireModel classes.
 */
public class MarbleSolitaireTextView extends AbstractMarbleSolitaireTextView {

  /**
   * This constructor only takes in a single MarbleSolitaireModelState
   * in order to produce a valid text view.
   *
   * @param m the given MarbleSolitaireModelState
   * @throws IllegalArgumentException when the MarbleSolitaireModelState parameter (m) is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState m) throws IllegalArgumentException {
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
  public MarbleSolitaireTextView(MarbleSolitaireModelState m, Appendable a)
          throws IllegalArgumentException {
    super(m, a);
  }
}
