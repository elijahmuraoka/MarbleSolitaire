import org.junit.Before;
import org.junit.Test;

import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.MarbleSolitaireModelState;
import server.model.hw04.AbstractSolitaireModel;
import server.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents all the tests for methods supported in the EnglishSolitaireModel class.
 */
public class TriangleSolitaireModelTest extends AbstractSolitaireModelTest {
  private AbstractSolitaireModel m1;
  private AbstractSolitaireModel m2;
  private AbstractSolitaireModel m3;
  private AbstractSolitaireModel m4;
  private AbstractSolitaireModel m5;

  // sets up initial examples to be used in testing
  @Before
  public void init() {
    this.m1 = new TriangleSolitaireModel();
    this.m2 = new TriangleSolitaireModel(4);
    this.m3 = new TriangleSolitaireModel(3, 2);
    this.m4 = new TriangleSolitaireModel(3, 2, 0);
    this.m5 = new TriangleSolitaireModel(7);
  }

  @Override
  protected AbstractSolitaireModel generateModelEmpty() {
    return new EnglishSolitaireModel();
  }

  @Override
  protected AbstractSolitaireModel generateModelOne(int at) {
    return new TriangleSolitaireModel(at);
  }

  @Override
  protected AbstractSolitaireModel generateModelTwo(int sRow, int sCol) {
    return new TriangleSolitaireModel(sRow, sCol);
  }

  @Override
  protected AbstractSolitaireModel generateModelThree(int at, int row, int col) {
    return new TriangleSolitaireModel(at, row, col);
  }

  // sets up initial examples with invalid arguments
  @Test
  public void initInvalidModels() {
    try {
      this.m1 = new TriangleSolitaireModel(-25);
      fail("Arm Thickness cannot be negative.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a negative arm thickness
    }

    try {
      this.m2 = new TriangleSolitaireModel(1, 2);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }
    try {
      this.m2 = new TriangleSolitaireModel(2, 3);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }

    try {
      this.m3 = new TriangleSolitaireModel(4, 1, 2);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }

    try {
      this.m2 = new TriangleSolitaireModel(-4, 3, 3);
      fail("Arm Thickness cannot be negative.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception for a negative arm thickness
    }

    try {
      this.m3 = new TriangleSolitaireModel(3, 5, 7);
      fail("Row and/or Column inputs are outside this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the given empty position is
      // outside the board's x and/or y-ranges
    }

    try {
      this.m3 = new TriangleSolitaireModel(3, 5, 7);
      fail("Row and/or Column inputs are outside this board's dimensions.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when the given empty position is
      // outside the board's x and/or y-ranges
    }

    try {
      this.m3 = new TriangleSolitaireModel(5, 0, 2);
      fail("Invalid empty cell position (r,c).");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception when empty cell position is same as
      // that of an invalid slot position
    }
  }

  @Test
  public void testValid() {
    // empty constructor
    assertEquals(14, this.m1.getScore());
    assertEquals(5, this.m1.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m1.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m1.getBoardSize(); j++) {
        if (j > i) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m1.getSlotAt(i, j));
        } else if (i == 0 && j == 0) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m1.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m1.getSlotAt(i, j));
        }
      }
    }

