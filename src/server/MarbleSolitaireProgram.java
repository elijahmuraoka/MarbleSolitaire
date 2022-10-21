package server;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import server.controller.MarbleSolitaireController;
import server.controller.MarbleSolitaireControllerImpl;
import server.controller.features.ControllerFeatures;
import server.controller.features.ControllerFeaturesImpl;
import server.model.hw02.EnglishSolitaireModel;
import server.model.hw02.MarbleSolitaireModel;
import server.model.hw04.EuropeanSolitaireModel;
import server.model.hw04.TriangleSolitaireModel;
import server.view.MarbleSolitaireGuiView;
import server.view.MarbleSolitaireTextView;
import server.view.MarbleSolitaireView;
import server.view.SwingGuiView;
import server.view.TriangleSolitaireTextView;

/**
 * This class represents the entire Marble Solitaire Program.
 */
public class MarbleSolitaireProgram {

  /**
   * The main method to run the entire Marble Solitaire Program and play the game
   * through the command line.
   *
   * @param args the user's inputs represented as an array of strings
   */
  // public static void main(String[] args) {
  // Integer at = null;
  // Integer row = null;
  // Integer col = null;
  // AbstractSolitaireModel model = null;
  // AbstractMarbleSolitaireTextView view = null;
  // Readable in = new InputStreamReader(System.in);
  // int i = 0;
  // int argsCap = 3;
  // String current = args[i];
  // String[] knownModelTypes = {"english", "european", "triangle"};
  // String currentModel = "";
  //
  // for (String m : knownModelTypes) {
  // if (current.equalsIgnoreCase(m)) {
  // currentModel = current.toLowerCase();
  // argsCap--;
  // break;
  // }
  // }
  //
  // // while there can still be passed in arguments and there are arguments left
  // while (argsCap > 0 && i < args.length - 1) {
  // i++;
  // current = args[i];
  // switch (current.toLowerCase()) {
  // case "-size": {
  // try {
  // i++;
  // at = Integer.parseInt(args[i]);
  // argsCap--;
  // } catch (NumberFormatException e) {
  // throw new IllegalArgumentException("Not a valid parameter following '-Size',
  // " +
  // "must pass in an integer.");
  // }
  // break;
  // }
  // case "-hole": {
  // try {
  // i++;
  // row = Integer.parseInt(args[i]);
  // i++;
  // col = Integer.parseInt(args[i]);
  // argsCap--;
  // } catch (NumberFormatException e) {
  // throw new IllegalArgumentException("Not a valid row and/or column following
  // '-Hole', " +
  // "must pass in two integers representing row and column respectively.");
  // }
  // break;
  // }
  // }
  // }
  //
  // switch (currentModel) {
  // case "english": {
  // try {
  // model = new EnglishSolitaireModel(at, row, col);
  // view = new MarbleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e1) {
  // try {
  // model = new EnglishSolitaireModel(at);
  // view = new MarbleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e2) {
  // try {
  // model = new EnglishSolitaireModel(row, col);
  // view = new MarbleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e3) {
  // model = new EnglishSolitaireModel();
  // view = new MarbleSolitaireTextView(model);
  // break;
  // }
  // }
  // }
  // }
  // case "european": {
  // try {
  // model = new EuropeanSolitaireModel(at, row, col);
  // view = new MarbleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e1) {
  // try {
  // model = new EuropeanSolitaireModel(at);
  // view = new MarbleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e2) {
  // try {
  // model = new EuropeanSolitaireModel(row, col);
  // view = new MarbleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e3) {
  // model = new EuropeanSolitaireModel();
  // view = new MarbleSolitaireTextView(model);
  // break;
  // }
  // }
  // }
  // }
  // case "triangle": {
  // try {
  // model = new TriangleSolitaireModel(at, row, col);
  // view = new TriangleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e1) {
  // try {
  // model = new TriangleSolitaireModel(at);
  // view = new TriangleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e2) {
  // try {
  // model = new TriangleSolitaireModel(row, col);
  // view = new TriangleSolitaireTextView(model);
  // break;
  // } catch (NullPointerException e3) {
  // model = new TriangleSolitaireModel();
  // view = new TriangleSolitaireTextView(model);
  // break;
  // }
  // }
  // }
  // }
  // }
  //
  // try {
  // MarbleSolitaireController controller = new
  // MarbleSolitaireControllerImpl(model, view, in);
  // controller.playGame();
  // } catch (IllegalArgumentException e) {
  // throw new IllegalStateException("\nOne of more of these controller's
  // parameters are null." +
  // "\nPlease pass in new Command Line Arguments.");
  // }
  // }
  public static void main(String[] args) {
    StringBuilder readerInput = new StringBuilder();
    for (String arg : args) {
      readerInput.append(arg).append(" ");
    }
    StringReader reader = new StringReader(readerInput.toString());
    Scanner scan = new Scanner(reader);
    String current;
    try {
      current = scan.next();
    } catch (NoSuchElementException e) {
      System.out.println("Error: Invalid Argument(s), must define either text or gui program.");
      return;
    }
    switch (current.toLowerCase()) {
      case "":
      case "-text": {
        MarbleSolitaireProgram.mainHelper(scan);
        return;
      }
      case "-gui": {
        MarbleSolitaireModel standardEnglish = new EnglishSolitaireModel();
        MarbleSolitaireGuiView guiView = new SwingGuiView(standardEnglish);
        ControllerFeatures controller = new ControllerFeaturesImpl(standardEnglish, guiView);
        guiView.renderMessage("<html>Welcome to Marble Solitaire!"
            + "<br/>Any user messages will appear here.<html>");
        return;
      }
      default:
        System.out.println("Error: Invalid Argument(s), must define either text or gui program.");
    }
  }

