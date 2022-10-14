package server.model.hw04;

import java.util.ArrayList;

import server.model.hw02.MarbleSolitaireModel;

/**
 * This class represents an EuropeanSolitaireModel and all of its supported operations.
 * This specific model supports a board with marbles placed to form an "octagonal-shaped design".
 * In this version, you can use a marble to jump over other marbles to remove them.
 * You typically start with 36 marbles (with the standard board) and the game continues until
 * either you have only a single marble left (win) or you just cannot make any more moves (lose).
 * It should be noted that other cultures may have different variations of this game design.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * Empty constructor which creates a standard EuropeanSolitaireModel object
   * with arm thickness, row, and column values of 3.
   */
  public EuropeanSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * A constructor that creates an EuropeanSolitaireModel object, given a valid arm thickness.
   *
   * @param at is the given arm thickness.
   * @throws IllegalArgumentException when the arm board's arm thickness is not odd or less than 3.
   */
  public EuropeanSolitaireModel(int at) {
    super(at, ((at * 3) - 2) / 2, ((at * 3) - 2) / 2);
  }

  /**
   * A constructor which takes in a given row and column to create an EuropeanSolitaireModel object.
   *
   * @param sRow is the new x-position of the empty space.
   * @param sCol is the new y-position of the empty space.
   * @throws IllegalArgumentException when placing the starting empty slot at an invalid position.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(3, sRow, sCol);
  }

  /**
   * A constructor that takes in arm thickness, row, and column inputs to create a
   * standard EuropeanSolitaireModel object.
   *
   * @param at  is the given arm thickness.
   * @param row is the row of the starting empty position
   * @param col is the column of the starting empty position
   * @throws IllegalArgumentException when the arm board's arm thickness is not odd or less than 3.
   * @throws IllegalArgumentException when placing the starting empty slot at an invalid position.
   */
  public EuropeanSolitaireModel(int at, int row, int col) {
    super(at, row, col);
  }

  @Override
  public void makeBoard(int row, int col) throws IllegalArgumentException {
    // boardSize
    int bs = this.getBoardSize();
    // calculates the pivot position
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
        // add a Marble SlotState
        newRow.add(SlotState.Marble);
      }
      this.board.add(newRow);
    }
    this.mutateInvalid();

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
   * Mutates the board of marbles, setting the invalid positions correctly
   * in order to create the octagonal design.
   */
  private void mutateInvalid() {
    int pivot = this.at - 1;
    this.mutateInvalidHelper(0, pivot, 1);
    this.mutateInvalidHelper(this.getBoardSize() - 1,
            this.at + pivot - 1, -1);
  }

  /**
   * Helper method for mutateInvalid
   * This method works by mutating 'x' number of slots at the beginning (from start)
   * and end (from board size) to be invalid. Once there are no more invalid slots left
   * in the row, it moves to the next one, with a set decrease in the number of invalid
   * slots each time.
   *
   * @param startY    the y-value the method starts at
   * @param endY      the y-value the method ends at
   * @param increment the integer amount to increment the row values by
   *                  - Top-Half (positive, top to bottom)
   *                  - Bottom-Half (negative, bottom to top)
   */
  private void mutateInvalidHelper(int startY, int endY, int increment) {
    int pivot = this.at - 1;
    int invalidSlotsTotal = 2 * this.at - 2;
    for (int i = startY;
         increment > 0 ? i < endY : i > endY;
         i = i + increment) {
      int invalidSlotsLeft = invalidSlotsTotal;
      for (int j = 0; invalidSlotsLeft >= 0; ) {
        // sets the first half of invalid slots from the beginning of the row
        if (invalidSlotsLeft > 0 && j < invalidSlotsTotal / 2) {
          this.board.get(i).set(j, MarbleSolitaireModel.SlotState.Invalid);
          invalidSlotsLeft--;
          j++;
        }
        // sets j to be at the end of the board once it sets half the invalid slots
        if (j == invalidSlotsTotal / 2) {
          j = this.getBoardSize() - 1;
        }
        // sets the remaining invalid slots starting from the end of the row towards the middle
        if (invalidSlotsLeft > 0 && j > this.getBoardSize() - pivot - 1) {
          this.board.get(i).set(j, MarbleSolitaireModel.SlotState.Invalid);
          invalidSlotsLeft--;
          j--;
        }
        // if there are no invalid slots left in this row, subtract two from the current
        // total number of invalid spots (for the next row)
        if (invalidSlotsLeft == 0) {
          invalidSlotsTotal = invalidSlotsTotal - 2;
          invalidSlotsLeft--;
        }
      }
    }
  }
}
