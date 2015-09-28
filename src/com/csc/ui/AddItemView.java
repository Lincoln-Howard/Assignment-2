package com.csc.ui;

import com.csc.model.FoodItem;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AddItemView extends GridPane {
  Label [] lbl;
  TextField [] fields;
  public AddItemView () {
    Label [] l = {
      new Label ("Category"),
      new Label ("Name"),
      new Label ("Price"),
      new Label ("Description"),
      new Label ("Quantity"),
      new Label ("Size")
    };
    lbl = l;
    TextField [] f = {
      new TextField (),
      new TextField (),
      new TextField (),
      new TextField (),
      new TextField (),
      new TextField ()
    };
    fields = f;
    for (int i = 0; i < 6; i++) {
      add (lbl [i], 0, i);
      add (fields [i], 1, i);
    }
    Button submit = new Button ("Submit");
    submit.addEventHandler (MouseEvent.MOUSE_CLICKED, new SubmitHandler ());
    add (submit, 0, 6);
  }
  
  private class SubmitHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      Main.menu.add (fields [0].getText ());
      Main.menu.add (fields [0].getText (), new FoodItem (fields [1].getText (),
          fields [2].getText (), fields [3].getText (), fields [4].getText (), fields [5].getText ()));
      Main.save ();
      Main.root.setCenter (new AdminView ());
    }
  }
}