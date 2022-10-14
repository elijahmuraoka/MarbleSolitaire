import org.junit.Test;

import mocks.MarbleSolitaireGuiMockView;
import server.controller.features.ControllerFeatures;
import server.controller.features.ControllerFeaturesImpl;
import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.view.MarbleSolitaireGuiView;
import server.view.MouseAdapterImpl;

import static org.junit.Assert.assertEquals;

public class ControllerFeaturesImplTest {
  MarbleSolitaireModel model;
  MarbleSolitaireGuiView view;
  ControllerFeatures controller;
  StringBuilder s;

  MouseAdapterImpl mouseAdapter;

  @Test
  public void inputValues() {
    this.s = new StringBuilder();
    this.model = new EnglishSolitaireModel();
    this.view = new MarbleSolitaireGuiMockView(this.s);
    this.controller = new ControllerFeaturesImpl(this.model, this.view);
    this.mouseAdapter = new MouseAdapterImpl(this.controller, 0, 0, 0, 0);
    this.controller.inputValues(this.mouseAdapter, 3, 1);
    assertEquals(32, this.model.getScore());
    this.controller.inputValues(this.mouseAdapter, 3, 3);
    System.out.println(this.s.toString());
  }
}