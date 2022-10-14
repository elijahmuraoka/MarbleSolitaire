import org.junit.Before;
import org.junit.Test;

import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.MarbleSolitaireModelState;
import server.model.hw04.AbstractSolitaireModel;
import server.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents all the tests for methods supported in the
 * EuropeanSolitaire class.
 */
public class EuropeanSolitaireModelTest extends AbstractSolitaireModelTest {
  private AbstractSolitaireModel m1;
  private AbstractSolitaireModel m2;
  private AbstractSolitaireModel m3;
  private AbstractSolitaireModel m4;
  private AbstractSolitaireModel m5;

  @Override
  protected AbstractSolitaireModel generateModelEmpty() {
    return new EuropeanSolitaireModel();
  }

  @Override
  protected AbstractSolitaireModel generateModelOne(int at) {
    return new EuropeanSolitaireModel(at);
  }

  @Override
  protected AbstractSolitaireModel generateModelTwo(int sRow, int sCol) {
    return new EuropeanSolitaireModel(sRow, sCol);
  }

  @Override
  protected AbstractSolitaireModel generateModelThree(int at, int row, int col) {
    return new EuropeanSolitaireModel(at, row, col);
  }

  // sets up initial examples to be used in testing
  @Before
  public void init() {
    this.m1 = new EuropeanSolitaireModel();
    this.m2 = new EuropeanSolitaireModel(3);
    this.m3 = new EuropeanSolitaireModel(4, 5);
    this.m4 = new EuropeanSolitaireModel(3, 2, 4);
    this.m5 = new EuropeanSolitaireModel(5);
  }

