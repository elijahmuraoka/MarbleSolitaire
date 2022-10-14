package server.view;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import server.controller.features.ControllerFeatures;
import server.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents a GUI view that is implemented using Java Swing.
 */
public class SwingGuiView extends JFrame implements MarbleSolitaireGuiView {

  //the custom panel on which the board will be drawn
  private IBoardPanel boardPanel;
  //the model state
  private MarbleSolitaireModelState modelState;
  //a label to display the score
  private JLabel scoreLabel;
  //a label to display any messages to the user
  private JLabel messageLabel;
  private ControllerFeatures c;

  public SwingGuiView(MarbleSolitaireModelState state) {
    super("Marble solitaire");
    this.modelState = state;
    /*
     * main frame uses a border layout. Read about it here:
     */

    this.setLayout(new BorderLayout());
    //initialize the custom board with the model state
    boardPanel = new BoardPanel(this.modelState);
    //add custom board to the center of the frame
    this.add((Component) boardPanel, BorderLayout.CENTER);
    //create the score label
    this.scoreLabel = new JLabel();
    this.scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, 25));
    //create the message label
    //    this.messageLabel = new JLabel();
    //    this.messageLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));
    //put them both in a panel. This is done mostly to arrange them properly
    JPanel panel = new JPanel();
    /*
    the panel uses a grid layout with two columns. The gridlayout
    will stretch the labels so that they are exactly half of the width
    of this panel.

    Since we mention that we want a grid of 2 columns, and we
    add exactly two things to it, it will use one row.
     */

    //    System.out.println(this.getWidth());
    //    System.out.println(this.getHeight());
    // panel.setLayout(new GridLayout(0, 1));
    panel.add(scoreLabel);
    // panel.add(messageLabel);
    //add this panel to the bottom of the frame
    this.add(panel, BorderLayout.SOUTH);

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public void refresh() {
    //refresh the score
    this.scoreLabel.setText("Score: " + modelState.getScore());
    //this repaints the frame, which cascades to everything added
    //in the frame
    this.repaint();
    this.revalidate();
    this.c.gameOver();
  }

  @Override
  public void renderMessage(String message) throws IllegalStateException {
    Image messageImageScaled;
    try {
      Image messageImage = ImageIO.read(new FileInputStream("src/server/view/res/shrek.png"));
      messageImageScaled = messageImage.getScaledInstance(50, 70, Image.SCALE_DEFAULT);
    } catch (IOException e) {
      throw new IllegalStateException("Icon not found!");
    }
    Icon messageIcon = new ImageIcon(messageImageScaled);
    JOptionPane.showMessageDialog(this, message, "User Information",
            JOptionPane.INFORMATION_MESSAGE, messageIcon);
  }

  @Override
  public void addMouseListenerToPanel(ControllerFeatures c) {
    this.boardPanel.addMouseListener(c);
  }

  @Override
  public void addGameOverListener(ControllerFeatures c) {
    this.c = c;
  }
}

