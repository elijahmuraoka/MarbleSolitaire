package mocks;

import server.controller.features.ControllerFeatures;
import server.view.MarbleSolitaireGuiView;

public class MarbleSolitaireGuiMockView implements MarbleSolitaireGuiView {
  StringBuilder s;

  public MarbleSolitaireGuiMockView(StringBuilder s) {
    this.s = s;
  }

  @Override
  public void refresh() {
    this.s.append("This mock view has successfully refreshed.\n");
  }

  @Override
  public void renderMessage(String message) {
    this.s.append(message + "\n");
  }

  @Override
  public void addGameOverListener(ControllerFeatures c) {
    this.s.append("Added the game over listener successfully.\n");
  }

  @Override
  public void addMouseListenerToPanel(ControllerFeatures c) {
    this.s.append("Added the mouse listener to board panel.\n");
  }
}