  // sets up initial examples with invalid arguments
  @Test
  public void initInvalidModels() {
    try {
      this.m1 = new EuropeanSolitaireModel(6);
      fail("Arm Thickness cannot be even.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for an even arm thickness
    }

    try {
      this.m1 = new EuropeanSolitaireModel(-23);
      fail("Arm Thickness cannot be negative.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a negative arm
      // thickness
    }

    try {
      this.m2 = new EuropeanSolitaireModel(0, 0);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is
      // same as
      // that of an invalid slot position

    }
    try {
      this.m2 = new EuropeanSolitaireModel(6, 6);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is
      // same as
      // that of an invalid slot position
    }

    try {
      this.m3 = new EuropeanSolitaireModel(4, 1, 0);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is
      // same as
      // that of an invalid slot position
    }

    try {
      this.m2 = new EuropeanSolitaireModel(-4, 3, 3);
      fail("Arm Thickness cannot be negative.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a negative arm
      // thickness
    }

    try {
      this.m3 = new EuropeanSolitaireModel(3, 10, 5);
      fail("Row and/or Column inputs are outside this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the given empty
      // position is
      // outside the board's x and/or y-ranges
    }

    try {
      this.m3 = new EuropeanSolitaireModel(3, 5, 21);
      fail("Row and/or Column inputs are outside this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the given empty
      // position is
      // outside the board's x and/or y-ranges
    }

    try {
      this.m3 = new EuropeanSolitaireModel(5, 0, 1);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is
      // same as
      // that of an invalid slot position
    }
  }

  public void testValid() {
    // // empty constructor
    // assertEquals(36, this.m1.getScore());
    // assertEquals(7, this.m1.getBoardSize());

    // // for each row (i)
    // for (int i = 0; i < this.m1.getBoardSize(); i++) {
    // // for each column (i)
    // for (int j = 0; j < this.m1.getBoardSize(); j++) {
    // if (this.testValidHelper(this.m1, i, j)) {
    // // checks an Invalid SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
    // this.m1.getSlotAt(i, j));
    // } else if (i == 3 && j == 3) {
    // assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m1.getSlotAt(i,
    // j));
    // } else {
    // // checks a Marble SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m1.getSlotAt(i,
    // j));
    // }
    // }
    // }

    // // arm thickness = 5
    // assertEquals(36, this.m2.getScore());
    // assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
    // this.m2.getSlotAt(6, 6));
    // assertEquals(7, this.m2.getBoardSize());

    // // for each row (i)
    // for (int i = 0; i < this.m2.getBoardSize(); i++) {
    // // for each column (i)
    // for (int j = 0; j < this.m2.getBoardSize(); j++) {
    // if (this.testValidHelper(this.m2, i, j)) {
    // // checks an Invalid SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
    // this.m2.getSlotAt(i, j));
    // } else if (i == 6 && j == 6) {
    // assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(i,
    // j));
    // } else {
    // // checks a Marble SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m2.getSlotAt(i,
    // j));
    // }
    // }
    // }

    // // constructor with 2 arguments
    // assertEquals(32, this.m3.getScore());
    // assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(2,
    // 4));
    // assertEquals(7, this.m3.getBoardSize());

    // // for each row (i)
    // for (int i = 0; i < this.m3.getBoardSize(); i++) {
    // // for each column (i)
    // for (int j = 0; j < this.m3.getBoardSize(); j++) {
    // if (this.testValidHelper(this.m3, i, j)) {
    // // checks an Invalid SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
    // this.m3.getSlotAt(i, j));
    // } else if (i == 2 && j == 4) {
    // assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(i,
    // j));
    // } else {
    // // checks a Marble SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m3.getSlotAt(i,
    // j));
    // }
    // }
    // }

    // // constructor with 3 arguments
    // assertEquals(32, this.m4.getScore());
    // assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(3,
    // 3));
    // assertEquals(7, this.m4.getBoardSize());

    // // for each row (i)
    // for (int i = 0; i < this.m4.getBoardSize(); i++) {
    // // for each column (i)
    // for (int j = 0; j < this.m4.getBoardSize(); j++) {
    // if (this.testValidHelper(this.m4, i, j)) {
    // // checks an Invalid SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
    // this.m4.getSlotAt(i, j));
    // } else if (i == 3 && j == 3) {
    // assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(i,
    // j));
    // } else {
    // // checks a Marble SlotState is in the correct position
    // assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m4.getSlotAt(i,
    // j));
    // }
    // }
    // }
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
    // move up
    assertEquals(36, this.m1.getScore());
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    m1.move(5, 3, 3, 3);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(5, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 3));
    assertEquals(35, this.m1.getScore());

    // move right
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 3));
    m1.move(4, 1, 4, 3);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 3));
    assertEquals(34, this.m1.getScore());

    // move down
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(5, 3));
    m1.move(3, 3, 5, 3);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(5, 3));
    assertEquals(33, this.m1.getScore());

    // move left
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 3));
    m1.move(4, 5, 4, 3);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 3));
    assertEquals(32, this.m1.getScore());

    try {
      this.m1.move(-2, 0, 0, 0);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if the from position does
      // not exist
      // within the current board's dimensions
    }

    try {
      this.m1.move(1, 0, 0, 8);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if the to position does not
      // exist
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
      this.m1.move(1, 0, 1, 4);
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
      this.m1.move(0, 2, 0, 0);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when
      // the to position is a marble
    }

    try {
      this.m1.move(2, 1, 0, 5);
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
    this.m1 = new EuropeanSolitaireModel(2, 3);
    assertFalse(this.m1.isGameOver());
    assertEquals(36, this.m1.getScore());
    this.m1.move(2, 1, 2, 3);
    assertEquals(35, this.m1.getScore());
    this.m1.move(0, 2, 2, 2);
    assertEquals(34, this.m1.getScore());
    this.m1.move(4, 1, 2, 1);
    assertEquals(33, this.m1.getScore());
    this.m1.move(4, 3, 4, 1);
    assertEquals(32, this.m1.getScore());
    this.m1.move(2, 3, 4, 3);
    assertEquals(31, this.m1.getScore());
    this.m1.move(1, 4, 1, 2);
    assertEquals(30, this.m1.getScore());
    this.m1.move(2, 1, 2, 3);
    assertEquals(29, this.m1.getScore());
    this.m1.move(0, 4, 0, 2);
    assertEquals(28, this.m1.getScore());
    this.m1.move(4, 4, 4, 2);
    assertEquals(27, this.m1.getScore());
    this.m1.move(3, 4, 1, 4);
    assertEquals(26, this.m1.getScore());
    this.m1.move(6, 3, 4, 3);
    assertEquals(25, this.m1.getScore());
    this.m1.move(1, 1, 1, 3);
    assertEquals(24, this.m1.getScore());
    this.m1.move(4, 6, 4, 4);
    assertEquals(23, this.m1.getScore());
    this.m1.move(5, 1, 3, 1);
    assertEquals(22, this.m1.getScore());
    this.m1.move(2, 6, 2, 4);
    assertEquals(21, this.m1.getScore());
    this.m1.move(1, 4, 1, 2);
    assertEquals(20, this.m1.getScore());
    this.m1.move(0, 2, 2, 2);
    assertEquals(19, this.m1.getScore());
    this.m1.move(3, 6, 3, 4);
    assertEquals(18, this.m1.getScore());
    this.m1.move(4, 3, 4, 1);
    assertEquals(17, this.m1.getScore());
    this.m1.move(6, 2, 4, 2);
    assertEquals(16, this.m1.getScore());
    this.m1.move(2, 3, 2, 1);
    assertEquals(15, this.m1.getScore());
    this.m1.move(4, 1, 4, 3);
    assertEquals(14, this.m1.getScore());
    this.m1.move(5, 5, 5, 3);
    assertEquals(13, this.m1.getScore());
    this.m1.move(2, 0, 2, 2);
    assertEquals(12, this.m1.getScore());
    this.m1.move(2, 2, 4, 2);
    assertEquals(11, this.m1.getScore());
    this.m1.move(3, 4, 5, 4);
    assertEquals(10, this.m1.getScore());
    this.m1.move(4, 3, 4, 1);
    assertEquals(9, this.m1.getScore());
    this.m1.move(3, 0, 3, 2);
    assertEquals(8, this.m1.getScore());
    this.m1.move(6, 4, 4, 4);
    assertEquals(7, this.m1.getScore());
    this.m1.move(4, 0, 4, 2);
    assertEquals(6, this.m1.getScore());
    this.m1.move(3, 2, 5, 2);
    assertEquals(5, this.m1.getScore());
    this.m1.move(5, 2, 5, 4);
    assertEquals(4, this.m1.getScore());
    this.m1.move(5, 4, 3, 4);
    assertEquals(3, this.m1.getScore());
    this.m1.move(3, 4, 1, 4);
    assertEquals(2, this.m1.getScore());
    this.m1.move(1, 5, 1, 3);
    assertEquals(1, this.m1.getScore());
    assertTrue(this.m1.isGameOver());
  }

  // tests the getBoardSize method
  @Test
  public void testGetBoardSize() {
    assertEquals(7, m1.getBoardSize());
    assertEquals(7, m2.getBoardSize());
    assertEquals(7, m3.getBoardSize());
    assertEquals(7, m4.getBoardSize());
    assertEquals(13, m5.getBoardSize());
  }

  // tests the getSlotAt method
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 2));
    assertNotEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(0, 6));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 3));
    assertNotEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m2.getSlotAt(5, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m3.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m5.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m5.getSlotAt(6, 6));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m5.getSlotAt(4, 9));

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
    assertEquals(36, this.m1.getScore());
    assertEquals(128, this.m5.getScore());
    assertEquals(36, this.m3.getScore());
    m5.move(6, 4, 6, 6);
    assertNotEquals(128, this.m5.getScore());
    assertEquals(127, this.m5.getScore());
  }
}