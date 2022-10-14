package server.model.hw04;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a TriangleSolitaireModel and all of its supported operations.
 * This specific model supports a board with marbles placed to form a right triangle.
 * In this version, you can use a marble to jump over other marbles to remove them.
 * Unlike the English and European models, you can also move the marbles diagonally as long as
 * they are still two spaces apart. You typically start with 14 marbles (with the standard board)
 * and the game continues until either you have only a single marble left (win) or you just cannot
 * make any more moves (1).
 * It should be noted that other cultures may have different variations of this game design.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * A default constructor (no parameters) that creates a 5-row game with the
   * empty slot at (0,0).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }


  /**
   * A constructor with a single parameter (dimensions) that create a game
   * with the specified dimension (number of slots in the bottom-most row) and
   * the empty slot at (0,0).
   *
   * @param d the given dimension of the board (one side)
   * @throws IllegalArgumentException when the board dimension (d) is invalid (non-positive)
   */
  public TriangleSolitaireModel(int d) throws IllegalArgumentException {
    super(d, 0, 0);
  }

  /**
   * A constructor with two parameters (row,col) that creates a 5-row game with
   * the empty slot at the specified position.
   *
   * @param sRow the row of the starting empty position
   * @param sCol the column of the starting empty position
   * @throws IllegalArgumentException when the specified empty position (sRow, sCol) is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    super(5, sRow, sCol);
  }

  /**
   * A constructor with three parameters (dimensions,row,col) that creates a
   * game with the specified dimension and an empty slot at the specified row and column.
   *
   * @param d   the given dimension of the board (one side)
   * @param row the row of the starting empty position
   * @param col the column of the starting empty position
   * @throws IllegalArgumentException when...
   *                                  - the specified dimension is invalid (non-positive)
   *                                  - the specified position (row,col) is invalid
   */
  public TriangleSolitaireModel(int d, int row, int col) throws IllegalArgumentException {
    super(d, row, col);
  }

  @Override
  protected void makeBoard(int row, int col) throws IllegalArgumentException {
    if (!this.withinBoardDimensions(row, col)) {
      throw new IllegalArgumentException("Row and/or Column inputs are outside "
              + "this board's dimensions.");
    }
    if (this.at < 0) {
      throw new IllegalArgumentException("Board dimensions cannot be negative.");
    }
    List<List<SlotState>> result = new ArrayList<>();
    for (int i = 0; i < this.getBoardSize(); i++) {
      List<SlotState> newRow = new ArrayList<>();
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (j <= i) {
          newRow.add(SlotState.Marble);
        } else {
          newRow.add(SlotState.Invalid);
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

  // allows the user to also move marbles diagonally on the board
  @Override
  public boolean canMakeMoveHelper(int fromRow, int fromCol, int toRow, int toCol) {
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

    return r || c || (up && (left || right)) || (down && (left || right));
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
                  && !this.canMakeMove(i, j, i + 2, j))
                  // cannot make a move diagonal up left
                  && !this.canMakeMove(i, j, i - 2, j - 2)
                  // cannot make a move diagonal up right
                  && !this.canMakeMove(i, j, i - 2, j + 2)
                  // cannot make a move diagonal down left
                  && !this.canMakeMove(i, j, i + 2, j - 2)
                  // cannot make a move diagonal down right
                  && !this.canMakeMove(i, j, i + 2, j + 2);
        }
      }
    }
    return result;
  }

  @Override
  public int getBoardSize() {
    return this.at;
  }
}