    // constructor with arm thickness = 4
    assertEquals(9, this.m2.getScore());
    assertEquals(4, this.m2.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m2.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m2.getBoardSize(); j++) {
        if (j > i) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m2.getSlotAt(i, j));
        } else if (i == 0 && j == 0) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m2.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m2.getSlotAt(i, j));
        }
      }
    }

    // constructor with empty slot at 3,2 (r,c)
    assertEquals(14, this.m3.getScore());
    assertEquals(5, this.m3.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m3.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m3.getBoardSize(); j++) {
        if (j > i) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m3.getSlotAt(i, j));
        } else if (i == 3 && j == 2) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m3.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m3.getSlotAt(i, j));
        }
      }
    }

    // constructor with arm thickness = 3, ane empty position at 2,0 (r,c)
    assertEquals(5, this.m4.getScore());
    assertEquals(3, this.m4.getBoardSize());

    // for each row (i)
    for (int i = 0; i < this.m4.getBoardSize(); i++) {
      // for each column (i)
      for (int j = 0; j < this.m4.getBoardSize(); j++) {
        if (j > i) {
          // checks an Invalid SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.m4.getSlotAt(i, j));
        } else if (i == 2 && j == 0) {
          assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.m4.getSlotAt(i, j));
        } else {
          // checks a Marble SlotState is in the correct position
          assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.m4.getSlotAt(i, j));
        }
      }
    }
  }

  // tests the move method
  @Test
  public void testMove() {
    // move up
    assertEquals(14, this.m1.getScore());
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(0, 0));
    m1.move(2, 0, 0, 0);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(0, 0));
    assertEquals(13, this.m1.getScore());

    // move diagonal up left
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(1, 0));
    m1.move(3, 2, 1, 0);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(1, 0));
    assertEquals(12, this.m1.getScore());

    // move right
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 2));
    m1.move(3, 0, 3, 2);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 2));
    assertEquals(11, this.m1.getScore());

    // move down
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 0));
    m1.move(0, 0, 2, 0);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 0));
    assertEquals(10, this.m1.getScore());

    this.m1 = new TriangleSolitaireModel();
    this.m1.move(2, 2, 0, 0);
    // move diagonal up right
    assertEquals(13, this.m1.getScore());
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 2));
    m1.move(4, 0, 2, 2);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 2));
    assertEquals(12, this.m1.getScore());

    this.m1 = new TriangleSolitaireModel();
    this.m1.move(2, 0, 0, 0);
    // move left
    assertEquals(13, this.m1.getScore());
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 0));
    m1.move(2, 2, 2, 0);
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(2, 0));
    assertEquals(12, this.m1.getScore());

    try {
      this.m1.move(-2, 0, 0, 0);
      fail("The move you are trying to make is invalid.");
    } catch (IllegalArgumentException e) {
      // successfully throws an illegal argument exception if the from position does not exist
      // within the current board's dimensions
    }

    try {
      this.m1.move(1, 0, 0, 8);
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
      this.m1.move(1, 3, 3, 3);
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
    assertFalse(this.m4.isGameOver());
    assertEquals(5, this.m4.getScore());
    this.m4.move(2, 2, 2, 0);
    this.m4.move(0, 0, 2, 2);
    this.m4.move(2, 0, 0, 0);
    assertEquals(2, this.m4.getScore());
    assertTrue(this.m4.isGameOver());
  }

  // tests the getBoardSize method
  @Test
  public void testGetBoardSize() {
    assertEquals(5, m1.getBoardSize());
    assertEquals(4, m2.getBoardSize());
    assertEquals(5, m3.getBoardSize());
    assertEquals(3, m4.getBoardSize());
    assertEquals(7, m5.getBoardSize());
  }

  // tests the getSlotAt method
  @Test
  public void testGetSlotAt() {
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m1.getSlotAt(4, 2));
    assertNotEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(0, 1));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(0, 0));
    assertNotEquals(MarbleSolitaireModel.SlotState.Empty, this.m1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m2.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m3.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModel.SlotState.Invalid, this.m5.getSlotAt(4, 5));
    assertEquals(MarbleSolitaireModel.SlotState.Empty, this.m5.getSlotAt(0, 0));
    assertEquals(MarbleSolitaireModel.SlotState.Marble, this.m5.getSlotAt(5, 5));


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
    assertEquals(14, this.m1.getScore());
    assertEquals(9, this.m2.getScore());
    assertEquals(14, this.m3.getScore());
    m1.move(2, 2, 0, 0);
    assertNotEquals(14, this.m1.getScore());
    assertEquals(13, this.m1.getScore());
    m1.move(2, 0, 2, 2);
    assertEquals(12, this.m1.getScore());
  }
}
