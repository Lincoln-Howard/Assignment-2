package com.csc.ui;

import com.csc.model.CategoryNode;
import com.csc.model.FoodItem;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class MerchantView extends Pane {
  
  public MerchantView () {
    TabPane tpain = new TabPane ();
    getChildren ().add (tpain);
    for (CategoryNode category : Main.menu) {
      Tab tab = new Tab (category.name ());
      tab.setClosable (false);
      BorderPane border = new BorderPane ();
      tab.setContent (border);
      HBox heads = new HBox ();
      border.setTop (heads);
      Label [] labels = {
        new Label ("Name"),
        new Label ("Price"),
        new Label ("Description"),
        new Label ("Quantity"),
        new Label ("Size")
      };
      for (int i = 0; i < 5; i++) {
        labels [i].setFont (Font.font ("Serif", FontWeight.BOLD, 20));
        labels [i].setPrefSize (150, 25);
        labels [i].setTextAlignment (TextAlignment.CENTER);
      }
      heads.getChildren ().addAll (labels);
      ScrollPane scroll = new ScrollPane ();
      border.setCenter (scroll);
      VBox vbox= new VBox ();
      scroll.setContent (vbox);
      for (FoodItem item : category.data ()) {
        HBox row = new HBox ();
        Label name = new Label (item.name ());
        name.setPrefSize (150, 25);
        name.setWrapText (true);
        Label price = new Label (item.price ());
        price.setPrefSize (150, 25);
        price.setWrapText (true);
        Label desc = new Label (item.description ());
        desc.setPrefSize (150, 25);
        desc.setWrapText (true);
        Label quantity = new Label (item.quantity ());
        quantity.setPrefSize (150, 25);
        quantity.setWrapText (true);
        Label size = new Label (item.size ());
        size.setPrefSize (150, 25);
        size.setWrapText (true);
        row.getChildren ().addAll (
          name,
          price,
          desc,
          quantity,
          size
        );
        vbox.getChildren ().add (row);
      }
      tpain.getTabs ().add (tab);
    }
  }
}