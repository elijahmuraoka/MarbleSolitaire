package server.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import server.controller.features.ControllerFeatures;

public class MouseAdapterImpl extends MouseAdapter {
  ControllerFeatures c;
  int cellDimension;
  int originX;
  int originY;
  int currentRow;
  int currentCol;
  boolean mouseReleased;
  int boardSize;

  public MouseAdapterImpl(ControllerFeatures c, int cellDimension, int originX, int originY,
      int boardSize) {
    this.c = c;
    this.cellDimension = cellDimension;
    this.originX = originX;
    this.originY = originY;
    this.boardSize = boardSize;
    this.mouseReleased = false;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    this.currentRow = (int) ((e.getY() - originY) / this.cellDimension * 1.0);
    this.currentCol = (int) ((e.getX() - originX) / this.cellDimension * 1.0);
    if (this.currentRow >= 0 && this.currentRow < this.boardSize
        && this.currentCol >= 0 && this.currentCol < this.boardSize) {
      this.mouseReleased = true;
      this.c.inputValues(this, this.currentRow, this.currentCol);
    }
  }

  public void setMouseReleased(boolean b) {
    this.mouseReleased = b;
  }

  public int getCurrentRow() {
    return this.currentRow;
  }

  public int getCurrentCol() {
    return this.currentCol;
  }

  public boolean getMouseReleased() {
    return this.mouseReleased;
  }
}
