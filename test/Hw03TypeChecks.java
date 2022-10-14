import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.EnglishSolitaireModel;
import server.controller.MarbleSolitaireControllerImpl;
import server.view.MarbleSolitaireTextView;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw03TypeChecks {

  /**
   * The main method for the Hw03TypeChecks class.
   *
   * @param args the user's inputs passed in as an array of strings.
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helper(new EnglishSolitaireModel(), rd, ap);
    helper(new EnglishSolitaireModel(3, 3), rd, ap);
  }

  private static void helper(
          MarbleSolitaireModel model,
          Readable rd, Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model, ap), rd);
  }

}
