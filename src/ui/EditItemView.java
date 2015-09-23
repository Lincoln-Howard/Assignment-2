package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.CategoryNode;
import model.FoodItem;
import model.LinkedList;

public class EditItemView extends BorderPane {
  ObservableList <FoodItem> all;
  ListView <FoodItem> items;
  LinkedList <FoodItem> list;
  FoodItem current;
  Label [] lbl;
  TextField [] fields;
  Button set, remove;
  public EditItemView () {
    list = new LinkedList <FoodItem> ();
    for (CategoryNode category : Main.menu)
      list.addAll (category.data ());
    all = FXCollections.observableArrayList (list);
    items = new ListView <FoodItem> (all);
    setLeft (items);
    items.addEventHandler (MouseEvent.MOUSE_CLICKED, new SwitchHandler ());
    items.getSelectionModel ().setSelectionMode(SelectionMode.SINGLE);
    GridPane grid = new GridPane ();
    setCenter (grid);
    Label [] l = {
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
      new TextField ()
    };
    fields = f;
    for (int i = 0; i < 5; i++) {
      f [i].setEditable (false);
      f [i].setPrefSize (150, 20);
      grid.add (lbl [i], 0, i);
      grid.add (fields [i], 1, i);
    }
    set = new Button ("Set Item");
    set.addEventHandler (MouseEvent.MOUSE_CLICKED, new SetHandler ());
    set.setPrefSize (150, 20);
    grid.add (set, 1, 5, 2, 1);
    remove = new Button ("Remove Item");
    remove.addEventHandler (MouseEvent.MOUSE_CLICKED, new RemoveHandler ());
    remove.setPrefSize (150, 20);
    grid.add (remove, 1, 6, 2, 1);
  }
  private class SwitchHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      current = list.get (items.getSelectionModel ().getSelectedIndex ());
      for (int i = 0; i < 5; i++)
        fields [i].setEditable (true);
      fields [0].setText (current.name ());
      fields [1].setText (current.price ());
      fields [2].setText (current.description ());
      fields [3].setText (current.quantity ());
      fields [4].setText (current.size ());
      Main.save ();
    }
  }
  private class SetHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      current.name (fields [0].getText ());
      current.price (fields [1].getText ());
      current.description (fields [2].getText ());
      current.quantity (fields [3].getText ());
      current.size (fields [4].getText ());
      Main.save ();
      items.refresh ();
    }
  }
  private class RemoveHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      FoodItem item = list.remove (items.getSelectionModel ().getSelectedIndex ());
      items = new ListView <FoodItem> (all);
      all = FXCollections.observableArrayList (list);
      for (CategoryNode category : Main.menu)
        if (category.data ().contains (item))
          category.data ().remove (item);
      Main.save ();
      items.refresh ();
    }
  }
}