  public static void mainHelper(Scanner scan) {
    String modelType = "";
    try {
      modelType = scan.next();
    } catch (NoSuchElementException e) {
      System.out.println("No model type found. Please pass in new arguments");
    }
    Integer at = null;
    Integer row = null;
    Integer col = null;
    int counter = 0;
    while (scan.hasNext() && counter <= 2) {
      String current = scan.next();
      switch (current.toLowerCase()) {
        case "-size": {
          try {
            at = Integer.parseInt(scan.next());
            counter++;
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a valid parameter following '-Size', " +
                "must pass in an integer.");
          }
          break;
        }
        case "-hole": {
          try {
            row = Integer.parseInt(scan.next());
            col = Integer.parseInt(scan.next());
            counter++;
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a valid row and/or column following '-Hole', " +
                "must pass in two integers representing row and column respectively.");
          }
          break;
        }
      }
    }
    MarbleSolitaireModel model = null;
    switch (modelType.toLowerCase()) {
      case "":
      case "english": {
        switch (counter) {
          case 0: {
            model = new EnglishSolitaireModel();
            break;
          }
          case 1: {
            model = new EnglishSolitaireModel(Objects.requireNonNull(at));
            break;
          }
          case 2: {
            model = new EnglishSolitaireModel(Objects.requireNonNull(at),
                Objects.requireNonNull(row), Objects.requireNonNull(col));
            break;
          }
          default:
            System.out.println("Error: Counter exceeded the maximum number of parameters.");
            break;
        }
        break;
      }
      case "european": {
        switch (counter) {
          case 0: {
            model = new EuropeanSolitaireModel();
            break;
          }
          case 1: {
            model = new EuropeanSolitaireModel(Objects.requireNonNull(at));
            break;
          }
          case 2: {
            model = new EuropeanSolitaireModel(Objects.requireNonNull(at),
                Objects.requireNonNull(row), Objects.requireNonNull(col));
            break;
          }
          default:
            System.out.println("Error: Counter exceeded the maximum number of parameters.");
            break;
        }
        break;
      }
      case "triangle": {
        switch (counter) {
          case 0: {
            model = new TriangleSolitaireModel();
            break;
          }
          case 1: {
            model = new TriangleSolitaireModel(Objects.requireNonNull(at));
            break;
          }
          case 2: {
            model = new TriangleSolitaireModel(Objects.requireNonNull(at),
                Objects.requireNonNull(row), Objects.requireNonNull(col));
            break;
          }
          default:
            System.out.println("Error: Counter exceeded the maximum number of parameters.");
            break;
        }
        break;
      }
    }
    Readable in = new InputStreamReader(System.in);
    try {
      MarbleSolitaireView view = new MarbleSolitaireTextView(model);
      if (modelType.toLowerCase().equals("triangle")) {
        view = new TriangleSolitaireTextView(model);
      }
      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, in);
      controller.playGame();
    } catch (NullPointerException e) {
      System.out.println("One or more arguments are not valid. Please try again.");
    }
  }
}
