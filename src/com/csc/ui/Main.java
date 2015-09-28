package com.csc.ui;

import java.io.FileNotFoundException;

import com.csc.io.JSON;
import com.csc.model.CategoryList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

  public static CategoryList menu;
  
  public static BorderPane root = new BorderPane ();
  
  @Override
  public void start(Stage primaryStage) throws Exception {
    menu = JSON.read ("menu.json");
    primaryStage.setMaximized (true);
    primaryStage.setScene (new Scene (root));
    root.setCenter (new ItemListView ());
    root.setBottom (null);
    primaryStage.show ();
  }

  public static void main (String [] args) {
    Application.launch (args);
  }

  public static void save() {
    try {
      JSON.write ("menu.json", menu);
    } catch (FileNotFoundException e) {
    }
  }
}