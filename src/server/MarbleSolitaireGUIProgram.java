package server;
import server.controller.features.ControllerFeatures;
import server.controller.features.ControllerFeaturesImpl;
import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.view.MarbleSolitaireGuiView;
import server.view.SwingGuiView;

/**
 * The MarbleSolitaireProgram class that creates a GUI.
 */
public class MarbleSolitaireGUIProgram {

  public static void main(String... args) {
    MarbleSolitaireModel standardEnglish = new EnglishSolitaireModel();
    MarbleSolitaireGuiView guiView = new SwingGuiView(standardEnglish);
    ControllerFeatures controller = new ControllerFeaturesImpl(standardEnglish, guiView);
    guiView.renderMessage("<html>Welcome to Marble Solitaire!"
            + "<br/>Any user messages will appear here.<html>");
  }
}
