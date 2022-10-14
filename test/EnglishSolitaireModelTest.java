import org.junit.Before;
import org.junit.Test;

import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.MarbleSolitaireModelState;
import server.model.hw04.AbstractSolitaireModel;
import server.view.AbstractMarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents all the tests for methods supported in the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest extends AbstractSolitaireModelTest {
  private MarbleSolitaireModel m1;
  private MarbleSolitaireModel m2;
  private MarbleSolitaireModel m3;
  private MarbleSolitaireModel m4;
  private MarbleSolitaireModel m5;

  // sets up initial examples to be used in testing
  @Before
  public void init() {
    this.m1 = new EnglishSolitaireModel();
    this.m2 = new EnglishSolitaireModel(5);
    this.m3 = new EnglishSolitaireModel(2, 4);
    this.m4 = new EnglishSolitaireModel(3, 3, 3);
    this.m5 = new EnglishSolitaireModel(7);
  }

  @Override
  protected AbstractSolitaireModel generateModelEmpty() {
    return new EnglishSolitaireModel();
  }

  @Override
  protected AbstractSolitaireModel generateModelOne(int at) {
    return new EnglishSolitaireModel(at);
  }

  @Override
  protected AbstractSolitaireModel generateModelTwo(int sRow, int sCol) {
    return new EnglishSolitaireModel(sRow, sCol);
  }

  @Override
  protected AbstractSolitaireModel generateModelThree(int at, int row, int col) {
    return new EnglishSolitaireModel(at, row, col);
  }

  // sets up initial examples with invalid arguments
  @Test
  public void initInvalidModels() {
    try {
      this.m1 = new EnglishSolitaireModel(6);
      fail("Arm Thickness cannot be less than 3 or an even number.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for an even arm thickness
    }

    try {
      this.m2 = new EnglishSolitaireModel(1);
      fail("Arm Thickness cannot be less than 3 or an even number.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for an arm thickness less than 3
    }

    try {
      this.m3 = new EnglishSolitaireModel(1, 1);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }
    try {
      this.m3 = new EnglishSolitaireModel(7, 6);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }

    try {
      this.m3 = new EnglishSolitaireModel(3, 1, 1);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }

    try {
      this.m2 = new EnglishSolitaireModel(1, 3, 3);
      fail("Arm Thickness cannot be less than 3 or an even number.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for an arm thickness less than 3
    }

    try {
      this.m2 = new EnglishSolitaireModel(-41, 3, 3);
      fail("Arm Thickness cannot be less than 3 or an even number.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for an arm thickness less than 3
    }

    try {
      this.m2 = new EnglishSolitaireModel(8, 3, 3);
      fail("Arm Thickness cannot be less than 3 or an even number.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for an even arm thickness
    }

    try {
      this.m3 = new EnglishSolitaireModel(3, 5, 7);
      fail("Row and/or Column inputs are outside this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the given empty position is
      // outside the board's x and/or y-ranges
    }

    try {
      this.m3 = new EnglishSolitaireModel(3, 5, 7);
      fail("Row and/or Column inputs are outside this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the given empty position is
      // outside the board's x and/or y-ranges
    }

    try {
      this.m3 = new EnglishSolitaireModel(5, 0, 2);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }
  }

  @Test
  public void testValid() {
    // empty constructor
    assertEquals(32, this.m1.getScore());
    assertEquals(7, this.m1.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m1.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m1.getBoardSize(); j++) {
        if (this.testValidHelper(this.m1, i, j)) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m1.getSlotAt(i, j));
        } else if (i == 3 && j == 3) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m1.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m1.getSlotAt(i, j));
        }
      }
    }

    // arm thickness = 5
    assertEquals(104, this.m2.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(6, 6));
    assertEquals(13, this.m2.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m2.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m2.getBoardSize(); j++) {
        if (this.testValidHelper(this.m2, i, j)) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m2.getSlotAt(i, j));
        } else if (i == 6 && j == 6) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m2.getSlotAt(i, j));
        }
      }
    }

    // constructor with 2 arguments
    assertEquals(32, this.m3.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(2, 4));
    assertEquals(7, this.m3.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m3.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m3.getBoardSize(); j++) {
        if (this.testValidHelper(this.m3, i, j)) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m3.getSlotAt(i, j));
        } else if (i == 2 && j == 4) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m3.getSlotAt(i, j));
        }
      }
    }

    // constructor with 3 arguments
    assertEquals(32, this.m4.getScore());
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(3, 3));
    assertEquals(7, this.m4.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m4.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m4.getBoardSize(); j++) {
        if (this.testValidHelper(this.m4, i, j)) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m4.getSlotAt(i, j));
        } else if (i == 3 && j == 3) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m4.getSlotAt(i, j));
        }
      }
    }
  }

  /**
   * Helper method for testValid.
   *
   * @param m the given MarbleSolitaireModel to be evaluated
   * @param x the x-coordinate of the given slot state
   * @param y the y-coordinate of the given slot state
   * @return whether the given position is an invalid one or not.
   */
  private boolean testValidHelper(MarbleSolitaireModel m, int x, int y) {
    // boardSize
    int bs = m.getBoardSize();
    // calculates the pivot position
    int pivot = ((bs + 2) / 3) - 1;
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

  // tests the move method
  @Test
  public void testMove() {
    // move right
    assertEquals(32, this.m1.getScore());
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    m1.move(3, 1, 3, 3);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 3));
    assertEquals(31, this.m1.getScore());

    // move left
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 2));
    m1.move(3, 4, 3, 2);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 2));
    assertEquals(30, this.m1.getScore());

    // move up
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    m1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 3));
    assertEquals(29, this.m1.getScore());

    // move down
    assertEquals(104, this.m2.getScore());
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m2.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m2.getSlotAt(4, 6));
    m2.move(4, 6, 6, 6);
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m2.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m2.getSlotAt(5, 6));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m2.getSlotAt(4, 6));
    assertEquals(103, this.m2.getScore());

    try {
      this.m1.move(-2, 0, 0, 0);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if the from position does not exist
      // within the current board's dimensions
    }

    try {
      this.m1.move(0, 6, 0, 8);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if the to position does not exist
      // within the current board's dimensions
    }

    try {
      this.m1.move(2, 5, 2, 5);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when
      // moving the marble to the same location
    }

    try {
      this.m1.move(3, 2, 3, 3);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when
      // moving only one slot over
    }

    try {
      this.m1.move(0, 3, 0, 6);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if trying to
      // move too far in one direction (2+)
    }

    try {
      this.m1.move(2, 2, 4, 4);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if trying to
      // move 2 away but not in either same column or row
    }

    try {
      this.m1.move(0, 0, 2, 0);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the
      // from position is invalid
    }

    try {
      this.m1.move(4, 3, 3, 2);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the
      // from position is empty
    }

    try {
      this.m1.move(1, 3, 3, 3);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when
      // the to position is a marble
    }

    try {
      this.m1.move(0, 3, 0, 5);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when
      // the to position is invalid
    }

    try {
      this.m1.move(3, 3, 5, 3);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when
      // position you are jumping over is not a marble
    }
  }

  // tests the isGameOver method and resetBoard method
  @Test
  public void testGameOver() {
    assertFalse(this.m1.isGameOver());
    this.m1.move(1, 3, 3, 3);
    this.m1.move(4, 3, 2, 3);
    this.m1.move(3, 5, 3, 3);
    this.m1.move(3, 2, 3, 4);
    this.m1.move(3, 0, 3, 2);
    this.m1.move(6, 3, 4, 3);
    assertEquals(26, this.m1.getScore());
    assertTrue(this.m1.isGameOver());
    AbstractMarbleSolitaireTextView v1 = new AbstractMarbleSolitaireTextView(this.m1) {
    };
  }

  // tests the getBoardSize method
  @Test
  public void testGetBoardSize() {
    assertEquals(7, m1.getBoardSize());
    assertEquals(13, m2.getBoardSize());
    assertEquals(7, m3.getBoardSize());
    assertEquals(7, m4.getBoardSize());
    assertEquals(19, m5.getBoardSize());
  }

  // tests the getSlotAt method
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(5, 4));
    assertNotEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(6, 5));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    assertNotEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m2.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m3.getSlotAt(2, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m5.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m5.getSlotAt(9, 9));
    assertNotEquals(MarbleSolitaireModel.SlotState.Marble, this.m5.getSlotAt(18, 15));


    try {
      this.m1.getSlotAt(8, 6);
      fail("The given slot is not within this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if trying to
      // pass in a slot outside of the board's y-range
    }

    try {
      this.m2.getSlotAt(8, -36);
      fail("The given slot is not within this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if trying to
      // pass in a slot outside of the board's x-range
    }
  }

  // tests the getScore method
  @Test
  public void testGetScore() {
    assertEquals(32, this.m1.getScore());
    assertEquals(104, this.m2.getScore());
    assertEquals(32, this.m3.getScore());
    m1.move(3, 1, 3, 3);
    assertNotEquals(32, this.m1.getScore());
    assertEquals(31, this.m1.getScore());
    m1.move(1, 2, 3, 2);
    assertEquals(30, this.m1.getScore());
  }
}