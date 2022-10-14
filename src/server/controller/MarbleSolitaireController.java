package server.controller;

/**
 * This interface represents operations that should be supported by a controller
 * for the Marble Solitaire game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException only if the controller is unable to successfully
   *                               read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
