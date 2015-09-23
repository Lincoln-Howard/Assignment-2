package ui;

import javafx.scene.control.ComboBox;
import model.CategoryNode;
import model.FoodItem;
import model.LinkedList;

public class SearchFoods extends ComboBox <FoodItem> {
  private LinkedList <FoodItem> all;
  private LinkedList <FoodItem> options;
  public SearchFoods () {
    for (CategoryNode category : Main.menu)
      all.addAll (category.data ());
    all.sort (LinkedList.DIRECTION.ASCENDING);
  }
}