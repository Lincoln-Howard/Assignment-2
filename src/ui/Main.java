package ui;

import io.JSON;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CategoryList;

public class Main extends Application {

  public static CategoryList menu;
  
  public static BorderPane root = new BorderPane ();
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    menu = JSON.read ("menu.json");
    primaryStage.setMaximized (true);
    primaryStage.setScene (new Scene (root));
    root.setCenter (new MerchantView ());
    root.setBottom (null);
    primaryStage.show ();
  }

  public static void main (String [] args) {
    Application.launch (args);
  }
}