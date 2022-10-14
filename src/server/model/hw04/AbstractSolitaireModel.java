package server.model.hw04;

import java.util.ArrayList;
import java.util.List;

import server.model.hw02.MarbleSolitaireModel;

/**
 * The abstract class for any Marble Solitaire game model. Every model must be able to
 * initialize a specific type of game board and support all operations contained in the
 * MarbleSolitaireModel interface.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  // the arm thickness, which determines the size of the board.
  protected final int at;
  // the x-position of the starting empty cell.
  protected final List<List<SlotState>> board;

  /**
   * A constructor that takes in arm thickness, row, and column inputs to create a
   * standard AbstractSolitaireModel object.
   *
   * @param at  the given arm thickness.
   * @param row the row of the starting empty position
   * @param col the column of the starting empty position
   * @throws IllegalArgumentException when the arm board's arm thickness is not odd or less than 3.
   * @throws IllegalArgumentException when placing the starting empty slot at an invalid position.
   */
  public AbstractSolitaireModel(int at, int row, int col) {
    this.at = at;
    this.board = new ArrayList<>();
    this.makeBoard(row, col);
  }

  /**
   * Creates a starting game board using valid data to initialize an empty slot at
   * the given position (row, col).
   *
   * @param row the row to set the starting  empty position at
   * @param col the column to set the starting empty position at
   * @throws IllegalArgumentException when the arm board's arm thickness is not odd or less than 3.
   * @throws IllegalArgumentException when placing the starting empty slot at an invalid position.
   */
  protected abstract void makeBoard(int row, int col) throws IllegalArgumentException;

  /**
   * This operation is used to play the game by moving a marble to another location
   * by jumping over other marbles and removing the ones jumped over in the process.
   * This method MUST:
   * - Turn from slot state to empty
   * - Turn in between slot state to empty
   * - Turn to slot state to marble
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException when a move is invalid, meaning...
   *                                  - the from position is not a marble
   *                                  - the to position is not empty
   *                                  - the slot position in-between is not a marble
   *                                  - the from and to positions are not exactly one slot apart
   *                                  - the from and to positions are either not in the same row
   *                                  or column
   *                                  - the from position is not within the board's dimensions
   *                                  = the to position is not within the board's dimensions
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws
          IllegalArgumentException {
    if (this.canMakeMove(fromRow, fromCol, toRow, toCol)) {
      this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
      this.board.get(fromRow).set(fromCol, SlotState.Empty);
      this.board.get(toRow).set(toCol, SlotState.Marble);
    } else {
      throw new IllegalArgumentException("The move you are trying to make is invalid.");
    }
  }

  /**
   * Determine whether the user can make a valid move.
   *
   * @param fromRow the row of the from position (starting from 0)
   * @param fromCol the column of the from position (starting from 0)
   * @param toRow   the row of the to position (starting from 0)
   * @param toCol   the column of the to position (starting from 0)
   * @return a boolean representation of whether a user can make a move from one cell to another,
   *         meaning the two cells are on the same x or y-axis, have one marble cell in between,
   *         and the to cell is an empty one.
   */
  protected boolean canMakeMove(int fromRow, int fromCol, int toRow, int toCol) {
    // check all conditions needed in order to make a move
    return this.withinBoardDimensions(fromRow, fromCol)
            && this.withinBoardDimensions(toRow, toCol)
            && this.canMakeMoveHelper(fromRow, fromCol, toRow, toCol)
            && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            && this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            == SlotState.Marble;
  }

  /**
   * The helper method for the canMakeMove method.
   *
   * @param fromRow the row of the from position (starting from 0)
   * @param fromCol the column of the from position (starting from 0)
   * @param toRow   the row of the to position (starting from 0)
   * @param toCol   the column of the to position (starting from 0)
   * @return a boolean representing which direction(s) a marble of this model can travel in
   */
  protected boolean canMakeMoveHelper(int fromRow, int fromCol, int toRow, int toCol) {
    // initialize local variables
    boolean sameRow = (fromRow == toRow);
    boolean sameCol = (fromCol == toCol);
    boolean up = (toRow - fromRow) == -2;
    boolean down = (toRow - fromRow) == 2;
    boolean left = (toCol - fromCol) == -2;
    boolean right = (toCol - fromCol) == 2;
    boolean r = false;
    boolean c = false;
    if (sameRow) {
      r = left || right;
    }
    if (sameCol) {
      c = up || down;
    }
    return r || c;
  }

  /**
   * Checks whether a given position is within the game board's dimensions.
   *
   * @param row the row of the position (x-coordinate)
   * @param col the column of the position (y-coordinate)
   * @return a boolean representation of whether the given position is within the dimensions
   *         of the board.
   */
  protected boolean withinBoardDimensions(int row, int col) {
    return (row >= 0 && row < this.getBoardSize()
            && col >= 0 && col < this.getBoardSize());
  }

  @Override
  public boolean isGameOver() {
    int bs = this.getBoardSize();
    boolean result = true;
    for (int i = 0; i < bs; i++) {
      for (int j = 0; j < bs; j++) {
        SlotState current = this.getSlotAt(i, j);
        if (current == SlotState.Marble) {
          // cannot make a move to the left
          result = result && (!this.canMakeMove(i, j, i, j + 2)
                  // cannot make a move to the right
                  && !this.canMakeMove(i, j, i, j - 2)
                  // cannot make a move up
                  && !this.canMakeMove(i, j, i - 2, j)
                  // cannot make a move down
                  && !this.canMakeMove(i, j, i + 2, j));
        }
      }
    }
    return result;
  }

  @Override
  public int getBoardSize() {
    return (this.at * 3) - 2;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (this.withinBoardDimensions(row, col)) {
      return this.board.get(row).get(col);
    }
    throw new IllegalArgumentException("The given slot is not within this board's dimensions.");
  }

  @Override
  public int getScore() {
    int result = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        SlotState current = this.getSlotAt(i, j);
        if (current == SlotState.Marble) {
          result++;
        }
      }
    }
    return result;
  }
}
