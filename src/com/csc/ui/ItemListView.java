package com.csc.ui;

import com.csc.model.*;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;

public class ItemListView extends GridPane {
  // the view of categories
  private ListView <CategoryNode> categories;
  // the view of items
  private ListView <FoodItem> items;
  // a list of all possible food items
  private LinkedList <FoodItem> allItems;
  // current category
  private CategoryNode currentCategory;
  // current item
  private FoodItem current;
  // search box for quick look ups of items/categories
  private TextField search;
  // the inputs for editing and creating elements
  private TextInputControl [] fields;
  // utility button in the edit box
  private Button utilBtn;
  // default constructor
  public ItemListView () {
    // handle category events
    addEventHandler (KeyEvent.KEY_PRESSED, new AddListener ());
    addEventHandler (KeyEvent.KEY_PRESSED, new RemoveListener ());
    // set visual stuff
    setVgap (5);
    setHgap (5);
    // create the search box object
    search = new TextField ();
    // add the search event handler
    search.addEventHandler (KeyEvent.KEY_RELEASED, new SearchHandler ());
    // add the search box
    add (search, 0, 0, 2, 1);
    // create category list
    categories = new ListView <CategoryNode> (FXCollections.observableArrayList (Main.menu));
    // set category width
    categories.setPrefWidth (175);
    // add event handler
    categories.addEventHandler (MouseEvent.MOUSE_CLICKED, new CatgeorySwitchHandler ());
    // add the category list
    add (categories, 0, 1);
    // create the item list
    items = new ListView <FoodItem> ();
    // adde event handler
    items.addEventHandler (MouseEvent.MOUSE_CLICKED, new ItemSwitchHandler ());
    // set items width
    items.setPrefWidth (175);
    // add item list view
    add (items, 1, 1);
    // initiate all
    allItems = new LinkedList <FoodItem> ();
    // add stuff to items
    for (CategoryNode category : Main.menu)
      allItems.addAll (category.data ());
    // create grid for editing
    GridPane edits = new GridPane ();
    // visual stuff
    edits.setVgap (12);
    edits.setHgap (12);
    edits.setPadding (new Insets (10));
    edits.setBorder (new Border (new BorderStroke (Paint.valueOf ("black"), BorderStrokeStyle.SOLID, new CornerRadii (10), new BorderWidths (1))));
    // create labels
    Label [] lbls = {
      new Label ("Category"),
      new Label ("Name"),
      new Label ("Price"),
      new Label ("Description"),
      new Label ("Quantity"),
      new Label ("Size")
    };
    // create descrition field
    TextArea description = new TextArea ();
    // wrap text
    description.setWrapText (true);
    // create fields
    TextInputControl [] f = {
        new TextField (),
        new TextField (),
        new TextField (),
        description,
        new TextField (),
        new TextField ()
    };
    // tricky
    fields = f;
    // add stuff to edits
    for (int i = 0; i < 6; i++) {
      lbls [i].setPrefWidth (75);
      f [i].setPrefWidth (225);
      edits.add (lbls [i], 0, i);
      edits.add (f [i], 1, i);
    }
    // util btn stuff
    utilBtn = new Button ("...");
    // add event handler
    utilBtn.addEventHandler (MouseEvent.MOUSE_CLICKED, new UtilBtnHandler ());
    // make the button expand
    GridPane.setHgrow (utilBtn, Priority.ALWAYS);
    utilBtn.setMaxWidth (Double.MAX_VALUE);
    // add the util button
    edits.add (utilBtn, 0, 6, 2, 1);
    // add the edits to the pane
    add (edits, 2, 0, 2, 2);
  }
  
