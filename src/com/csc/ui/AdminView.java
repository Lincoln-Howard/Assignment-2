package com.csc.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class AdminView extends BorderPane {
  
  public AdminView () {
    setCenter (new MerchantView ());
    HBox commands = new HBox ();
    setTop (commands);
    Button addItem = new Button ("Add Item");
    addItem.addEventHandler (MouseEvent.MOUSE_CLICKED, new AddItemHandler ());
    Button editItem = new Button ("Edit Items");
    editItem.addEventHandler (MouseEvent.MOUSE_CLICKED, new EditItemHandler ());
    Button back = new Button ("Back");
    back.addEventHandler (MouseEvent.MOUSE_CLICKED, new BackHandler ());
    commands.getChildren ().addAll (addItem, editItem, back);
  }
  
  private class AddItemHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      AdminView.this.setCenter (new AddItemView ());
    }
  }
  private class EditItemHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      AdminView.this.setCenter (new EditItemView ());
    }
  }
  private class BackHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      Main.root.setCenter (new AdminView ());
    }
    
  }
}