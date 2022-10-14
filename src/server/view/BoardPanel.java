package server.view;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import server.controller.features.ControllerFeatures;
import server.model.hw02.MarbleSolitaireModel;
import server.model.hw02.MarbleSolitaireModelState;

public class BoardPanel extends JPanel implements IBoardPanel {
  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot, emptySlotH, marbleSlotH;
  private final int cellDimension;
  private int originX, originY;
  private MouseAdapterImpl mouseAdapter;

  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("src/server/view/res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("src/server/view/res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("src/server/view/res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      File emptyH = new File("src/server/view/res/emptyH.jpg");
      emptySlotH = ImageIO.read(new FileInputStream(emptyH));
      emptySlotH = emptySlotH.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      File marbleH = new File("src/server/view/res/marbleH.jpg");
      marbleSlotH = ImageIO.read(new FileInputStream(marbleH));
      marbleSlotH = marbleSlotH.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(
          new Dimension((this.modelState.getBoardSize() + 4) * cellDimension,
              (this.modelState.getBoardSize() + 4) * cellDimension));

      originX = (int) (this.getPreferredSize().getWidth() / 2 - this.modelState.getBoardSize()
          * cellDimension / 2);
      originY = (int) (this.getPreferredSize().getHeight() / 2 - this.modelState.getBoardSize()
          * cellDimension / 2);
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // your code to draw the board should go here.
    // The originX and originY is the top-left of where the cell (0,0) should start
    // cellDimension is the width (and height) occupied by every cell

    int bs = this.modelState.getBoardSize();
    // for each row
    for (int i = 0; i < bs; i++) {
      // for each column
      for (int j = 0; j < bs; j++) {
        MarbleSolitaireModel.SlotState current = this.modelState.getSlotAt(i, j);
        this.paintComponentHelper(g, originX + (j * this.cellDimension),
            originY + (i * this.cellDimension), current);
      }
    }
    if (this.mouseAdapter.getMouseReleased()) {
      int currentMouseRow = this.mouseAdapter.getCurrentRow();
      int currentMouseCol = this.mouseAdapter.getCurrentCol();
      int currentMouseY = originY + (this.mouseAdapter.getCurrentRow() * this.cellDimension);
      int currentMouseX = originX + (this.mouseAdapter.getCurrentCol() * this.cellDimension);
      switch (this.modelState.getSlotAt(currentMouseRow, currentMouseCol)) {
        case Empty:
          g.drawImage(emptySlotH, currentMouseX, currentMouseY, null);
          break;
        case Marble:
          g.drawImage(marbleSlotH, currentMouseX, currentMouseY, null);
          break;
      }
    }
    // int currentMouseRow = this.mouseAdapter.getCurrentRow();
    // int currentMouseY = originY + this.mouseAdapter.getCurrentRow() *
    // this.cellDimension;
    // int currentMouseCol = this.mouseAdapter.getCurrentCol();
    // int currentMouseX = originX + this.mouseAdapter.getCurrentCol() *
    // this.cellDimension;
    // switch (this.modelState.getSlotAt(currentMouseRow, currentMouseCol)) {
    // case Empty:
    // g.drawImage(emptySlot, currentMouseX, currentMouseY, null);
    // break;
    // case Marble:
    // g.drawImage(marbleSlot, currentMouseX, currentMouseY, null);
    // break;
    // }
  }

  /**
   * The helper method for toString used to display a symbol for each respective
   * slot state.
   *
   * @param s the given slot state
   * @return the string representation of a specific slot state
   */
  protected void paintComponentHelper(Graphics g, int x, int y, MarbleSolitaireModel.SlotState s)
      throws IllegalArgumentException {
    switch (s) {
      case Empty:
        g.drawImage(emptySlot, x, y, null);
        break;
      case Marble:
        g.drawImage(marbleSlot, x, y, null);
        break;
      case Invalid:
        g.drawImage(blankSlot, x, y, null);
        break;
      default:
        throw new IllegalArgumentException("The given slot state parameter is invalid.");
    }
  }

  @Override
  public void addMouseListener(ControllerFeatures c) {
    this.mouseAdapter = new MouseAdapterImpl(c, this.cellDimension, this.originX, this.originY,
        this.modelState.getBoardSize());
    this.addMouseListener(this.mouseAdapter);
  }
}