  private class SearchHandler implements EventHandler <KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
      // if the text is empty refresh view and exit
      if (search.getText ().length () == 0) {
        refresh ();
        return;
      }
      // reset current
      current = null;
      currentCategory = null;
      // clear lists
      categories.setItems (FXCollections.observableArrayList ());
      items.setItems (FXCollections.observableArrayList ());
      // loop over all items
      for (FoodItem item : allItems)
        if (item.name ().toLowerCase ().contains (search.getText ().toLowerCase ()))
          // if an item contains the text, add it to the view
          items.getItems ().add (item);
      // loop over all categories
      for (CategoryNode category : Main.menu)
        if (category.name ().toLowerCase ().contains (search.getText ().toLowerCase ()))
          // if the category name contains the text, ad it to the view
          categories.getItems ().add (category);
      // refresh views
      items.refresh ();
      categories.refresh ();
    }
  }
  // handle category getting selected
  private class CatgeorySwitchHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      // get the selected category
      CategoryNode swap = categories.getSelectionModel ().getSelectedItem ();
      // if there was a null selection, return
      if (swap == null) return;
      // set category to swap
      currentCategory = swap;
      // make sure no food items are selected
      items.getSelectionModel ().clearSelection ();
      // refresh the view
      refresh ();
    }
  }
  // handle an item being selected
  private class ItemSwitchHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      // get the selected item
      FoodItem change = items.getSelectionModel ().getSelectedItem ();
      // if the item wasn't null, set current to that item
      if (change != null) {
        System.out.println (change);
        current = change;
      } else {
        System.out.println (change);
        return;
      }
      // if current is null, return
      if (current == null) return;
      // reset text of the util button
      utilBtn.setText ("Submit Changes");
      // set current category to the items category
      for (CategoryNode category : Main.menu)
        if (category.data ().contains (current))
          currentCategory = category;
      // set all fields
      fields [0].setText (currentCategory.name ());
      fields [1].setText (current.name ());
      fields [2].setText (current.price ());
      fields [3].setText (current.description ());
      fields [4].setText (current.quantity ());
      fields [5].setText (current.size ());
      // refresh view
      refresh ();
    }
  }
  // Handle the click of the utility button
  private class UtilBtnHandler implements EventHandler <MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
      // switch based on the button functionality
      switch (utilBtn.getText ()) {
      case "Submit Changes":
        // make sure all other fields are defined
        for (int i = 1; i < 6; i++)
          // if a field is left empty, return
          if (fields [i].getText () == "") return;
        // set all attributes of the current item
        current.name (fields [1].getText ());
        current.price (fields [2].getText ());
        current.description (fields [3].getText ());
        current.quantity (fields [4].getText ());
        current.size (fields [5].getText ());
        // save
        Main.save ();
        // refresh view
        items.refresh ();
        break;
      case "Add":
        // check that a category is specified, return if not
        if (fields [0].getText ().equals ("")) return;
        // add the category (if needed)
        Main.menu.add (fields [0].getText ());
        // reset current category
        currentCategory = Main.menu.get (Main.menu.byName (fields [0].getText ()));
        // save
        Main.save ();
        // make sure all other fields are defined
        for (int i = 1; i < 6; i++)
          // if a field is left empty, return
          if (fields [i].getText () == "") return;
        // create the new item
        FoodItem toAdd = new FoodItem (fields [1].getText (), fields [2].getText (),
            fields [3].getText (), fields [4].getText (), fields [5].getText ());
        // add the food item
        Main.menu.add (fields [0].getText (), toAdd);
        // save
        Main.save ();
        // refresh
        refresh ();
        break;
      }
    }
  }
  // on ctrl+n switch to an add feature
  private class AddListener implements EventHandler <KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
      // make sure control is down
      if (!event.isControlDown () || !event.getText ().equalsIgnoreCase ("n")) return;
      // see if the add function was already invoked
      if (utilBtn.getText ().equals ("Add")) {
        // if it was invoked, switch it off
        utilBtn.setText ("...");
        return;
      }
      // set current element/category to null
      current = null;
      currentCategory = null;
      // reset the fields
      for (int i = 0; i < 6; i++)
        fields [i].setText ("");
      // switch the button to say add
      utilBtn.setText ("Add");
    }
  }
  // add a remove feature on ctrl+r
  private class RemoveListener implements EventHandler <KeyEvent> {
    @Override
    public void handle(KeyEvent event) {
      // make sure control is down
      if (!event.isControlDown () || !event.getText ().equalsIgnoreCase ("r")) return;
      // make sure there is currently an item and category selected
      if ((current == null) || (currentCategory == null)) return;
      // remove the current item
      for (CategoryNode category : Main.menu)
        if (category.data ().contains (current))
          category.data ().remove (current);
    }
  }
  // simple method to refresh the lists
  private void refresh () {
    // make sure a category is selected
    if (currentCategory != null) {
      // set category and refresh
      categories.setItems (FXCollections.observableArrayList (Main.menu));
      categories.getSelectionModel ().select (currentCategory);
      categories.refresh ();
      // set items and refresh
      items.setItems (FXCollections.observableArrayList (currentCategory.data ()));
      if (current != null)
        items.getSelectionModel ().select (current);
      items.refresh ();
    } else {
      categories.setItems (FXCollections.observableArrayList (Main.menu));
      items.setItems (FXCollections.observableArrayList ());
    }
  }
}