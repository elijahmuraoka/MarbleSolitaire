package server.controller.features;

import server.view.MouseAdapterImpl;

/**
 * The GUI controller interface that represents the various features this controller can provide.
 * <p>
 * Our vision is that the user makes a move by clicking on one cell (from),
 * then clicking on another (to). The controller should then use the model to attempt a move
 * from the first cell to the second. Thus, the "input" to our program by the user is a
 * single click, which provides a row and column.
 */
public interface ControllerFeatures {

  void inputValues(MouseAdapterImpl mouseAdapter, int row, int col);

  void gameOver();
}
