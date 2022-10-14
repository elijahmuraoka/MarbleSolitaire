import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.model.hw04.TriangleSolitaireModel;
import server.controller.MarbleSolitaireControllerImpl;
import server.view.MarbleSolitaireTextView;

/**
 * Do not modify this file. This file should compile correctly with your code!
 */
public class Hw04TypeChecks {

  /**
   * The main method for the Hw04TypeChecks class.
   *
   * @param args the user's inputs passed in as an array of strings.
   */
  public static void main(String[] args) {
    Readable rd = null;
    Appendable ap = null;
    helperMarble(new EnglishSolitaireModel(),
            rd, ap);

    helperTriangle(new TriangleSolitaireModel(3, 3),
            rd, ap);
  }

  private static void helperMarble(MarbleSolitaireModel model,
                                   Readable rd, Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model, ap), rd);
  }

  private static void helperTriangle(MarbleSolitaireModel model,
                                     Readable rd, Appendable ap) {
    new MarbleSolitaireControllerImpl(model,
            new MarbleSolitaireTextView(model, ap), rd);
  }

}
