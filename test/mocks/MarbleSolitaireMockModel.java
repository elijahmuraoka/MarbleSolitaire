package mocks;

import server.model.hw02.MarbleSolitaireModel;

/**
 * This class represents a mock model of the ModelSolitaireModel class,
 * which is used to log and verify inputs through the model.
 */
public class MarbleSolitaireMockModel implements MarbleSolitaireModel {
  private StringBuilder log;
  private int uniqueCode;

  /**
   * This is the constructor for my mock model in order to keep track of a user's inputs given a
   * unique code and a (initially empty) log.
   *
   * @param uniqueCode an integer that can be used to identify a specific instance of a
   *                   MarbleSolitaireMockModel
   * @param log        a StringBuilder used to keep track and log various inputs that pass through
   *                   operations in this model
   */
  public MarbleSolitaireMockModel(int uniqueCode, StringBuilder log) {
    this.uniqueCode = uniqueCode;
    this.log = log;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.log.append("fromRow: ").append(fromRow).append(", ")
            .append("fromCol: ").append(fromCol).append(", ")
            .append("toRow: ").append(toRow).append(", ")
            .append("toCol: ").append(toCol);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return uniqueCode;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return uniqueCode;
  }
}
