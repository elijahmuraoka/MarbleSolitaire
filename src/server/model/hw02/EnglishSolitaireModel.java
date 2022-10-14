package server.model.hw02;

import java.util.ArrayList;

import server.model.hw04.AbstractSolitaireModel;

/**
 * This class represents an EnglishSolitaireModel and all of its supported operations.
 * This specific model supports a board with marbles placed to form a "plus-shaped design".
 * In this version, you can use a marble to jump over other marbles to remove them.
 * You typically start with 32 marbles (with the standard board) and the game continues until
 * either you have only a single marble left (win) or you just cannot make any more moves (1).
 * It should be noted that other cultures may have different variations of this game design.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * empty constructor to create a standard EnglishSolitaireModel object
   * with arm thickness, row, and column values of 3.
   */
  public EnglishSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * A constructor that creates an EnglishSolitaireModel object, given a value for arm thickness.
   *
   * @param at is the given arm thickness.
   * @throws IllegalArgumentException when the arm board's arm thickness is not odd or less than 3.
   */
  public EnglishSolitaireModel(int at) {
    super(at, ((at * 3) - 2) / 2, ((at * 3) - 2) / 2);
  }

  /**
   * A constructor which takes in a given row and column to create an EnglishSolitaireModel object.
   *
   * @param sRow is the new x-position of the empty space.
   * @param sCol is the new y-position of the empty space.
   * @throws IllegalArgumentException when placing the starting empty slot at an invalid position.
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * A constructor that takes in arm thickness, row, and column inputs to create a
   * standard EnglishSolitaireModel object.
   *
   * @param at  is the given arm thickness.
   * @param row is the row of the starting empty position
   * @param col is the column of the starting empty position
   * @throws IllegalArgumentException when the arm board's arm thickness is not odd or less than 3.
   * @throws IllegalArgumentException when placing the starting empty slot at an invalid position.
   */
  public EnglishSolitaireModel(int at, int row, int col) {
    super(at, row, col);
  }

  @Override
  public void makeBoard(int row, int col) {
    if (!this.withinBoardDimensions(row, col)) {
      throw new IllegalArgumentException("Row and/or Column inputs are outside "
              + "this board's dimensions.");
    }
    if (this.at < 3 || this.at % 2 == 0) {
      throw new IllegalArgumentException("Arm Thickness cannot be less than 3 or an even number.");
    }
    // for each row (i)
    for (int i = 0; i < this.getBoardSize(); i++) {
      // for each column (i)
      ArrayList<SlotState> newRow = new ArrayList<>();
      for (int j = 0; j < this.getBoardSize(); j++) {
        // if (i, j) is an invalid position
        if (this.isInvalidPosition(i, j)) {
          // add an Invalid SlotState
          newRow.add(SlotState.Invalid);
        } else {
          // add a Marble SlotState
          newRow.add(SlotState.Marble);
        }
      }
      this.board.add(newRow);
    }
    // if this position is invalid
    if (board.get(row).get(col) == SlotState.Invalid) {
      // throw an exception since this position is invalid
      throw new IllegalArgumentException("Invalid empty cell position (r,c).");
    } else {
      // set this position as an Empty SlotState
      this.board.get(row).set(col, SlotState.Empty);
    }
  }

  /**
   * Determines the validity of a given position.
   *
   * @param x the x-coordinate of the given slot state
   * @param y the y-coordinate of the given slot state
   * @return whether the given position is an invalid one or not
   */
  private boolean isInvalidPosition(int x, int y) {
    // boardSize
    int bs = this.getBoardSize();
    // calculates the pivot position
    int pivot = this.at - 1;
    // is y in the y-range of invalid cells?
    boolean inYRange = (y >= 0 && y < pivot)
            || (y >= (bs - pivot) && y <= (bs - 1));
    if (x >= 0 && x < pivot) {
      return inYRange;
    }
    if (x >= (bs - pivot) && x <= (bs - 1)) {
      return inYRange;
    }
    return false;
  }
}
