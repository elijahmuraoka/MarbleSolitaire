package server.controller.features;

import java.util.ArrayList;
import java.util.List;

import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.MarbleSolitaireModelState;
import server.view.MarbleSolitaireGuiView;
import server.view.MouseAdapterImpl;

/**
 * An implementation of the ControllerFeatures interface.
 */
public class ControllerFeaturesImpl implements ControllerFeatures {
  MarbleSolitaireModel model;
  MarbleSolitaireGuiView view;
  List<Integer> currentMoveSet;

  /**
   * The constructor for the ControllerFeaturesImpl class.
   *
   * @param model the current MarbleSolitaireModel
   * @param view  the current MarbleSolitaireGuiView
   * @throws IllegalArgumentException when either the model or view parameters are null
   */
  public ControllerFeaturesImpl(MarbleSolitaireModel model, MarbleSolitaireGuiView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Either the model or view parameters are null."
              + " Please try again.");
    }
    this.model = model;
    this.view = view;
    this.currentMoveSet = new ArrayList<Integer>();
    this.view.addMouseListenerToPanel(this);
    this.view.addGameOverListener(this);
  }

  @Override
  public void inputValues(MouseAdapterImpl mouseAdapter, int row, int col) {
    // if the game is not over
    if (!model.isGameOver()) {
      // if the current moveset is empty
      if (this.currentMoveSet.size() == 0) {
        // if the from slot is not a marble
        if (this.model.getSlotAt(row, col) != MarbleSolitaireModelState.SlotState.Marble) {
          this.view.renderMessage("From position must be a marble slot. Please try again.");
          mouseAdapter.setMouseReleased(false);
        } else {
          // otherwise add the row and column to the moveset
          this.currentMoveSet.add(row);
          this.currentMoveSet.add(col);
        }
      }
      else {
        this.currentMoveSet.add(row);
        this.currentMoveSet.add(col);
        int fromRow = this.currentMoveSet.get(0);
        int fromCol = this.currentMoveSet.get(1);
        int toRow = this.currentMoveSet.get(2);
        int toCol = this.currentMoveSet.get(3);
        try {
          this.model.move(fromRow, fromCol, toRow, toCol);
        } catch (IllegalArgumentException e) {
          this.view.renderMessage(e.getMessage());
        }
        mouseAdapter.setMouseReleased(false);
        this.currentMoveSet = new ArrayList<>();
      }
    }
    this.view.refresh();
  }

  @Override
  public void gameOver() {
    if (this.model.isGameOver()) {
      if (this.model.getScore() == 1) {
        this.view.renderMessage("Congrats Gamer You Won!");
      } else {
        this.view.renderMessage("Game Over. Better luck next time noob!");
      }
    }
  }
}
